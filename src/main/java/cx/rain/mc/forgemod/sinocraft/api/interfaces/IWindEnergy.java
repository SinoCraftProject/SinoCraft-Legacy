package cx.rain.mc.forgemod.sinocraft.api.interfaces;

/**
 * All Tile Entity of machines that can output wind energy must implement this interface
 * @author Infinity_rain
 */
public interface IWindEnergy {
    /**
     * Get wind energy
     * @return Wind energy
     * @author flysong
     */
    int getWindEnergy();

    /**
     * Set wind energy
     * @param we Wind energy
     * @author flysong
     */
    void setWindEnergy(int we);

    /**
     * Reset wind energy
     * @author flysong
     */
    void resetWindEnergy();

    /**
     * Add wind energy
     * @param we Wind energy
     * @author flysong
     */
    void addWindEnergy(int we);

    /**
     * Sub wind energy
     * @param we Wind energy
     * @author flysong
     */
    void subWindEnergy(int we);
}
