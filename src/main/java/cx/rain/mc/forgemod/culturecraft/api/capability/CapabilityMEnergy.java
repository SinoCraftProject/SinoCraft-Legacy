package cx.rain.mc.forgemod.culturecraft.api.capability;

import cx.rain.mc.forgemod.culturecraft.api.interfaces.IMEnergy;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

/**
 * The Capability for {@link IMEnergy}
 * @author Infinity_rain
 */
public class CapabilityMEnergy {

    /**
     * MEnergy output Storage
     */
    public static class Storage implements Capability.IStorage<IMEnergy>
    {
        @Override
        public INBT writeNBT(Capability<IMEnergy> capability, IMEnergy instance, Direction side)
        {
            return IntNBT.valueOf(instance.getMEnergy());
        }

        @Override
        public void readNBT(Capability<IMEnergy> capability, IMEnergy instance, Direction side, INBT nbt)
        {
            instance.setMEnergy(((IntNBT)nbt).getInt());
        }
    }

    /**
     * MEnergy output factory method
     */
    public static class Factory implements Callable<IMEnergy> {

        @Override
        public IMEnergy call() throws Exception {
            return new Implementation();
        }

        /**
         * MEnergy output default implement
         */
        private class Implementation implements IMEnergy {
            private int MEnergy;

            @Override
            public int getMEnergy() {
                return this.MEnergy;
            }

            @Override
            public void setMEnergy(int MEnergy) {
                this.MEnergy=MEnergy;
            }

            @Override
            public void resetMEnergy() {
                this.MEnergy=0;
            }

            @Override
            public void addMEnergy(int MEnergy) {
                this.MEnergy+=MEnergy;
            }

            @Override
            public void subMEnergy(int MEnergy) {
                this.MEnergy-=MEnergy;
            }
        }
    }
}
