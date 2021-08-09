package cx.rain.mc.forgemod.sinocraft.capability.storage;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWaterPower;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public enum WaterPowerStorage implements Capability.IStorage<IWaterPower> {

    INSTANCE;

    @Override
    public INBT writeNBT(Capability<IWaterPower> capability, IWaterPower instance, Direction side) {
        return IntNBT.valueOf(instance.getWaterPower());
    }

    @Override
    public void readNBT(Capability<IWaterPower> capability, IWaterPower instance, Direction side, INBT nbt) {
        instance.setWaterPower(((IntNBT) nbt).getInt());
    }
}
