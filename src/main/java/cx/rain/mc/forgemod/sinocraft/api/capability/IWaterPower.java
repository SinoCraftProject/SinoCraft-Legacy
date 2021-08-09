package cx.rain.mc.forgemod.sinocraft.api.capability;

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
     * @param wp Water power
     * @author flysong
     */
    void setWaterPower(int wp);

    /**
     * Reset water power
     * @author flysong
     */
    void resetWaterPower();

    /**
     * Add water power
     * @param wp Water power
     * @author flysong
     */
    void addWaterPower(int wp);

    /**
     * Sub water power
     * @param wp Water power
     * @author flysong
     */
    void subWaterPower(int wp);
}
