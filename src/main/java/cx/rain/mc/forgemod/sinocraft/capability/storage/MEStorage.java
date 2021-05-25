package cx.rain.mc.forgemod.sinocraft.capability.storage;

import cx.rain.mc.forgemod.sinocraft.api.capability.IME;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public enum MEStorage implements Capability.IStorage<IME> {

    INSTANCE;

    @Override
    public INBT writeNBT(Capability<IME> capability, IME instance, Direction side) {
        return IntNBT.valueOf(instance.getME());
    }

    @Override
    public void readNBT(Capability<IME> capability, IME instance, Direction side, INBT nbt) {
        instance.setME(((IntNBT) nbt).getInt());
    }
}
