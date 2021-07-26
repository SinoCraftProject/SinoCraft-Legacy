package cx.rain.mc.forgemod.sinocraft.capability;

import cx.rain.mc.forgemod.sinocraft.api.capability.IME;

/**
 * MEnergy output default implement
 */
public class ME implements IME {
    private int ME;

    @Override
    public int getME() {
        return this.ME;
    }

    @Override
    public void setME(int MEnergy) {
        this.ME = MEnergy;
    }

    @Override
    public void resetME() {
        this.ME = 0;
    }

    @Override
    public void addME(int ME) {
        this.ME += ME;
    }

    @Override
    public void subME(int ME) {
        this.ME -= ME;
    }
}
