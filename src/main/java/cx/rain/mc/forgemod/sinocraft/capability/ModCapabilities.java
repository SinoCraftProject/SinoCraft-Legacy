package cx.rain.mc.forgemod.sinocraft.capability;

import cx.rain.mc.forgemod.sinocraft.api.capability.IHeat;
import cx.rain.mc.forgemod.sinocraft.api.capability.IME;
import cx.rain.mc.forgemod.sinocraft.api.capability.IWaterPower;
import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;
import cx.rain.mc.forgemod.sinocraft.capability.storage.HeatStorage;
import cx.rain.mc.forgemod.sinocraft.capability.storage.MEStorage;
import cx.rain.mc.forgemod.sinocraft.capability.storage.WaterPowerStorage;
import cx.rain.mc.forgemod.sinocraft.capability.storage.WindEnergyStorage;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCapabilities {

    @SubscribeEvent
    public static void registerCapabilities(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            CapabilityManager.INSTANCE.register(IHeat.class, HeatStorage.INSTANCE, Heat::new);
            CapabilityManager.INSTANCE.register(IME.class, MEStorage.INSTANCE, ME::new);
            CapabilityManager.INSTANCE.register(IWaterPower.class, WaterPowerStorage.INSTANCE, WaterPower::new);
            CapabilityManager.INSTANCE.register(IWindEnergy.class, WindEnergyStorage.INSTANCE, WindEnergy::new);

            CapabilityManager.INSTANCE.register(CapabilityTeacup.CapTeacup.class, CapabilityTeacup.STORAGE, CapabilityTeacup.CapTeacup::new);
            CapabilityManager.INSTANCE.register(CapabilityTeapot.CapTeapot.class, CapabilityTeapot.STORAGE, CapabilityTeapot.CapTeapot::new);
        });
    }
}
