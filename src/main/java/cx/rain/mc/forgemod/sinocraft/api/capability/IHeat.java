package cx.rain.mc.forgemod.sinocraft.api.capability;

/**
 * All Tile Entity of machines that can output heat must implement this interface
 * @author Infinity_rain
 */
public interface IHeat {
    /**
     * Get heat
     * @return heat
     * @author flysong
     */
    int getHeat();

    /**
     * Set heat
     * @param heat heat
     * @author flysong
     */
    void setHeat(int heat);

    /**
     * Reset heat
     * @author flysong
     */
    void resetHeat();

    /**
     * Add heat
     * @param heat heat
     * @author flysong
     */
    void addHeat(int heat);

    /**
     * Sub heat
     * @param heat heat
     * @author flysong
     */
    void subHeat(int heat);
}
