package cx.rain.mc.forgemod.sinocraft.capability;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWaterPower;

/**
 * WaterPower output default implement
 */
public class WaterPower implements IWaterPower {
    private int WaterPower;

    @Override
    public int getWaterPower() {
        return this.WaterPower;
    }

    @Override
    public void setWaterPower(int WaterPower) {
        this.WaterPower = WaterPower;
    }

    @Override
    public void resetWaterPower() {
        this.WaterPower = 0;
    }

    @Override
    public void addWaterPower(int WaterPower) {
        this.WaterPower += WaterPower;
    }

    @Override
    public void subWaterPower(int WaterPower) {
        this.WaterPower -= WaterPower;
    }
}
