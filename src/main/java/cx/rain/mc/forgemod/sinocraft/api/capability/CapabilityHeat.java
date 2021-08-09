package cx.rain.mc.forgemod.sinocraft.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * The Capability for {@link IHeat}
 * @author Infinity_rain
 */
public class CapabilityHeat {

    @CapabilityInject(IHeat.class)
    public static Capability<IHeat> CAPABILITY;
}
