package cx.rain.mc.forgemod.sinocraft.api.block;

public interface ITileEntityStove {

    /**
     * Get the remaining burning time.
     */
    int getBurnTime();

    /**
     * Set the remaining burning time.
     */
    void setBurnTime(int time);

    /**
     * Add the remaining burning time.
     */
    void addBurnTime(int time);

    /**
     * Reduce the remaining burning time.
     */
    void subBurnTime(int time);

    /**
     * Return true if the stove is burning.
     */
    boolean isBurning();
}
