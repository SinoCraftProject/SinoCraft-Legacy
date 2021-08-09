package cx.rain.mc.forgemod.sinocraft.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * The Capability for {@link IWindEnergy}
 * @author Infinity_rain
 */
public class CapabilityWindEnergy {

    @CapabilityInject(IWindEnergy.class)
    public static Capability<IWindEnergy> CAPABILITY;
}
