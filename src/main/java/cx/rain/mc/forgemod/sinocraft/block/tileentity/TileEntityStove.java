package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileEntityStove extends TileEntity implements ITickableTileEntity, IHeatSource, IHasBurnTime {
    private int heatValue = 0;

    private int burnTime = 0;

    public TileEntityStove() {
        super(ModTileEntities.STOVE.get());
    }

    @Override
    public void tick() {

    }

    @Override
    public int getHeatValue() {
        return heatValue;
    }

    @Override
    public void setHeatValue(int value) {
        heatValue = value;
    }

    @Override
    public int getBurnTime() {
        return burnTime;
    }

    @Override
    public void setBurnTime(int time) {
        burnTime = time;
    }

    @Override
    public void addBurnTime(int time) {
        burnTime += time;
    }

    @Override
    public void subBurnTime(int time) {
        burnTime -= time;
    }

    @Override
    public boolean isBurning() {
        return burnTime > 0;
    }
}
