package cx.rain.mc.forgemod.sinocraft.api.capability;

import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;

/**
 * The Capability for {@link IME}
 * @author Infinity_rain
 */
public class CapabilityME {

    @CapabilityInject(IME.class)
    public static Capability<IME> CAPABILITY;
}
