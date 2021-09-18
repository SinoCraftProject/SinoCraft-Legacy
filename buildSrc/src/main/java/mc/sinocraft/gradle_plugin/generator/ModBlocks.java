package mc.sinocraft.gradle_plugin.generator;

import com.squareup.javapoet.*;
import mc.sinocraft.gradle_plugin.ModSourceGenerator;
import mc.sinocraft.gradle_plugin.Templates;
import mc.sinocraft.gradle_plugin.Utils;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.StringLiteral;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

import static mc.sinocraft.gradle_plugin.ClassTypes.ModBlockItems;
import static mc.sinocraft.gradle_plugin.ClassTypes.ModBlocks;
import static mc.sinocraft.gradle_plugin.ClassTypes.ModFluids;
import static mc.sinocraft.gradle_plugin.ClassTypes.ModItems;
import static mc.sinocraft.gradle_plugin.ClassTypes.*;
import static mc.sinocraft.gradle_plugin.Templates.registerWithNew;

public class ModBlocks {

    private TypeSpec.Builder blocks;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger();

    public void create(ModSourceGenerator task) throws IOException {
        blocks = TypeSpec.classBuilder("ModBlocks").addModifiers(Modifier.PUBLIC);
        blocks.addField(FieldSpec.builder(DeferredRegister(Block), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.BLOCKS, $T.MODID)", DeferredRegister, ForgeRegistries, SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin add blocks...");
        counter.set(0);
        addPlants();
        addTrees();
        addMarbles();
        addFluids();
        addTypedBlocks();
        task.logger.printLog("    Added " + counter.get() + " blocks.");
        blocks.addMethod(MethodSpec.constructorBuilder()
                .addModifiers(Modifier.PUBLIC)
                .addParameter(IEventBus, "bus")
                .addCode("$T.getLogger().info($S);\n", SinoCraft, "Registering blocks.")
                .addCode("REGISTRY.register(bus);")
                .build());
        JavaFile.builder("cx.rain.mc.forgemod.sinocraft.block", blocks.build()).build().writeTo(task.srcPath);
    }

    private void addPlants() throws IOException {
        task.logger.printDetailedLog(" Add plants use " + BlockPlant.canonicalName());
        Utils.getEnumValues(PlantType, task).forEach(value -> {
            String identifier = value.getName().getIdentifier();
            String fieldName = identifier + "_PLANT";
            String name = ((StringLiteral) value.arguments().get(0)).getLiteralValue() + "_plant";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockPlant), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format("REGISTRY.register($S, () -> $T.create($T.%s))", identifier), name, BlockPlant, PlantType)
                    .build());
            task.logger.printDetailedLog("  Plant: " + fieldName + "{id = " + name + ", type = " + identifier + "}");
            counter.addAndGet(1);
        });
    }

    private void addTrees() throws IOException {
        task.logger.printDetailedLog(" Add trees: log*4, plank, leaf, sapling.");
        Utils.getEnumValues(TreeType, task).forEach(value -> {
            String identifier = value.getName().getIdentifier();
            String id = identifier.toLowerCase(Locale.ROOT);
            task.logger.printDetailedLog("  Tree " + identifier);
            // log
            String logName = identifier + "_LOG_STRIPPED";
            String logId = id + "_log_stripped";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLog), logName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(identifier), logId, BlockLog, TreeType)
                    .build());
            task.logger.printDetailedLog("   Log: " + logName + "{id = " + logId + "}");
            counter.addAndGet(1);
            logName = identifier + "_LOG";
            logId = id + "_log";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLogStrippable), logName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(identifier, identifier + "_LOG_STRIPPED"), logId, BlockLogStrippable, TreeType, ModBlocks)
                    .build());
            task.logger.printDetailedLog("   Log: " + logName + "{id = " + logId + "}");
            counter.addAndGet(1);
            logName = identifier + "_LOG_STRIPPED_BARK";
            logId = id + "_log_stripped_bark";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLog), logName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(identifier), logId, BlockLog, TreeType)
                    .build());
            task.logger.printDetailedLog("   Log: " + logName + "{id = " + logId + "}");
            counter.addAndGet(1);
            logName = identifier + "_LOG_BARK";
            logId = id + "_log_bark";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLogStrippable), logName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(identifier, identifier + "_LOG_STRIPPED_BARK"), logId, BlockLogStrippable, TreeType, ModBlocks)
                    .build());
            task.logger.printDetailedLog("   Log: " + logName + "{id = " + logId + "}");
            counter.addAndGet(1);
            // plank
            String obj = registerWithNew(identifier);
            blocks.addField(FieldSpec.builder(RegistryObject(BlockPlank), identifier + "_PLANK", Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(obj, id + "_plank", BlockPlank, TreeType).build());
            task.logger.printDetailedLog("   Plank: " + identifier + "_PLANK{id = " + id + "_plank}");
            counter.addAndGet(1);
            // leaf
            Object fruit = value.arguments().get(2);
            if (fruit instanceof QualifiedName) {
                ClassName items;
                switch (((QualifiedName) fruit).getQualifier().getFullyQualifiedName()) {
                    case "ModItems": items = ModItems;break;
                    case "ModBlockItems": items = ModBlockItems;break;
                    default: items = Items;break;
                }
                String fruitName = ((QualifiedName) fruit).getName().getIdentifier();
                blocks.addField(FieldSpec.builder(RegistryObject(BlockLeavesGrowable), identifier + "_LEAVES", Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(registerWithNew(identifier, fruitName), id + "_leaves", BlockLeavesGrowable, TreeType, items).build());
                task.logger.printDetailedLog("   Leaf: " + identifier + "_LEAVES{id = " + id + "_leaves, fruit = " + items + "." + fruitName + "}");
            } else {
                blocks.addField(FieldSpec.builder(RegistryObject(BlockLeaves), identifier + "_LEAVES", Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(obj, id + "_leaves", BlockLeaves, TreeType).build());
                task.logger.printDetailedLog("   Leaf: " + identifier + "_LEAVES{id = " + id + "_leaves}");
            }
            counter.addAndGet(1);
            // sapling
            String saplingInitializer = String.format("REGISTRY.register($S, () -> new $T($T.%s, new $T()))", identifier);
            ClassName treeClass = ClassName.get("cx.rain.mc.forgemod.sinocraft.world.tree", "Tree" + Utils.toCamelCase(identifier, false));
            blocks.addField(FieldSpec.builder(RegistryObject(BlockSapling), identifier + "_SAPLING", Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(saplingInitializer, id + "_sapling", BlockSapling, TreeType, treeClass).build());
            task.logger.printDetailedLog("   Sapling: " + identifier + "_SAPLING{id = " + id + "_sapling, tree = " + treeClass + "}");
            counter.addAndGet(1);
        });
    }

    private void addMarbles() throws IOException {
        task.logger.printDetailedLog(" Add marbles use " + BlockMarble.canonicalName());

        Utils.getEnumValues(MarbleType, task).forEach(value -> {
            String name = value.getName().getIdentifier();
            String id = name.toLowerCase(Locale.ROOT) + "_marble";
            String fieldName = name + "_MARBLE";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockMarble), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(registerWithNew(name), id, BlockMarble, MarbleType)
                    .build());
            task.logger.printDetailedLog("  Marble: " + fieldName + "{id = " + id + ", type = " + name + "}");
            counter.addAndGet(1);
        });
    }

    private void addFluids() throws IOException {
        String template = "REGISTRY.register($S, () -> new $T($T.%s, $T.Properties.create($T.WATER)))";
        task.logger.printDetailedLog(" Add fluids");

        for (String fluid : mc.sinocraft.gradle_plugin.generator.ModFluids.getAllFluids(task)) {
            String name = fluid + "_BLOCK";
            String id = name.toLowerCase(Locale.ROOT);
            blocks.addField(FieldSpec.builder(RegistryObject(FlowingFluidBlock), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(template, fluid), id, FlowingFluidBlock, ModFluids, Block, Material)
                    .build());
            task.logger.printDetailedLog("  Fluid: " + name + "{id = " + id + ", fluid = " + fluid + "}");
            counter.addAndGet(1);
        }
    }

    private void addTypedBlocks() throws IOException {
        task.logger.printDetailedLog(" Add custom blocks");
        Utils.forClasses("cx.rain.mc.forgemod.sinocraft.block", task.srcPath, false, (packageName, className, filePath) -> {
            if (className.startsWith("Block")) {
                ClassName blockType = ClassName.get(packageName, className);

                String registerName = Utils.toLowerName(className, 5);
                String fieldName = Utils.toUpperName(className, 5);

                blocks.addField(FieldSpec.builder(RegistryObject(blockType), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                        .initializer("REGISTRY.register($S, $T::new)", registerName, blockType)
                        .build());
                task.logger.printDetailedLog("  Custom: " + fieldName + "{id = " + registerName + ", type = " + blockType.canonicalName() + "}");
                counter.addAndGet(1);
            }
        });
    }
}
