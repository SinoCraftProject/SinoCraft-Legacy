package cx.rain.mc.forgemod.sinocraft.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * The Capability for {@link IWaterPower}
 * @author Infinity_rain
 */
public class CapabilityWaterPower {

    @CapabilityInject(IWaterPower.class)
    public static Capability<IWaterPower> CAPABILITY;
}
