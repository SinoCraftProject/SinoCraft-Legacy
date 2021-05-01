package cx.rain.mc.forgemod.sinocraft.block.tileentity;

public interface IHasBurnTime {
    public int getBurnTime();

    public void setBurnTime(int time);

    public void addBurnTime(int time);

    public void subBurnTime(int time);

    public boolean isBurning();
}
