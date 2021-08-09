package cx.rain.mc.forgemod.sinocraft.utility;

import cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityHeat;
import cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityWindEnergy;
import cx.rain.mc.forgemod.sinocraft.api.capability.IHeat;
import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeacup;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeapot;
import cx.rain.mc.forgemod.sinocraft.capability.empty.NoHeat;
import cx.rain.mc.forgemod.sinocraft.capability.empty.NoWind;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public class CapabilityHelper {

    public static IHeat getHeat(@Nullable TileEntity te) {
        return getCapability(te, CapabilityHeat.CAPABILITY, NoHeat.INSTANCE);
    }

    public static CapabilityTeacup.CapTeacup getTeacup(@Nullable ItemStack stack) {
        return getCapability(stack, CapabilityTeacup.CAPABILITY, CapabilityTeacup.DUMMY);
    }

    public static CapabilityTeapot.CapTeapot getTeapot(@Nullable ItemStack stack) {
        return getCapability(stack, CapabilityTeapot.CAPABILITY, CapabilityTeapot.DUMMY);
    }

    public static IWindEnergy getWind(@Nullable TileEntity te) {
        return getCapability(te, CapabilityWindEnergy.CAPABILITY, NoWind.INSTANCE);
    }

    public static <T> T getCapability(@Nullable ICapabilityProvider provider, Capability<T> capability, T empty) {
        if (provider == null || capability == null) {
            return empty;
        }
        return provider.getCapability(capability).orElse(empty);
    }
}
