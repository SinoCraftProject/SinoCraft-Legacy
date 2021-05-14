package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;
import cx.rain.mc.forgemod.sinocraft.utility.DataFixHelper;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

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
            CompoundNBT data = DataFixHelper.fixHeatData(nbt);
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
            return new Implementation();
        }

        /**
         * Heat output default implement
         */
        private static class Implementation implements IHeat {
            private int heat;
            private int maxHeat;

            @Override
            public int getHeat() {
                return this.heat;
            }

            @Override
            public void setHeat(int heat) {
                this.heat=heat;
            }

            @Override
            public void resetHeat() {
                this.heat=0;
                this.maxHeat = 0;
            }

            @Override
            public void addHeat(int heat) {
                this.heat+=heat;
            }

            @Override
            public void subHeat(int heat) {
                this.heat-=heat;
            }

            @Override
            public void setMaxHeat(int heat) {
                maxHeat = heat;
            }

            @Override
            public int getMaxHeat() {
                return maxHeat;
            }
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
