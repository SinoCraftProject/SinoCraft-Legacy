package cx.rain.mc.forgemod.sinocraft.capability.empty;

import cx.rain.mc.forgemod.sinocraft.api.capability.IHeat;

public enum NoHeat implements IHeat {

    INSTANCE;

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
}
