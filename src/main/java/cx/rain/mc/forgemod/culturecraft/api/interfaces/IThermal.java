package cx.rain.mc.forgemod.culturecraft.api.interfaces;

/**
 * All Tile Entity of machines that can output thermal must implement this interface
 * @author Infinity_rain
 */
public interface IThermal {
    /**
     * Get thermal
     * @return Thermal
     * @author flysong
     */
    int getThermal();

    /**
     * Set thermal
     * @param Thermal Thermal
     * @author flysong
     */
    void setThermal(int Thermal);

    /**
     * Reset thermal
     * @author flysong
     */
    void resetThermal();

    /**
     * Add thermal
     * @param Thermal Thermal
     * @author flysong
     */
    void addThermal(int Thermal);

    /**
     * Sub thermal
     * @param Thermal Thermal
     * @author flysong
     */
    void subThermal(int Thermal);
}
