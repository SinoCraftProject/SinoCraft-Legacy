package cx.rain.mc.forgemod.sinocraft.api.interfaces;

/**
 * All Tile Entity of machines that can output water power must implement this interface
 * @author Infinity_rain
 */
public interface IWaterPower {
    /**
     * Get water power
     * @return Water power
     * @author flysong
     */
    int getWaterPower();

    /**
     * Set water power
     * @param WaterPower Water power
     * @author flysong
     */
    void setWaterPower(int WaterPower);

    /**
     * Reset water power
     * @author flysong
     */
    void resetWaterPower();

    /**
     * Add water power
     * @param WaterPower Water power
     * @author flysong
     */
    void addWaterPower(int WaterPower);

    /**
     * Sub water power
     * @param WaterPower Water power
     * @author flysong
     */
    void subWaterPower(int WaterPower);
}
