package cx.rain.mc.forgemod.sinocraft.capability.empty;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;

public enum NoWind implements IWindEnergy {

    INSTANCE;

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
}
