package lq2007.gradle.mod_src_gen.generator;

import com.squareup.javapoet.*;
import lq2007.gradle.mod_src_gen.Checker;
import lq2007.gradle.mod_src_gen.ModSourceGenerator;
import lq2007.gradle.mod_src_gen.Utils;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.QualifiedName;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;

import javax.lang.model.element.Modifier;
import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import static lq2007.gradle.mod_src_gen.ClassTypes.ModBlocks;
import static lq2007.gradle.mod_src_gen.ClassTypes.ModItems;
import static lq2007.gradle.mod_src_gen.ClassTypes.*;
import static lq2007.gradle.mod_src_gen.Templates.TEMPLATE_FIELD;

public class ModBlocks {

    private TypeSpec.Builder blocks;
    private ModSourceGenerator task;
    private final AtomicInteger counter = new AtomicInteger();

    public void create(ModSourceGenerator task) throws IOException {
        blocks = TypeSpec.classBuilder("ModBlock2");
        blocks.addField(FieldSpec.builder(DeferredRegister(Block), "REGISTRY", Modifier.PUBLIC, Modifier.STATIC, Modifier.FINAL)
                .initializer("$T.create($T.BLOCKS, $T.MODID)", DeferredRegister, ForgeRegistries, SinoCraft)
                .build());
        this.task = task;

        task.logger.printLog("Begin create blocks...");
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
        Checker.checkRegistryObjects(task, ModBlocks, ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlock2"), Collections.emptyMap());
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
        ClassName BlockLeaves = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockLeaves");
        ClassName BlockLeavesGrowable = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockLeavesGrowable");
        ClassName BlockLog = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockLog");
        ClassName BlockPlank = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockPlank");
        ClassName BlockSapling = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockSapling");
        ClassName TreeType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "TreeType");
        task.logger.printDetailedLog(" Add trees: log*4, plank, leaf, sapling.");

        Utils.getEnumValues(TreeType, task).forEach(value -> {
            String identifier = value.getName().getIdentifier();
            String id = identifier.toLowerCase(Locale.ROOT);
            task.logger.printDetailedLog("  Tree " + identifier);
            // log
            String obj = String.format(TEMPLATE_FIELD, identifier);
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLog), identifier + "_LOG", Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(obj, id + "_log", BlockLog, TreeType).build());
            task.logger.printDetailedLog("   Log: " + identifier + "_LOG{id = " + id + "_log}");
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLog), identifier + "_LOG_BARK", Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(obj, id + "_log_bark", BlockLog, TreeType).build());
            task.logger.printDetailedLog("   Log: " + identifier + "_LOG_BARK{id = " + id + "_log_bark}");
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLog), identifier + "_LOG_STRIPPED", Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(obj, id + "_log_stripped", BlockLog, TreeType).build());
            task.logger.printDetailedLog("   Log: " + identifier + "_LOG_STRIPPED{id = " + id + "_log_stripped}");
            blocks.addField(FieldSpec.builder(RegistryObject(BlockLog), identifier + "_LOG_STRIPPED_BARK", Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(obj, id + "_log_stripped_bark", BlockLog, TreeType).build());
            task.logger.printDetailedLog("   Log: " + identifier + "_LOG_STRIPPED_BARK{id = " + id + "_log_stripped_bark}");
            counter.addAndGet(4);
            // plank
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
                String initializer = String.format("REGISTRY.register($S, () -> new $T($T.%s, $T.%s))", identifier, fruitName);
                blocks.addField(FieldSpec.builder(RegistryObject(BlockLeavesGrowable), identifier + "_LEAVES", Modifier.PUBLIC, Modifier.STATIC)
                        .initializer(initializer, id + "_leaves", BlockLeavesGrowable, TreeType, items).build());
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
        ClassName MarbleType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "MarbleType");
        ClassName BlockMarble = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockMarble");
        task.logger.printDetailedLog(" Add marbles use " + BlockMarble.canonicalName());

        Utils.getEnumValues(MarbleType, task).forEach(value -> {
            String name = value.getName().getIdentifier();
            String id = name.toLowerCase(Locale.ROOT) + "_marble";
            String fieldName = name + "_MARBLE";
            blocks.addField(FieldSpec.builder(RegistryObject(BlockMarble), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(TEMPLATE_FIELD, name), id, BlockMarble, MarbleType)
                    .build());
            task.logger.printDetailedLog("  Marble: " + fieldName + "{id = " + id + ", type = " + name + "}");
            counter.addAndGet(1);
        });
    }

    private void addFluids() throws IOException {
        String template = "REGISTRY.register($S, () -> new $T($T.%s, $T.Properties.create($T.WATER)))";
        ClassName FlowingFluidBlock = ClassName.get("net.minecraft.block", "FlowingFluidBlock");
        task.logger.printDetailedLog(" Add fluids");

        for (String fluid : ModFluids.getAllFluids(task)) {
            String name = fluid + "_BLOCK";
            String id = name.toLowerCase(Locale.ROOT);
            blocks.addField(FieldSpec.builder(RegistryObject(FlowingFluidBlock), name, Modifier.PUBLIC, Modifier.STATIC)
                    .initializer(String.format(template, fluid), id, FlowingFluidBlock, ModFluids.ModFluids, Block, Material)
                    .build());
            task.logger.printDetailedLog("  Fluid: " + name + "{id = " + id + ", fluid = " + fluid + "}");
            counter.addAndGet(1);
        }
    }

    private void addTypedBlocks() throws IOException {
        task.logger.printDetailedLog(" Add custom blocks");
        Utils.findClasses("cx.rain.mc.forgemod.sinocraft.block", task.srcPath, false).stream()
                .filter(info -> info.className.startsWith("Block"))
                .forEach(info -> {
                    ClassName blockType = ClassName.get(info.packageName, info.className);

                    String registerName = Utils.toLowerName(info.className, 5);
                    String fieldName = Utils.toUpperName(info.className, 5);

                    blocks.addField(FieldSpec.builder(RegistryObject(blockType), fieldName, Modifier.PUBLIC, Modifier.STATIC)
                            .initializer("REGISTRY.register($S, $T::new)", registerName, blockType)
                            .build());
                    task.logger.printDetailedLog("  Custom: " + fieldName + "{id = " + registerName + ", type = " + blockType.canonicalName() + "}");
                    counter.addAndGet(1);
                });
    }
}
