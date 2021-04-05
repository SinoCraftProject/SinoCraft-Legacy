package cx.rain.mc.forgemod.sinocraft.api.interfaces;

/**
 * All Tile Entity of machines that can output MEnergy must implement this interface
 * ME:Mechanical Energy
 * @author Infinity_rain
 */
public interface IME {

    /**
     * Get ME
     * @return ME
     * @author flysong
     */
    int getME();

    /**
     * Set ME
     * @param ME ME
     * @author flysong
     */
    void setME(int ME);

    /**
     * Reset ME
     * @author flysong
     */
    void resetME();

    /**
     * Add ME
     * @param ME ME
     * @author flysong
     */
    void addME(int ME);

    /**
     * Sub ME
     * @param ME MEnergy
     * @author flysong
     */
    void subME(int ME);
}
