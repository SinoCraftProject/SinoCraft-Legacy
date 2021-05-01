package cx.rain.mc.forgemod.sinocraft.block.tileentity;

public interface IHeatSource {
    public double getHeat();

    public void setHeat(double value);

    public void addHeat(double value);

    public void subHeat(double value);
}
