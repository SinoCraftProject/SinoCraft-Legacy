package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.block.*;
import cx.rain.mc.forgemod.sinocraft.block.BlockPaperDryingRack;
import cx.rain.mc.forgemod.sinocraft.block.BlockStoneMill;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStoneMill;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStove;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.MathHelper;
import org.jetbrains.annotations.Nullable;

public enum APIBlocks implements ISinoBlocks {

    INSTANCE;

    @Override
    public IModBlocks getBlocks() {
        return APIModBlocks.INSTANCE;
    }

    @Nullable
    @Override
    public ITileEntityPot asTilePot(@Nullable TileEntity te) {
        if (te != null && te.getClass() == TileEntityPot.class) {
            return (ITileEntityPot) te;
        }
        return null;
    }

    @Nullable
    @Override
    public ITileEntityStoneMill asTileStoneMill(@Nullable TileEntity te) {
        if (te != null && te.getClass() == TileEntityStoneMill.class) {
            return (ITileEntityStoneMill) te;
        }
        return null;
    }

    @Nullable
    @Override
    public ITileEntityStove asTileStove(@Nullable TileEntity te) {
        if (te != null && te.getClass() == TileEntityStove.class) {
            return (ITileEntityStove) te;
        }
        return null;
    }

    @Nullable
    @Override
    public ITileEntityVat getAsVat(@Nullable TileEntity te) {
        if (te != null && te.getClass() == TileEntityVat.class) {
            return (ITileEntityVat) te;
        }
        return null;
    }

    @Override
    public BlockState setDryingProgress(BlockState block, EnumDrying progress) {
        return block.with(BlockPaperDryingRack.STATE, progress.progress);
    }

    @Override
    public EnumDrying getDryingProgress(BlockState block) {
        return EnumDrying.valueOf(block.get(BlockPaperDryingRack.STATE));
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
        return state.with(BlockStoneMill.ROTATE, MathHelper.clamp(rotate, 1, 16));
    }

    @Override
    public int getMillRotate(BlockState block) {
        return block.get(BlockStoneMill.ROTATE);
    }

}
