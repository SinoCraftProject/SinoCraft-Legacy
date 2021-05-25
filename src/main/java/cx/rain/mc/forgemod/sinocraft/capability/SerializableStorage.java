package cx.rain.mc.forgemod.sinocraft.capability;

import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

import javax.annotation.Nullable;

public class SerializableStorage<T extends INBTSerializable<INBT>> implements Capability.IStorage<T> {

    private static final SerializableStorage<?> storage = new SerializableStorage<>();

    public static <T extends INBTSerializable<? extends INBT>> Capability.IStorage<T> get() {
        return (Capability.IStorage<T>) storage;
    }

    @Nullable
    @Override
    public INBT writeNBT(Capability<T> capability, T instance, Direction side) {
        return instance.serializeNBT();
    }

    @Override
    public void readNBT(Capability<T> capability, T instance, Direction side, INBT nbt) {
        instance.deserializeNBT(nbt);
    }
}
