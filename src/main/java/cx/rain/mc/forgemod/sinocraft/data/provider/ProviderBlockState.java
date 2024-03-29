package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.IPlantType;
import cx.rain.mc.forgemod.sinocraft.block.*;
import cx.rain.mc.forgemod.sinocraft.block.tree.BlockLeavesGrowable;
import cx.rain.mc.forgemod.sinocraft.block.plant.BlockPlant;
import cx.rain.mc.forgemod.sinocraft.block.plant.BlockPlantMulti;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.*;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderBlockState extends BlockStateProvider {
    public ProviderBlockState(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SinoCraft.MODID, exFileHelper);
    }

    protected void simpleBlock(Block block, String texture) {
        simpleBlock(block, models().cubeAll("block/" + texture, modLoc("block/" + texture)));
    }

    protected void crossBlock(Block block, String texture) {
        simpleBlock(block, models().cross("block/" + texture, modLoc("block/" + texture)));
    }

    protected void logBlock(Block block, String side, String end) {
        if (!(block instanceof RotatedPillarBlock)) {
            throw new RuntimeException("Block is not rotatable.");
        }

        axisBlock((RotatedPillarBlock) block, "block/" + side, "block/" + end);
    }

    protected void axisBlock(RotatedPillarBlock block, String side, String end) {
        ModelBuilder<BlockModelBuilder> modelBuilder =
                models().cubeColumn(block.getRegistryName().getPath(), modLoc(side), modLoc(end));
        getVariantBuilder(block)
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.X)
                .modelForState().modelFile(modelBuilder).rotationX(90).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Y)
                .modelForState().modelFile(modelBuilder).rotationY(90).addModel()
                .partialState().with(RotatedPillarBlock.AXIS, Direction.Axis.Z)
                .modelForState().modelFile(modelBuilder).rotationX(90).addModel();

    }

    protected void barkBlock(Block block, String all) {
        simpleBlock(block, models().cubeAll(block.getRegistryName().getPath(), modLoc("block/" + all)));
    }

    protected void cropsStaged(BlockPlant plant) {
        cropsStaged(plant, plant.getType().getName());
    }

    protected void cropsStaged(BlockPlant plant, String name) {
        VariantBlockStateBuilder builder = getVariantBuilder(plant);
        IPlantType type = plant.getType();
        if (type.getMaxHeight() > 1) {
            for (int i = 0; i <= plant.getMaxAge(); i++) {
                builder.partialState().with(plant.getAgeProperty(), i).with(BlockPlantMulti.IS_TOP, true).modelForState()
                        .modelFile(models().crop("block/" + name + "_stage_top_" + i,
                                modLoc("block/" + name + "_stage_top_" + i)))
                        .addModel();
                builder.partialState().with(plant.getAgeProperty(), i).with(BlockPlantMulti.IS_TOP, false).modelForState()
                        .modelFile(models().crop("block/" + name + "_stage_bottom_" + i,
                                modLoc("block/" + name + "_stage_bottom_" + i)))
                        .addModel();
            }
        } else {
            for (int i = 0; i <= plant.getMaxAge(); i++) {
                builder.partialState().with(plant.getAgeProperty(), i).modelForState()
                        .modelFile(models().crop("block/" + name + "_stage_" + i,
                                modLoc("block/" + name + "_stage_" + i)))
                        .addModel();
            }
        }
    }

    @Override
    protected void registerStatesAndModels() {
        simpleBlock(ModBlocks.VAT.get(), models().getExistingFile(modLoc("block/vat")));

        MultiPartBlockStateBuilder stoneMill = getMultipartBuilder(ModBlocks.STONE_MILL.get());
        for (int i = 1; i <= 16; i++) {
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.HORIZONTAL_FACING, Direction.NORTH).
                    end();
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(180).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.HORIZONTAL_FACING, Direction.SOUTH).
                    end();
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(270).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.HORIZONTAL_FACING, Direction.WEST).
                    end();
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(90).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.HORIZONTAL_FACING, Direction.EAST).
                    end();
        }

        addCrops();
        addTrees();
        addBuildingBlocks();
        addMachineBlocks();
        addOtherBlock();

        getVariantBuilder(ModBlocks.WOOD_PULP_BLOCK.get()).partialState().modelForState()
                .modelFile(models().getBuilder("block/wood_pump").texture("particle", modLoc("block/wood_pulp_still")))
                .addModel();

    }

    private void addCrops() {
        cropsStaged(ModBlocks.WHITE_RADISH_PLANT.get(), "white_radish_plant");
        cropsStaged(ModBlocks.SUMMER_RADISH_PLANT.get(), "summer_radish_plant");
        cropsStaged(ModBlocks.GREEN_RADISH_PLANT.get(), "green_radish_plant");
        cropsStaged(ModBlocks.GREEN_PEPPER_PLANT.get(), "green_pepper_plant");
        cropsStaged(ModBlocks.CHILI_PEPPER_PLANT.get(), "chili_pepper_plant");
        cropsStaged(ModBlocks.CABBAGE_PLANT.get(), "celery_cabbage");
        cropsStaged(ModBlocks.EGGPLANT_PLANT.get(), "eggplant");
        cropsStaged(ModBlocks.MILLET_PLANT.get(), "millet");
        cropsStaged(ModBlocks.SOYBEAN_PLANT.get(), "soybean");
        cropsStaged(ModBlocks.RICE_PLANT.get());
        cropsStaged(ModBlocks.SORGHUM_PLANT.get());
    }

    private void addTrees() {
        logBlock(ModBlocks.PEACH_LOG.get(), "peach_log_side", "peach_log_end");
        barkBlock(ModBlocks.PEACH_LOG_BARK.get(), "peach_log_side");
        logBlock(ModBlocks.PEACH_LOG_STRIPPED.get(), "peach_log_stripped", "peach_log_end");
        barkBlock(ModBlocks.PEACH_LOG_STRIPPED_BARK.get(), "peach_log_stripped");
        simpleBlock(ModBlocks.PEACH_PLANK.get(), "peach_plank");
        getVariantBuilder(ModBlocks.PEACH_LEAVES.get())
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), false).modelForState().modelFile(models().cubeAll("peach_leaves", modLoc("block/peach_leaves"))).addModel()
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), true).modelForState().modelFile(models().cubeAll("peach_leaves_mature", modLoc("block/peach_leaves_mature"))).addModel();
        crossBlock(ModBlocks.PEACH_SAPLING.get(), "peach_sapling");

        logBlock(ModBlocks.WALNUT_LOG.get(), "walnut_log_side", "walnut_log_end");
        barkBlock(ModBlocks.WALNUT_LOG_BARK.get(), "walnut_log_side");
        logBlock(ModBlocks.WALNUT_LOG_STRIPPED.get(), "walnut_log_stripped", "walnut_log_end");
        barkBlock(ModBlocks.WALNUT_LOG_STRIPPED_BARK.get(), "walnut_log_stripped");
        simpleBlock(ModBlocks.WALNUT_PLANK.get(), "walnut_plank");
        simpleBlock(ModBlocks.WALNUT_LEAVES.get(), "walnut_leaves");
        crossBlock(ModBlocks.WALNUT_SAPLING.get(), "walnut_sapling");

        logBlock(ModBlocks.MULBERRY_LOG.get(), "mulberry_log_side", "mulberry_log_end");
        barkBlock(ModBlocks.MULBERRY_LOG_BARK.get(), "mulberry_log_side");
        logBlock(ModBlocks.MULBERRY_LOG_STRIPPED.get(), "mulberry_log_stripped", "mulberry_log_end");
        barkBlock(ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get(), "mulberry_log_stripped");
        simpleBlock(ModBlocks.MULBERRY_PLANK.get(), "mulberry_plank");
        simpleBlock(ModBlocks.MULBERRY_LEAVES.get(), "mulberry_leaves");
        crossBlock(ModBlocks.MULBERRY_SAPLING.get(), "mulberry_sapling");

        logBlock(ModBlocks.PLUM_LOG.get(), "plum_log_side", "plum_log_end");
        barkBlock(ModBlocks.PLUM_LOG_BARK.get(), "plum_log_side");
        logBlock(ModBlocks.PLUM_LOG_STRIPPED.get(), "plum_log_stripped", "plum_log_end");
        barkBlock(ModBlocks.PLUM_LOG_STRIPPED_BARK.get(), "plum_log_stripped");
        simpleBlock(ModBlocks.PLUM_PLANK.get(), "plum_plank");
        simpleBlock(ModBlocks.PLUM_LEAVES.get(), "plum_leaves");
        crossBlock(ModBlocks.PLUM_SAPLING.get(), "plum_sapling");
    }

    private void addBuildingBlocks() {
        simpleBlock(ModBlocks.WHITE_MARBLE.get());
        simpleBlock(ModBlocks.RED_MARBLE.get());
        simpleBlock(ModBlocks.BLACK_MARBLE.get());
    }

    private void addMachineBlocks() {
        // Stove
        VariantBlockStateBuilder stoveBuilder = getVariantBuilder(ModBlocks.STOVE.get());
        Direction stoveDirection = Direction.NORTH;
        for (int i = 0; i < 4; i++) {
            stoveBuilder.partialState().with(BlockStove.HORIZONTAL_FACING, stoveDirection).with(BlockStove.BURNING, false)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/stove_off")))
                    .rotationY(90 * i)
                    .addModel();
            stoveBuilder.partialState().with(BlockStove.HORIZONTAL_FACING, stoveDirection).with(BlockStove.BURNING, true)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/stove_on")))
                    .rotationY(90 * i)
                    .addModel();
            stoveDirection = stoveDirection.rotateY();
        }

        getVariantBuilder(ModBlocks.POT.get()).partialState().modelForState()
                .modelFile(models().getExistingFile(modLoc("block/pot"))).addModel();

        MultiPartBlockStateBuilder paperDryingRackBuilder = getMultipartBuilder(ModBlocks.PAPER_DRYING_RACK.get());
        for (int i = 0; i <= 4; i++) {
            Direction paperDryingRackBuilderDirection = Direction.NORTH;
            for (int j = 0; j < 4; j++) {
                paperDryingRackBuilder.part().modelFile(models().getExistingFile(modLoc("block/paper_drying_rack" + i)))
                        .rotationY(90 * j)
                        .addModel()
                        .condition(BlockPaperDryingRack.STATE, i)
                        .condition(BlockPaperDryingRack.HORIZONTAL_FACING, paperDryingRackBuilderDirection)
                        .end();
                paperDryingRackBuilderDirection = paperDryingRackBuilderDirection.rotateY();
            }
        }

        MultiPartBlockStateBuilder bellowsBuilder = getMultipartBuilder(ModBlocks.BELLOWS.get());
        for (int i = 0; i <= 3; i++) {
            Direction bellowsBuilderDirection = Direction.EAST;
            for (int j = 0; j < 4; j++) {
                bellowsBuilder.part().modelFile(models().getExistingFile(modLoc("block/bellows" + i)))
                        .rotationY(90 * j)
                        .addModel()
                        .condition(BlockBellows.STATE, i)
                        .condition(BlockBellows.HORIZONTAL_FACING, bellowsBuilderDirection)
                        .end();
                bellowsBuilderDirection = bellowsBuilderDirection.rotateY();
            }
        }
    }

    private void addOtherBlock() {
        simpleBlock(ModBlocks.TEA_TABLE.get(), models().getExistingFile(new ResourceLocation("air")));
        getVariantBuilder(ModBlocks.TEACUP.get()).partialState()
                .with(BlockTeacup.WITH_TEA, false).modelForState().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/teacup")))
                .addModel().partialState()
                .with(BlockTeacup.WITH_TEA, true).modelForState().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/teacup_with_tea")))
                .addModel();
        getVariantBuilder(ModBlocks.TEAPOT.get()).partialState()
                .with(BlockTeapot.FLUID, 0).modelForState().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/teapot")))
                .addModel().partialState()
                .with(BlockTeapot.FLUID, 1).modelForState().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/teapot_without_lid")))
                .addModel().partialState()
                .with(BlockTeapot.FLUID, 2).modelForState().modelFile(new ModelFile.UncheckedModelFile(modLoc("block/teapot_without_lid_with_tea")))
                .addModel();
    }
}
