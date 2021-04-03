package cx.rain.mc.forgemod.sinocraft.api.interfaces;

/**
 * All Tile Entity of machines that can output thermal must implement this interface
 * @author Infinity_rain
 */
public interface IThermal {
    /**
     * Get thermal
     * @return thermal
     * @author flysong
     */
    int getThermal();

    /**
     * Set thermal
     * @param thermal thermal
     * @author flysong
     */
    void setThermal(int thermal);

    /**
     * Reset thermal
     * @author flysong
     */
    void resetThermal();

    /**
     * Add thermal
     * @param thermal thermal
     * @author flysong
     */
    void addThermal(int thermal);

    /**
     * Sub thermal
     * @param thermal thermal
     * @author flysong
     */
    void subThermal(int thermal);
}
