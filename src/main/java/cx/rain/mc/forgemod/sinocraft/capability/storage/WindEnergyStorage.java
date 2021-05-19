package cx.rain.mc.forgemod.sinocraft.capability.storage;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public enum WindEnergyStorage implements Capability.IStorage<IWindEnergy> {

    INSTANCE;

    @Override
    public INBT writeNBT(Capability<IWindEnergy> capability, IWindEnergy instance, Direction side) {
        return IntNBT.valueOf(instance.getWindEnergy());
    }

    @Override
    public void readNBT(Capability<IWindEnergy> capability, IWindEnergy instance, Direction side, INBT nbt) {
        instance.setWindEnergy(((IntNBT) nbt).getInt());
    }
}
