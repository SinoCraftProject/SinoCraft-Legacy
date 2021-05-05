package cx.rain.mc.forgemod.sinocraft.capability;

import cx.rain.mc.forgemod.sinocraft.api.capability.*;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {
    @CapabilityInject(IHeat.class)
    public static Capability<IHeat> HEAT_CAPABILITY;

    @CapabilityInject(IME.class)
    public static Capability<IME> ME_CAPABILITY;

    @CapabilityInject(IWaterPower.class)
    public static Capability<IWaterPower> WATER_POWER_CAPABILITY;

    @CapabilityInject(IWindEnergy.class)
    public static Capability<IWindEnergy> WIND_ENERGY_CAPABILITY;

    @SubscribeEvent
    public static void registerCapabilities(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(IHeat.class, new CapabilityHeat.Storage(), new CapabilityHeat.Factory());
            CapabilityManager.INSTANCE.register(IME.class, new CapabilityME.Storage(), new CapabilityME.Factory());
            CapabilityManager.INSTANCE.register(IWaterPower.class, new CapabilityWaterPower.Storage(), new CapabilityWaterPower.Factory());
            CapabilityManager.INSTANCE.register(IWindEnergy.class, new CapabilityWindEnergy.Storage(), new CapabilityWindEnergy.Factory());
        });
    }
}
