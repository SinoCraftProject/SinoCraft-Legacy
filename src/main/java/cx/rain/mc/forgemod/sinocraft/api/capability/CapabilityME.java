package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IME;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

/**
 * The Capability for {@link IME}
 * @author Infinity_rain
 */
public class CapabilityME {

    /**
     * MEnergy output Storage
     */
    public static class Storage implements Capability.IStorage<IME>
    {
        @Override
        public INBT writeNBT(Capability<IME> capability, IME instance, Direction side)
        {
            return IntNBT.valueOf(instance.getME());
        }

        @Override
        public void readNBT(Capability<IME> capability, IME instance, Direction side, INBT nbt)
        {
            instance.setME(((IntNBT)nbt).getInt());
        }
    }

    /**
     * MEnergy output factory method
     */
    public static class Factory implements Callable<IME> {

        @Override
        public IME call() throws Exception {
            return new Implementation();
        }

        /**
         * MEnergy output default implement
         */
        private class Implementation implements IME {
            private int ME;

            @Override
            public int getME() {
                return this.ME;
            }

            @Override
            public void setME(int MEnergy) {
                this.ME=MEnergy;
            }

            @Override
            public void resetME() {
                this.ME=0;
            }

            @Override
            public void addME(int ME) {
                this.ME+=ME;
            }

            @Override
            public void subME(int ME) {
                this.ME-=ME;
            }
        }
    }
}
