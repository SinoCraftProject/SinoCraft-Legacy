package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IThermal;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

/**
 * The Capability for {@link IThermal}
 * @author Infinity_rain
 */
public class CapabilityThermal {
    /**
     * Thermal output Storage
     */
    public static class Storage implements Capability.IStorage<IThermal>
    {
        @Override
        public INBT writeNBT(Capability<IThermal> capability, IThermal instance, Direction side)
        {
            return IntNBT.valueOf(instance.getThermal());
        }

        @Override
        public void readNBT(Capability<IThermal> capability, IThermal instance, Direction side, INBT nbt)
        {
            instance.setThermal(((IntNBT)nbt).getInt());
        }
    }

    /**
     * Thermal output factory method
     */
    public static class Factory implements Callable<IThermal> {

        @Override
        public IThermal call() throws Exception {
            return new CapabilityThermal.Factory.Implementation();
        }

        /**
         * Thermal output default implement
         */
        private class Implementation implements IThermal {
            private int Thermal;

            @Override
            public int getThermal() {
                return this.Thermal;
            }

            @Override
            public void setThermal(int Thermal) {
                this.Thermal=Thermal;
            }

            @Override
            public void resetThermal() {
                this.Thermal=0;
            }

            @Override
            public void addThermal(int Thermal) {
                this.Thermal+=Thermal;
            }

            @Override
            public void subThermal(int Thermal) {
                this.Thermal-=Thermal;
            }
        }
    }
}
