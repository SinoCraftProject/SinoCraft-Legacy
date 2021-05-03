package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockPaperDryingRack;
import cx.rain.mc.forgemod.sinocraft.block.BlockStoneMill;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockLeavesGrowable;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockPlant;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.Direction;
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

    protected void cropsStaged(BlockPlant plant, int stageCount, String name) {
        VariantBlockStateBuilder builder = getVariantBuilder(plant);
        for (int i = 0; i < stageCount; i++) {
            builder.partialState().with(plant.getStage(), i).modelForState()
                    .modelFile(models().crop("block/" + name + "_stage_" + i,
                            modLoc("block/" + name + "_stage_" + i)))
                    .addModel();
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
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.NORTH).
                    end();
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(180).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.SOUTH).
                    end();
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(270).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.WEST).
                    end();
            stoneMill.part().modelFile(models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(90).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.EAST).
                    end();
        }

        MultiPartBlockStateBuilder paperDryingRack = getMultipartBuilder(ModBlocks.PAPER_DRYING_RACK.get());
        for (int i = 0; i <= 4; i++) {
            paperDryingRack.part().modelFile(models().getExistingFile(modLoc("block/paper_drying_rack" + i))).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.NORTH).
                    end();
            paperDryingRack.part().modelFile(models().getExistingFile(modLoc("block/paper_drying_rack" + i))).rotationY(180).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.SOUTH).
                    end();
            paperDryingRack.part().modelFile(models().getExistingFile(modLoc("block/paper_drying_rack" + i))).rotationY(270).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.WEST).
                    end();
            paperDryingRack.part().modelFile(models().getExistingFile(modLoc("block/paper_drying_rack" + i))).rotationY(90).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.EAST).
                    end();
        }

        addCrops();
        addTrees();
        addBuildingBlocks();
        addMachineBlocks();
    }

    private void addCrops() {
        cropsStaged((BlockPlant) ModBlocks.WHITE_RADISH_PLANT.get(), 4, "white_radish_plant");
        cropsStaged((BlockPlant) ModBlocks.SUMMER_RADISH_PLANT.get(), 4, "summer_radish_plant");
        cropsStaged((BlockPlant) ModBlocks.GREEN_RADISH_PLANT.get(), 4, "green_radish_plant");
        cropsStaged((BlockPlant) ModBlocks.GREEN_PEPPER_PLANT.get(), 8, "green_pepper_plant");
        cropsStaged((BlockPlant) ModBlocks.CHILI_PEPPER_PLANT.get(), 8, "chili_pepper_plant");
        cropsStaged((BlockPlant) ModBlocks.CABBAGE_PLANT.get(), 4, "celery_cabbage");
        cropsStaged((BlockPlant) ModBlocks.EGGPLANT_PLANT.get(), 8, "eggplant");
        cropsStaged((BlockPlant) ModBlocks.MILLET_PLANT.get(), 8, "millet");
        cropsStaged((BlockPlant) ModBlocks.SOYBEAN_PLANT.get(), 4, "soybean");
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
        Direction direction = Direction.NORTH;
        int rotates = 0;
        for (int i = 0;i < 4;i++) {
            stoveBuilder.partialState().with(BlockStove.FACING, direction).with(BlockStove.BURNING, false)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/stove_off")))
                    .rotationY(90 * rotates)
                    .addModel();
            stoveBuilder.partialState().with(BlockStove.FACING, direction).with(BlockStove.BURNING, true)
                    .modelForState()
                    .modelFile(models().getExistingFile(modLoc("block/stove_on")))
                    .rotationY(90 * rotates)
                    .addModel();
            direction = direction.rotateY();
            rotates++;
        }

        getVariantBuilder(ModBlocks.POT.get()).partialState().modelForState()
                .modelFile(models().getExistingFile(modLoc("block/pot"))).addModel();
    }
}
