package cx.rain.mc.forgemod.sinocraft.api.block;

import net.minecraft.block.BlockState;
import net.minecraft.util.Direction;

public interface IBlockStateHelper {

    // ==== Horizontal =================================================================================================

    BlockState setDirection(BlockState block, Direction direction);

    Direction getDirection(BlockState block);

    // ==== Paper Drying ===============================================================================================

    BlockState setDryingProgress(BlockState block, int progress);

    int getDryingProgress(BlockState block);

    // ==== Stove ======================================================================================================

    BlockState setStoveBurning(BlockState block, boolean burning);

    boolean isStoveBurning(BlockState block);

    // ==== Stone Mill =================================================================================================

    BlockState setMillRotate(BlockState state, int rotate);

    int getMillRotate(BlockState block);
}
