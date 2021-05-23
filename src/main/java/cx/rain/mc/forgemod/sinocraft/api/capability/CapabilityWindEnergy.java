package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.capability.IWindEnergy;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

import java.util.concurrent.Callable;

/**
 * The Capability for {@link IWindEnergy}
 * @author Infinity_rain
 */
public class CapabilityWindEnergy {
    /**
     * WindEnergy output Storage
     */
    public static class Storage implements Capability.IStorage<IWindEnergy>
    {
        @Override
        public INBT writeNBT(Capability<IWindEnergy> capability, IWindEnergy instance, Direction side)
        {
            return IntNBT.valueOf(instance.getWindEnergy());
        }

        @Override
        public void readNBT(Capability<IWindEnergy> capability, IWindEnergy instance, Direction side, INBT nbt)
        {
            instance.setWindEnergy(((IntNBT)nbt).getInt());
        }
    }

    /**
     * WindEnergy output factory method
     */
    public static class Factory implements Callable<IWindEnergy> {

        @Override
        public IWindEnergy call() throws Exception {
            return new CapabilityWindEnergy.Factory.Implementation();
        }

        /**
         * WindEnergy output default implement
         */
        private class Implementation implements IWindEnergy {
            private int WindEnergy;

            @Override
            public int getWindEnergy() {
                return this.WindEnergy;
            }

            @Override
            public void setWindEnergy(int WindEnergy) {
                this.WindEnergy=WindEnergy;
            }

            @Override
            public void resetWindEnergy() {
                this.WindEnergy=0;
            }

            @Override
            public void addWindEnergy(int WindEnergy) {
                this.WindEnergy+=WindEnergy;
            }

            @Override
            public void subWindEnergy(int WindEnergy) {
                this.WindEnergy-=WindEnergy;
            }
        }
    }

    public static final IWindEnergy NoWind = new IWindEnergy() {
        @Override
        public int getWindEnergy() {
            return 0;
        }

        @Override
        public void setWindEnergy(int we) {

        }

        @Override
        public void resetWindEnergy() {

        }

        @Override
        public void addWindEnergy(int we) {

        }

        @Override
        public void subWindEnergy(int we) {

        }
    };
}
