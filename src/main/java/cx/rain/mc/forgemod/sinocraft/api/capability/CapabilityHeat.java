package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.capability.IHeat;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;

import java.util.concurrent.Callable;

/**
 * The Capability for {@link IHeat}
 * @author Infinity_rain
 */
public class CapabilityHeat {
    /**
     * Thermal output Storage
     */
    public static class Storage implements Capability.IStorage<IHeat>
    {
        @Override
        public INBT writeNBT(Capability<IHeat> capability, IHeat instance, Direction side)
        {
            CompoundNBT data = new CompoundNBT();
            data.putInt("heat", instance.getHeat());
            data.putInt("maxHeat", instance.getMaxHeat());
            return data;
        }

        @Override
        public void readNBT(Capability<IHeat> capability, IHeat instance, Direction side, INBT nbt)
        {
            CompoundNBT data = fixHeatData(nbt);
            instance.setHeat(data.getInt("heat"));
            instance.setMaxHeat(data.getInt("maxHeat"));
        }
    }

    /**
     * Thermal output factory method
     */
    public static class Factory implements Callable<IHeat> {

        @Override
        public IHeat call() throws Exception {
            return new Heat();
        }
    }

    public static CompoundNBT fixHeatData(INBT nbt) {
        if (nbt instanceof IntNBT) {
            CompoundNBT newData = new CompoundNBT();
            newData.putInt("heat", ((IntNBT) nbt).getInt());
            newData.putInt("maxHeat", 0);
            return newData;
        } else if (nbt instanceof CompoundNBT) {
            CompoundNBT data = (CompoundNBT) nbt;
            if (!data.contains("heat", Constants.NBT.TAG_INT)) {
                data.putInt("heat", 0);
            }
            if (!data.contains("maxHeat", Constants.NBT.TAG_INT)) {
                data.putInt("maxHeat", 0);
            }
            return data;
        } else {
            CompoundNBT newData = new CompoundNBT();
            newData.putInt("heat", 0);
            newData.putInt("maxHeat", 0);
            return newData;
        }
    }

    public static final IHeat NoHeat = new IHeat() {
        @Override
        public int getHeat() {
            return 0;
        }

        @Override
        public void setHeat(int heat) {

        }

        @Override
        public void resetHeat() {

        }

        @Override
        public void addHeat(int heat) {

        }

        @Override
        public void subHeat(int heat) {

        }

        @Override
        public void setMaxHeat(int heat) {

        }

        @Override
        public int getMaxHeat() {
            return 0;
        }
    };

}
