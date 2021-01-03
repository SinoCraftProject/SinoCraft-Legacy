package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IMachine;
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

    protected void blockTreeLog(Block block, String side, String end) {
        axisBlock((RotatedPillarBlock) block, modLoc("block/" + side), modLoc("block/" + end));
    }

    protected void blocksTreeTrunkAndPlank(Block trunk, Block trunkStripped, Block trunkBark, Block trunkStrippedBark,
                                           Block plankBlock, String side, String stripped, String end, String plank) {
        blockTreeLog(trunk, side, end);
        blockTreeLog(trunkStripped, stripped, end);
        simpleBlock(trunkBark, side);
        simpleBlock(trunkStrippedBark, stripped);
        simpleBlock(plankBlock, plank);
    }

    protected void cropsStage(BlockPlant plant, int stageCount, String name) {
        VariantBlockStateBuilder builder = getVariantBuilder(plant);
        for (int i = 0; i < stageCount; i++) {
            builder.partialState().with(plant.getStage(), i).modelForState().modelFile(
                    models().crop("block/" + name + "_" + i, modLoc("block/" + name + "_" + i))).addModel();
        }
    }

    @Override
    protected void registerStatesAndModels() {
        blocksTreeTrunkAndPlank(ModBlocks.PEACH_LOG.get(), ModBlocks.PEACH_LOG_STRIPPED.get(), ModBlocks.PEACH_LOG_BARK.get(), ModBlocks.PEACH_LOG_STRIPPED_BARK.get(), ModBlocks.PEACH_PLANK.get(), "peach_log_side", "peach_log_stripped", "peach_log_end", "peach_plank");
        getVariantBuilder(ModBlocks.PEACH_LEAVES.get())
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), false).modelForState().modelFile(models().cubeAll("peach_leaves", modLoc("block/peach_leaves"))).addModel()
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), true).modelForState().modelFile(models().cubeAll("peach_leaves_mature", modLoc("block/peach_leaves_mature"))).addModel();
        crossBlock(ModBlocks.PEACH_SAPLING.get(), "peach_sapling");

        blocksTreeTrunkAndPlank(ModBlocks.WALNUT_LOG.get(), ModBlocks.WALNUT_LOG_STRIPPED.get(), ModBlocks.WALNUT_LOG_BARK.get(), ModBlocks.WALNUT_LOG_STRIPPED_BARK.get(), ModBlocks.WALNUT_PLANK.get(), "walnut_log_side", "walnut_log_stripped", "walnut_log_end", "walnut_plank");
        simpleBlock(ModBlocks.WALNUT_LEAVES.get(), "walnut_leaves");
        crossBlock(ModBlocks.WALNUT_SAPLING.get(), "walnut_sapling");

        blocksTreeTrunkAndPlank(ModBlocks.MULBERRY_LOG.get(), ModBlocks.MULBERRY_LOG_STRIPPED.get(), ModBlocks.MULBERRY_LOG_BARK.get(), ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get(), ModBlocks.MULBERRY_PLANK.get(), "mulberry_log_side", "mulberry_log_stripped", "mulberry_log_end", "mulberry_plank");
        simpleBlock(ModBlocks.MULBERRY_LEAVES.get(), "mulberry_leaves");
        crossBlock(ModBlocks.MULBERRY_SAPLING.get(), "mulberry_sapling");


        blocksTreeTrunkAndPlank(ModBlocks.PLUM_LOG.get(), ModBlocks.PLUM_LOG_STRIPPED.get(), ModBlocks.PLUM_LOG_BARK.get(), ModBlocks.PLUM_LOG_STRIPPED_BARK.get(), ModBlocks.PLUM_PLANK.get(), "plum_log_side", "plum_log_stripped", "plum_log_end", "plum_plank");
        simpleBlock(ModBlocks.PLUM_LEAVES.get(), "plum_leaves");
        crossBlock(ModBlocks.PLUM_SAPLING.get(), "plum_sapling");

        simpleBlock(ModBlocks.WHITE_MARBLE.get());
        simpleBlock(ModBlocks.RED_MARBLE.get());
        simpleBlock(ModBlocks.BLACK_MARBLE.get());

        simpleBlock(ModBlocks.VAT.get(), this.models().getExistingFile(modLoc("block/vat")));

        MultiPartBlockStateBuilder stoneMill = getMultipartBuilder(ModBlocks.STONE_MILL.get());
        for (int i = 1; i <= 16; i++) {
            stoneMill.part().modelFile(this.models().getExistingFile(modLoc("block/stone_mill" + i))).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.NORTH).
                    end();
            stoneMill.part().modelFile(this.models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(180).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.SOUTH).
                    end();
            stoneMill.part().modelFile(this.models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(270).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.WEST).
                    end();
            stoneMill.part().modelFile(this.models().getExistingFile(modLoc("block/stone_mill" + i))).rotationY(90).
                    addModel().
                    condition(BlockStoneMill.ROTATE, i).
                    condition(BlockStoneMill.STATE, IMachine.MachineState.values()).
                    condition(BlockStoneMill.FACING, Direction.EAST).
                    end();
        }

        MultiPartBlockStateBuilder paperDryingRack = getMultipartBuilder(ModBlocks.PAPER_DRYING_RACK.get());
        for (int i = 0; i <= 4; i++) {
            paperDryingRack.part().modelFile(this.models().getExistingFile(modLoc("block/paper_drying_rack" + i))).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.NORTH).
                    end();
            paperDryingRack.part().modelFile(this.models().getExistingFile(modLoc("block/paper_drying_rack" + i))).rotationY(180).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.SOUTH).
                    end();
            paperDryingRack.part().modelFile(this.models().getExistingFile(modLoc("block/paper_drying_rack" + i))).rotationY(270).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.WEST).
                    end();
            paperDryingRack.part().modelFile(this.models().getExistingFile(modLoc("block/paper_drying_rack" + i))).rotationY(90).
                    addModel().
                    condition(BlockPaperDryingRack.LEVEL, i).
                    condition(BlockPaperDryingRack.FACING, Direction.EAST).
                    end();
        }

        MultiPartBlockStateBuilder stove = getMultipartBuilder(ModBlocks.STOVE.get());
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_off"))).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.CLOSE).
                condition(BlockStove.FACING, Direction.NORTH).
                end();
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_off"))).rotationY(180).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.CLOSE).
                condition(BlockStove.FACING, Direction.SOUTH).
                end();
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_off"))).rotationY(270).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.CLOSE).
                condition(BlockStove.FACING, Direction.WEST).
                end();
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_off"))).rotationY(90).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.CLOSE).
                condition(BlockStove.FACING, Direction.EAST).
                end();

        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_on"))).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.IDLE, IMachine.MachineState.WORKING, IMachine.MachineState.OVERLOAD, IMachine.MachineState.DAMAGED).
                condition(BlockStove.FACING, Direction.NORTH).
                end();
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_on"))).rotationY(180).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.IDLE, IMachine.MachineState.WORKING, IMachine.MachineState.OVERLOAD, IMachine.MachineState.DAMAGED).
                condition(BlockStove.FACING, Direction.SOUTH).
                end();
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_on"))).rotationY(270).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.IDLE, IMachine.MachineState.WORKING, IMachine.MachineState.OVERLOAD, IMachine.MachineState.DAMAGED).
                condition(BlockStove.FACING, Direction.WEST).
                end();
        stove.part().modelFile(this.models().getExistingFile(modLoc("block/stove_on"))).rotationY(90).
                addModel().
                condition(BlockStove.STATE, IMachine.MachineState.IDLE, IMachine.MachineState.WORKING, IMachine.MachineState.OVERLOAD, IMachine.MachineState.DAMAGED).
                condition(BlockStove.FACING, Direction.EAST).
                end();

        cropsStage((BlockPlant) ModBlocks.WHITE_RADISH_PLANT.get(), 4, "white_radish_plant_stage");
        cropsStage((BlockPlant) ModBlocks.SUMMER_RADISH_PLANT.get(), 4, "summer_radish_plant_stage");
        cropsStage((BlockPlant) ModBlocks.GREEN_RADISH_PLANT.get(), 4, "green_radish_plant_stage");
        cropsStage((BlockPlant) ModBlocks.GREEN_PEPPER_PLANT.get(), 8, "green_pepper_plant_stage");
    }
}
