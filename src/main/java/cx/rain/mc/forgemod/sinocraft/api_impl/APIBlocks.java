package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.block.*;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStoneMill;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStove;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import net.minecraft.tileentity.TileEntity;
import org.jetbrains.annotations.Nullable;

public enum APIBlocks implements ISinoBlocks {

    INSTANCE;

    @Override
    public IModBlocks getBlocks() {
        return APIModBlocks.INSTANCE;
    }

    @Override
    public IBlockStateHelper getBlockStateHelper() {
        return APIBlockStateHelper.INSTANCE;
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
}
