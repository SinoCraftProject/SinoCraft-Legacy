package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;

import javax.annotation.Nullable;

public interface ISinoBlocks {

    Lazy<ISinoBlocks> INSTANCE = new Lazy<>();

    /**
     * An instance to get all block from the mod.
     */
    IModBlocks getBlocks();

    // ==== TileEntity =================================================================================================

    /**
     * Convert a te to ITileEntityPot if it is TileEntityPot or return null.
     */
    @Nullable
    ITileEntityPot asTilePot(@Nullable TileEntity te);

    /**
     * Convert a te to ITileEntityStoneMill if it is TileEntityStoneMill or return null.
     */
    @Nullable
    ITileEntityStoneMill asTileStoneMill(@Nullable TileEntity te);

    /**
     * Convert a te to ITileEntityStove if it is TileEntityStove or return null.
     */
    @Nullable
    ITileEntityStove asTileStove(@Nullable TileEntity te);

    /**
     * Convert a te to ITileEntityVat if it is TileEntityVat or return null.
     */
    @Nullable
    ITileEntityVat getAsVat(@Nullable TileEntity te);

    // ==== Paper Drying ===============================================================================================

    /**
     * Set the progress of drying paper.
     */
    BlockState setDryingProgress(BlockState block, EnumDrying progress);

    /**
     * Get the progress of drying paper.
     */
    EnumDrying getDryingProgress(BlockState block);

    // ==== Stove ======================================================================================================

    /**
     * Set the stove's burning state.
     */
    BlockState setStoveBurning(BlockState block, boolean burning);

    /**
     * Return if the stove is burning.
     */
    boolean isStoveBurning(BlockState block);

    // ==== Stone Mill =================================================================================================

    /**
     * Set the rotate of the stone mill. Rotate is in 1 to 16.
     */
    BlockState setMillRotate(BlockState state, int rotate);

    /**
     * Get the rotate of the stone mill.
     */
    int getMillRotate(BlockState block);
}
