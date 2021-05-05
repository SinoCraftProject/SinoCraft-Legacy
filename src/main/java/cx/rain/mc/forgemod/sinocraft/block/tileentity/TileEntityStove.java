package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;

import static cx.rain.mc.forgemod.sinocraft.capability.ModCapabilities.*;

public class TileEntityStove extends TileEntity implements ITickableTileEntity, IHeat {
    private int thermal = 0;
    private int burnTime = 0;
    private int burnSpeed = 1;

    public TileEntityStove() {
        super(ModTileEntities.STOVE.get());
    }

    @Override
    public void tick() {
        if (isBurning()) {
            modifyBurnSpeed();
            burn();
        }
    }

    private void modifyBurnSpeed() {
        BlockPos left = getPos().offset(getBlockState().get(BlockStove.FACING).rotateY(), 1);
        BlockPos right = getPos().offset(getBlockState().get(BlockStove.FACING).rotateYCCW(), 1);

        internalModify(left);
        internalModify(right);
    }

    private void internalModify(BlockPos pos) {
        TileEntity tile = world.getTileEntity(pos);
        if (tile.getCapability(WIND_ENERGY_CAPABILITY).isPresent()) {
            burnSpeed += tile.getCapability(WIND_ENERGY_CAPABILITY).orElse(null).getWindEnergy();
        }
    }

    private void burn() {
        burnTime -= burnSpeed;
        thermal += burnSpeed;

        BlockPos up = getPos().offset(Direction.UP, 1);
        TileEntity tile = world.getTileEntity(up);
        if (tile.getCapability(HEAT_CAPABILITY).isPresent()) {
            tile.getCapability(HEAT_CAPABILITY).orElse(null).setHeat(thermal);
        }
    }

    @Override
    public int getHeat() {
        return thermal;
    }

    @Override
    public void setHeat(int value) {
        thermal = value;
    }

    @Override
    public void resetHeat() {
        setHeat(0);
    }

    @Override
    public void addHeat(int value) {
        thermal += value;
    }

    @Override
    public void subHeat(int value) {
        thermal -= value;
    }

    public int getBurnTime() {
        return burnTime;
    }

    public void setBurnTime(int time) {
        burnTime = time;
    }

    public void addBurnTime(int time) {
        burnTime += time;
    }

    public void subBurnTime(int time) {
        burnTime -= time;
    }

    public boolean isBurning() {
        return burnTime > 0;
    }
}
