package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

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

    /**
     * Convert a te to ITileEntityTeaTable if it is TileEntityTeaTable or return null.
     */
    @Nullable
    ITileEntityTeaTable getAsTable(@Nullable TileEntity te);

    // ==== Plant ======================================================================================================

    /**
     * Get the height of plant. If the max height is 1, return 1.
     */
    int getPlantHeight(IBlockReader worldIn, BlockPos pos, BlockState state);

    /**
     * True if the plant's max height is more than 1 and is top plant. If max height is 1, return if the block is mod plant.
     */
    boolean isPlantTop(BlockState state);

    /**
     * Grow the plant.
     */
    void growPlant(World worldIn, BlockPos pos, BlockState state, int age);

    /**
     * If the block is plant from mod, return the plant type, otherwise return null.
     */
    @Nullable
    IPlantType getPlantType(Block block);

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
