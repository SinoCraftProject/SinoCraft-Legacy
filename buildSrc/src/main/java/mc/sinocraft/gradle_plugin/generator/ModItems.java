package mc.sinocraft.gradle_plugin.generator;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.*;
import mc.sinocraft.gradle_plugin.ClassTypes;
import mc.sinocraft.gradle_plugin.ModSourceGenerator;
import mc.sinocraft.gradle_plugin.Utils;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import static mc.sinocraft.gradle_plugin.Templates.registerWithNew;

public class ModItems {
    
    private TypeSpec.Builder items;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger(0);

    public void create(ModSourceGenerator task) throws IOException {
        items = TypeSpec.classBuilder("ModItems").addModifiers(Modifier.PUBLIC);
        items.addField(FieldSpec.builder(ClassTypes.DeferredRegister(ClassTypes.Item), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.ITEMS, $T.MODID)", ClassTypes.DeferredRegister, ClassTypes.ForgeRegistries, ClassTypes.SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin add items...");
        counter.set(0);
        addSeeds();
        addFoods();
        addKnives();
        addBuckets();
        addGroupItems();
        addTypedItems();
        task.logger.printLog("    Added " + counter.get() + " items.");
        items.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(ClassTypes.IEventBus, "bus")
                .addCode("$T.getLogger().info($S);\n", ClassTypes.SinoCraft, "Registering items.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.item", items.build()).build().writeTo(task.srcPath);
        Map<String, String> equalsClass = new HashMap<>();
        equalsClass.put("BucketItem", "ItemBucket");
        equalsClass.put("ItemFood", "ItemFood2");
    }

    private void addSeeds() throws IOException {
        task.logger.printDetailedLog(" Add seeds use " + ClassTypes.ItemSeed.canonicalName());

        Utils.getEnumValues(ClassTypes.PlantType, task, "WHITE_RADISH", "SUMMER_RADISH", "GREEN_RADISH", "SOYBEAN").forEach(value -> {
            String identifier = value.getName().getIdentifier();
            String fieldName = identifier + "_SEED";
            String name = ((StringLiteral) value.arguments().get(0)).getLiteralValue() + "_seed";
            items.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.ItemSeed), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(identifier), name, ClassTypes.ItemSeed, ClassTypes.PlantType)
                    .build());
            task.logger.printDetailedLog("  Seed: " + fieldName + "{id = " + name + ", type = " + identifier + "}");
            counter.addAndGet(1);
        });
        items.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.ItemSeed), "SOYBEAN", Modifier.PUBLIC, Modifier.STATIC)
                .initializer(registerWithNew("SOYBEAN"), "soybean", ClassTypes.ItemSeed, ClassTypes.PlantType)
                .build());
        task.logger.printDetailedLog("  Seed: SOYBEAN{id = soybean, type = SOYBEAN}");
        counter.addAndGet(1);
    }

    private void addFoods() throws IOException {
        task.logger.printDetailedLog(" Add foods use " + ClassTypes.ItemFood.canonicalName());

        Utils.getEnumValues(ClassTypes.EnumFoods, task).forEach(value -> {
            String name = value.getName().getIdentifier();
            String id = name.toLowerCase(Locale.ROOT);
            items.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.ItemFood), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(name), id, ClassTypes.ItemFood, ClassTypes.EnumFoods)
                    .build());
            task.logger.printDetailedLog("  Food: " + name + "{id = " + id + ", type = " + name + "}");
            counter.addAndGet(1);
        });
    }

    private void addKnives() {
        String[] materials = new String[] {
                "iron",
                "gold",
                "diamond"
        };
        task.logger.printDetailedLog(" Add knives use " + ClassTypes.ItemKnife.canonicalName());

        for (String material : materials) {
            String type = material.toUpperCase(Locale.ROOT);
            String fieldName = "KNIFE_" + type;
            String id = "knife_" + material;
            items.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.ItemKnife), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(type), id, ClassTypes.ItemKnife, ClassTypes.ItemTier)
                    .build());
            task.logger.printDetailedLog("  Knife: " + fieldName + "{id = " + id + ", type = " + type + "}");
            counter.addAndGet(1);
        }
    }

    private void addBuckets() throws IOException {
        task.logger.printDetailedLog(" Add bucket use " + ClassTypes.ItemBucket.canonicalName());

        for (String fluid : ModFluids.getAllFluids(task)) {
            String name = "BUCKET_" + fluid;
            String id = name.toLowerCase(Locale.ROOT);
            items.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.ItemBucket), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(fluid), id, ClassTypes.ItemBucket, ClassTypes.ModFluids)
                    .build());
            task.logger.printDetailedLog("  Bucket: " + name + "{id = " + id + ", fluid = " + fluid + "}");
            counter.addAndGet(1);
        }
    }

    private void addGroupItems() throws IOException {
        task.logger.printDetailedLog(" Add simple items in items.toml");
        Toml items = Utils.getToml("items.toml", task);
        for (FieldDeclaration field : Utils.getFields(ClassTypes.ModGroups, task)) {
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
                this.items.addField(FieldSpec.builder(ClassTypes.RegistryObject(ClassTypes.Item), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(initializer, name, ClassTypes.Item, ClassTypes.ModGroups)
                        .build());
                task.logger.printDetailedLog("  Simple: " + fieldName + "{id = " + name +
                        ", group = " + group +
                        ", properties: " + properties.stream().collect(Collectors.joining(",", "[", "]")) + "}");
                counter.addAndGet(1);
            });
        }
    }

    private void addTypedItems() throws IOException {
        task.logger.printDetailedLog(" Add custom items");
        Utils.forClasses("cx.rain.mc.forgemod.sinocraft.item", task.srcPath, false, (packageName, className, filePath) -> {
            if (className.startsWith("Item") && !"ItemKnife".equals(className)) {
                ClassName itemType = ClassName.get(packageName, className);

                String registerName = Utils.toLowerName(className, 4);
                String fieldName = Utils.toUpperName(className, 4);

                items.addField(FieldSpec.builder(ClassTypes.RegistryObject(itemType), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer("REGISTRY.register($S, $T::new)", registerName, itemType)
                        .build());
                task.logger.printDetailedLog("  Custom: " + fieldName + "{id = " + registerName + ", type = " + itemType.canonicalName() + "}");
                counter.addAndGet(1);
            }
        });
    }
}
