package cx.rain.mc.forgemod.culturecraft.init;

import cx.rain.mc.forgemod.culturecraft.api.game.interfaces.*;
import cx.rain.mc.forgemod.culturecraft.api.game.capability.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class RegistryCapability {
    @CapabilityInject(IMEnergy.class)
    public static Capability<IMEnergy> MEnergyCapabilityOutput;

    @CapabilityInject(IThermal.class)
    public static Capability<IThermal> ThermalCapabilityOutput;

    @CapabilityInject(IWaterPower.class)
    public static Capability<IWaterPower> WaterPowerCapabilityOutput;

    @CapabilityInject(IWindEnergy.class)
    public static Capability<IWindEnergy> WindEnergyCapabilityOutput;

    @CapabilityInject(IMEnergy.class)
    public static Capability<IMEnergy> MEnergyCapabilityInput;

    @CapabilityInject(IThermal.class)
    public static Capability<IThermal> ThermalCapabilityInput;

    @CapabilityInject(IWaterPower.class)
    public static Capability<IWaterPower> WaterPowerCapabilityInput;

    @CapabilityInject(IWindEnergy.class)
    public static Capability<IWindEnergy> WindEnergyCapabilityInput;

    public RegistryCapability(FMLPreInitializationEvent event)
    {
        CapabilityManager.INSTANCE.register(IMEnergy.class, new CapabilityMEnergy.Storage(),new CapabilityMEnergy.Factory());
        CapabilityManager.INSTANCE.register(IThermal.class, new CapabilityThermal.Storage(),new CapabilityThermal.Factory());
        CapabilityManager.INSTANCE.register(IWaterPower.class, new CapabilityWaterPower.Storage(),new CapabilityWaterPower.Factory());
        CapabilityManager.INSTANCE.register(IWindEnergy.class, new CapabilityWindEnergy.Storage(),new CapabilityWindEnergy.Factory());
    }
}
