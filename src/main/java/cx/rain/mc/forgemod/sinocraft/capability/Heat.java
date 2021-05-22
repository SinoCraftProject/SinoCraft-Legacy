package cx.rain.mc.forgemod.sinocraft.capability;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;

/**
 * Heat output default implement
 */
public class Heat implements IHeat {
    private int heat;
    private int maxHeat;

    @Override
    public int getHeat() {
        return this.heat;
    }

    @Override
    public void setHeat(int heat) {
        if (heat != this.heat) {
            this.heat = heat;
            onHeatChanged();
        }
    }

    @Override
    public void resetHeat() {
        this.heat = 0;
        this.maxHeat = 0;
        onHeatChanged();
    }

    @Override
    public void addHeat(int heat) {
        if (heat != 0) {
            this.heat += heat;
            onHeatChanged();
        }
    }

    @Override
    public void subHeat(int heat) {
        if (heat != 0) {
            setHeat(Math.max(0, this.heat - heat));
        }
    }

    @Override
    public void setMaxHeat(int heat) {
        if (maxHeat != heat) {
            maxHeat = heat;
            onHeatChanged();
        }
    }

    @Override
    public int getMaxHeat() {
        return maxHeat;
    }

    public void onHeatChanged() {

    }
}
