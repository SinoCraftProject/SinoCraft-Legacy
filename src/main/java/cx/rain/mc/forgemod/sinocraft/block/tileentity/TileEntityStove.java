package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

public class TileEntityStove extends TileEntity implements ITickableTileEntity, IHeatSource, IHasBurnTime {
    private double heat = 0;
    private int burnTime = 0;

    public TileEntityStove() {
        super(ModTileEntities.STOVE.get());
    }

    @Override
    public void tick() {
        if (isBurning()) {
            modifyHeat();
            burn();
        }
    }

    private void modifyHeat() {
        BlockPos left = getPos().offset(getBlockState().get(BlockStove.FACING).rotateY(), 1);
        BlockPos right = getPos().offset(getBlockState().get(BlockStove.FACING).rotateYCCW(), 1);

        internalModify(left);
        internalModify(right);
    }

    private void internalModify(BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile instanceof IHeatModifier) {
            IHeatModifier modifier = (IHeatModifier) tile;
            heat *= modifier.getModifyValue();
        }
    }

    private void burn() {
        if (burnTime >= 0) {
            setBurnState(true);
            burnTime--;

            BlockPos up = getPos().offset(Direction.UP, 1);
            TileEntity tile = world.getTileEntity(up);
            if (tile instanceof IStoveWorker) {
                IStoveWorker worker = (IStoveWorker) tile;
                worker.work(heat);
            }
        } else {
            setBurnState(false);
        }
    }

    public void setBurnState(boolean burning) {
        world.setBlockState(pos, getBlockState().with(BlockStove.BURNING, burning));
    }

    @Override
    public double getHeat() {
        return heat;
    }

    @Override
    public void setHeat(double value) {
        heat = value;
    }

    @Override
    public void addHeat(double value) {
        heat += value;
    }

    @Override
    public void subHeat(double value) {
        heat -= value;
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
