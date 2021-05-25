package cx.rain.mc.forgemod.sinocraft.capability;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;

/**
 * WindEnergy output default implement
 */
public class WindEnergy implements IWindEnergy {
    private int WindEnergy;

    @Override
    public int getWindEnergy() {
        return this.WindEnergy;
    }

    @Override
    public void setWindEnergy(int WindEnergy) {
        this.WindEnergy = WindEnergy;
    }

    @Override
    public void resetWindEnergy() {
        this.WindEnergy = 0;
    }

    @Override
    public void addWindEnergy(int WindEnergy) {
        this.WindEnergy += WindEnergy;
    }

    @Override
    public void subWindEnergy(int WindEnergy) {
        this.WindEnergy -= WindEnergy;
    }
}
