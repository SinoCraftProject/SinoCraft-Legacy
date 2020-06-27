package cx.rain.mc.forgemod.culturecraft.api.interfaces;

/**
 * All Tile Entity of machines that can output MEnergy must implement this interface
 * MEnergy:MechanicalEnergy
 * @author Infinity_rain
 */
public interface IMEnergy {

    /**
     * Get MEnergy
     * @return MEnergy
     * @author flysong
     */
    int getMEnergy();

    /**
     * Set MEnergy
     * @param MEnergy MEnergy
     * @author flysong
     */
    void setMEnergy(int MEnergy);

    /**
     * Reset MEnergy
     * @author flysong
     */
    void resetMEnergy();

    /**
     * Add MEnergy
     * @param MEnergy MEnergy
     * @author flysong
     */
    void addMEnergy(int MEnergy);

    /**
     * Sub MEnergy
     * @param MEnergy MEnergy
     * @author flysong
     */
    void subMEnergy(int MEnergy);
}
