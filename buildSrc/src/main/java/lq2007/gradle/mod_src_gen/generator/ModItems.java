package lq2007.gradle.mod_src_gen.generator;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.*;
import lq2007.gradle.mod_src_gen.ModSourceGenerator;
import lq2007.gradle.mod_src_gen.Utils;
import org.eclipse.jdt.core.dom.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static lq2007.gradle.mod_src_gen.ClassTypes.*;

public class ModItems {

    private static final String TEMPLATE_FIELD_ITEM = "REGISTRY.register($S, () -> new $T($T.%s))";

    private TypeSpec.Builder items;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger(0);

    public void create(ModSourceGenerator task) throws IOException {
        items = TypeSpec.classBuilder("ModItems2");
        items.addField(FieldSpec.builder(DeferredRegister(Item), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.ITEMS, $T.MODID)", DeferredRegister, ForgeRegistries, SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin create items...");
        counter.set(0);
        addSeeds();
        addFoods();
        addKnives();
        addBuckets();
        addGroupItems();
        addTypedItem();
        task.logger.printLog("    Created " + counter.get() + " items.");
        items.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(IEventBus, "bus")
                .addCode("$T.getLogger().info($S);\n", SinoCraft, "Registering items.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.item", items.build()).build().writeTo(task.srcPath);
    }

    private void addSeeds() throws IOException {
        ClassName ItemSeed = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.base", "ItemSeed");
        ClassName PlantType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "PlantType");
        task.logger.printDetailedLog(" Create seeds use " + ItemSeed.canonicalName());

        Utils.getEnumValues(PlantType, task, "WHITE_RADISH", "SUMMER_RADISH", "GREEN_RADISH", "SOYBEAN").forEach(value -> {
            String identifier = value.getName().getIdentifier();
            String fieldName = identifier + "_SEED";
            String name = ((StringLiteral) value.arguments().get(0)).getLiteralValue() + "_seed";
            items.addField(FieldSpec.builder(RegistryObject(ItemSeed), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, identifier), name, ItemSeed, PlantType)
                    .build());
            task.logger.printDetailedLog("  Seed: " + fieldName + "{id = " + name + ", type = " + identifier + "}");
            counter.addAndGet(1);
        });
        items.addField(FieldSpec.builder(RegistryObject(ItemSeed), "SOYBEAN", Modifier.PUBLIC, Modifier.STATIC)
                .initializer(String.format(TEMPLATE_FIELD_ITEM, "SOYBEAN"), "soybean", ItemSeed, PlantType)
                .build());
        task.logger.printDetailedLog("  Seed: SOYBEAN{id = soybean, type = SOYBEAN}");
        counter.addAndGet(1);
    }

    private void addFoods() throws IOException {
        ClassName EnumFoods = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.food", "EnumFoods");
        ClassName ItemFood2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.food", "ItemFood2");
        task.logger.printDetailedLog(" Create foods use " + ItemFood2.canonicalName());

        Utils.getEnumValues(EnumFoods, task).forEach(value -> {
            String name = value.getName().getIdentifier();
            String id = name.toLowerCase(Locale.ROOT);
            items.addField(FieldSpec.builder(ItemFood2, name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, name), id, ItemFood2, EnumFoods)
                    .build());
            task.logger.printDetailedLog("  Food: " + name + "{id = " + id + ", type = " + name + "}");
            counter.addAndGet(1);
        });
    }

    private void addKnives() {
        ClassName ItemKnife = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ItemKnife");
        String[] materials = new String[] {
                "iron",
                "gold",
                "diamond"
        };
        task.logger.printDetailedLog(" Create knives use " + ItemKnife.canonicalName());

        for (String material : materials) {
            String type = material.toUpperCase(Locale.ROOT);
            String fieldName = "KNIFE_" + type;
            String id = "knife_" + material;
            items.addField(FieldSpec.builder(RegistryObject(ItemKnife), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, type), id, ItemKnife, ItemTier)
                    .build());
            task.logger.printDetailedLog("  Knife: " + fieldName + "{id = " + id + ", type = " + type + "}");
            counter.addAndGet(1);
        }
    }

    private void addBuckets() throws IOException {
        ClassName ModFluids = ClassName.get("cx.rain.mc.forgemod.sinocraft.fluid", "ModFluids");
        ClassName ItemBucket = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.base", "ItemBucket");
        task.logger.printDetailedLog(" Create bucket use " + ItemBucket.canonicalName());

        FieldDeclaration[] fields = Utils.getFields(ModFluids, task);
        for (FieldDeclaration field : fields) {
            int modifiers = field.getModifiers();
            if (!org.eclipse.jdt.core.dom.Modifier.isStatic(modifiers)
                    || !org.eclipse.jdt.core.dom.Modifier.isPublic(modifiers)) {
                continue;
            }
            Type type = field.getType();
            String typeName;
            if (type instanceof ParameterizedType) {
                typeName = ((SimpleType) ((ParameterizedType) type).getType()).getName().getFullyQualifiedName();
            } else if (type instanceof SimpleType) {
                typeName = ((SimpleType) type).getName().getFullyQualifiedName();
            } else {
                continue;
            }
            if (!"RegistryObject".equals(typeName)) {
                continue;
            }
            VariableDeclarationFragment variable = (VariableDeclarationFragment) field.fragments().get(0);
            String fieldName = variable.getName().getIdentifier();
            if (fieldName.endsWith("_FLOWING")) {
                continue;
            }

            String name = "BUCKET_" + fieldName;
            String id = name.toLowerCase(Locale.ROOT);
            items.addField(FieldSpec.builder(RegistryObject(ItemBucket), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, fieldName), id, ItemBucket, ModFluids)
                    .build());
            task.logger.printDetailedLog("  Bucket: " + name + "{id = " + id + ", fluid = " + fieldName + "}");
            counter.addAndGet(1);
        }
    }

    private void addGroupItems() throws IOException {
        task.logger.printDetailedLog(" Create simple items in items.toml");
        Toml items = Utils.getToml("items.toml", task);
        for (FieldDeclaration field : Utils.getFields(ModGroups, task)) {
            int modifiers = field.getModifiers();
            if (!org.eclipse.jdt.core.dom.Modifier.isPublic(modifiers)
                    || !org.eclipse.jdt.core.dom.Modifier.isStatic(modifiers)) {
                continue;
            }
            String group = ((VariableDeclarationFragment) field.fragments().get(0)).getName().getIdentifier();
            if (!items.containsTable(group)) {
                continue;
            }
            Map<String, Object> map = items.getTable(group).toMap();
            map.forEach((name, props) -> {
                List<String> properties = (List<String>) props;
                // REGISTRY.register($S, () -> new $T(new Item.Properties().group($T.[group])).[...])
                String initializer;
                if (properties.isEmpty()) {
                    initializer = String.format("REGISTRY.register($S, () -> new $T(new Item.Properties().group($T.%s)))", group);
                } else {
                    StringBuilder sb = new StringBuilder("REGISTRY.register($S, () -> new $T(new Item.Properties().group($T.")
                            .append(group).append(")");
                    for (String s : properties) {
                        sb.append(".").append(s);
                    }
                    initializer = sb.append("))").toString();
                }
                String fieldName = name.toUpperCase(Locale.ROOT);
                this.items.addField(FieldSpec.builder(RegistryObject(Item), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(initializer, name, Item, ModGroups)
                        .build());
                task.logger.printDetailedLog("  Simple: " + fieldName + "{id = " + name +
                        ", group = " + group +
                        ", properties: " + properties.stream().collect(Collectors.joining(",", "[", "]")) + "}");
                counter.addAndGet(1);
            });
        }
    }

    private void addTypedItem() throws IOException {
        Utils.findClasses("cx.rain.mc.forgemod.sinocraft.item", task.srcPath, false).stream()
                .filter(info -> {
                    String name = info.className;
                    return name.startsWith("Item") && !"ItemKnife".equals(name);
                })
                .forEach(info -> {
                    ClassName itemType = ClassName.get(info.packageName, info.className);
                    task.logger.printDetailedLog(" Create custom items use " + itemType.canonicalName());

                    String registerName = Utils.toLowerName(info.className, 4);
                    String fieldName = Utils.toUpperName(info.className, 4);

                    items.addField(FieldSpec.builder(RegistryObject(itemType), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                            .initializer("REGISTRY.register($S, $T::new)", registerName, itemType)
                            .build());
                    task.logger.printDetailedLog("  Custom: " + fieldName + "{id = " + registerName + "}");
                    counter.addAndGet(1);
                });
    }

    private void checkGeneratedClass() throws IOException {
        ClassName generatedClass = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModItems2");
        ClassName originClass = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModItems");
        Set<String> fieldNames = new HashSet<>();
        StringBuilder sb = new StringBuilder();
        for (FieldDeclaration field : Utils.getFields(generatedClass, task)) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) field.fragments().get(0);
            fieldNames.add(fragment.getName().getIdentifier());
        }
        for (FieldDeclaration field : Utils.getFields(originClass, task)) {
            VariableDeclarationFragment fragment = (VariableDeclarationFragment) field.fragments().get(0);
            String identifier = fragment.getName().getIdentifier();
            if (!fieldNames.remove(identifier)) {
                sb.append("No field in ModItems2: ").append(identifier).append("\n");
            }
        }
        if (!fieldNames.isEmpty()) {
            for (String name : fieldNames) {
                sb.append("No field in ModItems: ").append(name).append("\n");
            }
        }
        if (sb.length() > 0) {
            throw new IOException("Items class has difference: \n" + sb);
        } else {
            System.out.println("ModItems check pass.");
        }
    }
}
