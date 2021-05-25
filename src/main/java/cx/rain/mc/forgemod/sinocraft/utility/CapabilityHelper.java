package cx.rain.mc.forgemod.sinocraft.utility;

import cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityHeat;
import cx.rain.mc.forgemod.sinocraft.api.capability.IHeat;
import cx.rain.mc.forgemod.sinocraft.capability.empty.NoHeat;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nullable;

public class CapabilityHelper {
    public static IHeat getHeat(@Nullable ICapabilityProvider provider) {
        if (provider == null) {
            return NoHeat.INSTANCE;
        }
        return provider.getCapability(CapabilityHeat.CAPABILITY).orElse(NoHeat.INSTANCE);
    }

    public static LazyOptional<IHeat> getHeatOpt(@Nullable ICapabilityProvider provider) {
        return provider == null ? LazyOptional.empty() : provider.getCapability(CapabilityHeat.CAPABILITY);
    }
}
