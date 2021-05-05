package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;
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
            return IntNBT.valueOf(instance.getHeat());
        }

        @Override
        public void readNBT(Capability<IHeat> capability, IHeat instance, Direction side, INBT nbt)
        {
            instance.setHeat(((IntNBT)nbt).getInt());
        }
    }

    /**
     * Thermal output factory method
     */
    public static class Factory implements Callable<IHeat> {

        @Override
        public IHeat call() throws Exception {
            return new CapabilityHeat.Factory.Implementation();
        }

        /**
         * Heat output default implement
         */
        private class Implementation implements IHeat {
            private int heat;

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
            }

            @Override
            public void addHeat(int heat) {
                this.heat+=heat;
            }

            @Override
            public void subHeat(int heat) {
                this.heat-=heat;
            }
        }
    }
}
