package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.block.IBlockStateHelper;
import cx.rain.mc.forgemod.sinocraft.block.BlockPaperDryingRack;
import cx.rain.mc.forgemod.sinocraft.block.BlockStoneMill;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontal;
import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;

public enum APIBlockStateHelper implements IBlockStateHelper {

    INSTANCE;

    @Override
    public BlockState setDirection(BlockState block, Direction direction) {
        return block.with(BlockHorizontal.FACING, direction);
    }

    @Override
    public Direction getDirection(BlockState block) {
        return block.get(BlockHorizontal.FACING);
    }

    @Override
    public BlockState setDryingProgress(BlockState block, int progress) {
        return block.with(BlockPaperDryingRack.STATE, progress);
    }

    @Override
    public int getDryingProgress(BlockState block) {
        return block.get(BlockPaperDryingRack.STATE);
    }

    @Override
    public BlockState setStoveBurning(BlockState block, boolean burning) {
        return block.with(BlockStove.BURNING, burning);
    }

    @Override
    public boolean isStoveBurning(BlockState block) {
        return block.get(BlockStove.BURNING);
    }

    @Override
    public BlockState setMillRotate(BlockState state, int rotate) {
        return state.with(BlockStoneMill.ROTATE, rotate);
    }

    @Override
    public int getMillRotate(BlockState block) {
        return block.get(BlockStoneMill.ROTATE);
    }

}
