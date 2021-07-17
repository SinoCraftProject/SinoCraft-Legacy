package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;
import net.minecraft.util.Direction;

/**
 * A interface applied for tileEntity and means the block has ability to receive wind energy.
 */
public interface IWindEnergyReceiver {

    int receiveWindEnergy(Direction direction, int energy);

    /**
     * Get min ticks between twice receive.
     */
    default int getWindEnergyReceiveInterval() {
        return 3;
    }

    /**
     * Return true if can receive wind energy from given provider.
     * <p>This method will be called when the provider block placed, or the provider has no block to output
     * and a receiver placed around the provider.</p>
     */
    default boolean canSupportWindEnergyProvider(Direction direction, IWindEnergy energy) {
        return true;
    }
}
