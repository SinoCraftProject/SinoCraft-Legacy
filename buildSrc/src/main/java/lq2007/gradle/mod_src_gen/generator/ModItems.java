package lq2007.gradle.mod_src_gen.generator;

import com.moandjiezana.toml.Toml;
import com.squareup.javapoet.*;
import lq2007.gradle.mod_src_gen.ClassFileInfo;
import lq2007.gradle.mod_src_gen.Utils;
import org.eclipse.jdt.core.dom.*;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static lq2007.gradle.mod_src_gen.ClassTypes.*;

public class ModItems implements Predicate<ClassFileInfo>, Consumer<ClassFileInfo> {

    private static final String TEMPLATE_FIELD_ITEM = "REGISTRY.register($S, () -> new $T($T.%s))";

    private TypeSpec.Builder items;

    public void create(Path srcPath, Path resPath, ASTParser parser) throws IOException {
        items = TypeSpec.classBuilder("ModItems2");
        items.addField(FieldSpec.builder(DeferredRegister(Item), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.ITEMS, $T.MODID)", DeferredRegister, ForgeRegistries, SinoCraft)
                .build());
        addSeeds(srcPath, parser);
        addFoods(srcPath, parser);
        addKnives();
        addBuckets(srcPath, parser);
        addGroupItems(srcPath, resPath, parser);
        Utils.findClasses("cx.rain.mc.forgemod.sinocraft.item", srcPath).stream()
                .filter(this)
                .forEach(this);
        items.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(IEventBus, "bus")
                .addCode("$T.getLogger().info($S);", SinoCraft, "Registering items.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.item", items.build()).build().writeTo(srcPath);
    }

    private void addSeeds(Path srcPath, ASTParser parser) throws IOException {
        ClassName ItemSeed = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.base", "ItemSeed");
        ClassName PlantType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "PlantType");

        Utils.getEnumValues(PlantType, srcPath, parser, "WHITE_RADISH", "SUMMER_RADISH", "GREEN_RADISH", "SOYBEAN").forEach(value -> {
            String identifier = value.getName().getIdentifier();
            String fieldName = identifier + "_SEED";
            String name = ((StringLiteral) value.arguments().get(0)).getLiteralValue() + "_seed";
            items.addField(FieldSpec.builder(RegistryObject(ItemSeed), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, identifier), name, ItemSeed, PlantType)
                    .build());
        });
        items.addField(FieldSpec.builder(RegistryObject(ItemSeed), "SOYBEAN", Modifier.PUBLIC, Modifier.STATIC)
                .initializer(String.format(TEMPLATE_FIELD_ITEM, "SOYBEAN"), "soybean", ItemSeed, PlantType)
                .build());
    }

    private void addGroupItems(Path srcPath, Path resPath, ASTParser parser) throws IOException {
        Path itemsFile = resPath.resolve("items.toml");
        if (!Files.isRegularFile(itemsFile)) {
            return;
        }
        Toml items = new Toml().read(itemsFile.toFile());
        for (FieldDeclaration field : Utils.getFields(ModGroups, srcPath, parser)) {
            String group = ((VariableDeclarationFragment) field.fragments().get(0)).getName().getIdentifier();
            if (!items.containsTable(group)) {
                continue;
            }
            items.getTable(group).toMap().forEach((name, props) -> {
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
            });
        }
    }

    private void addFoods(Path srcPath, ASTParser parser) throws IOException {
        ClassName EnumFoods = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.food", "EnumFoods");
        ClassName ItemFood2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.food", "ItemFood2");

        Utils.getEnumValues(EnumFoods, srcPath, parser).forEach(value -> {
            String name = value.getName().getIdentifier();
            String id = name.toLowerCase(Locale.ROOT);
            items.addField(FieldSpec.builder(ItemFood2, name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, name), id, ItemFood2, EnumFoods)
                    .build());
        });
    }

    private void addKnives() {
        ClassName ItemKnife = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ItemKnife");
        String[] materials = new String[] {
                "iron",
                "gold",
                "diamond"
        };
        for (String material : materials) {
            String type = material.toUpperCase(Locale.ROOT);
            String fieldName = "KNIFE_" + type;
            String id = "knife_" + material;
            items.addField(FieldSpec.builder(RegistryObject(ItemKnife), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD_ITEM, type), id, ItemKnife, ItemTier)
                    .build());
        }
    }

    private void addBuckets(Path srcPath, ASTParser parser) throws IOException {
        ClassName ModFluids = ClassName.get("cx.rain.mc.forgemod.sinocraft.fluid", "ModFluids");
        ClassName ItemBucket = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.base", "ItemBucket");

        FieldDeclaration[] fields = Utils.getFields(ModFluids, srcPath, parser);
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
        }
    }

    @Override
    public void accept(ClassFileInfo info) {
        String registerName = Utils.toLowerName(info.className, 4);
//        items.addField(typeName.toUpperName(4))
//                .setReturnType(new GenericClassName(registryObject, typeName.getClassName()))
//                .setValue(String.format(DEFAULT_ITEM_TEMPLATE, registerName, typeName.getClassName()));
    }

    @Override
    public boolean test(ClassFileInfo info) {
        String name = info.className;
        return name.startsWith("Item") && !"ItemKnife".equals(name);
    }
}
