package cx.rain.mc.forgemod.sinocraft.api.block;

public interface ITileEntityStove {
    int getBurnTime();

    void setBurnTime(int time);

    void addBurnTime(int time);

    void subBurnTime(int time);

    boolean isBurning();
}
