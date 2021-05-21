package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public interface ISinoBlocks {

    Lazy<ISinoBlocks> INSTANCE = new Lazy<>();

    IModBlocks getBlocks();

    IBlockStateHelper getBlockStateHelper();

    @Nullable
    ITileEntityPot asTilePot(@Nullable TileEntity te);

    @Nullable
    ITileEntityStoneMill asTileStoneMill(@Nullable TileEntity te);

    @Nullable
    ITileEntityStove asTileStove(@Nullable TileEntity te);

    @Nullable
    ITileEntityVat getAsVat(@Nullable TileEntity te);
}
