package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.capability.CapabilityWindEnergy;
import cx.rain.mc.forgemod.sinocraft.capability.Heat;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.capability.ModCapabilities;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.util.Constants;

import static cx.rain.mc.forgemod.sinocraft.capability.ModCapabilities.WIND_ENERGY_CAPABILITY;

public class TileEntityStove extends TileEntity implements ITickableTileEntity {

    private int burnTime = 0;
    private int burnSpeed = 1;
    private int cooldown = 0;
    private final Heat heat = new Heat() {
        @Override
        public void onHeatChanged() {
            markDirty();
        }
    };

    public TileEntityStove() {
        super(ModTileEntities.STOVE.get());
    }

    @Override
    public void tick() {
        if (world != null && !world.isRemote) {
            if (isBurning()) {
                modifyBurnSpeed();
                burn();
            } else if (cooldown > 0) {
                cooldown--;
            } else {
                heat.subHeat(1);
                cooldown = 20;
            }
        }
    }

    private void modifyBurnSpeed() {
        BlockPos left = getPos().offset(getBlockState().get(BlockStove.FACING).rotateY(), 1);
        BlockPos right = getPos().offset(getBlockState().get(BlockStove.FACING).rotateYCCW(), 1);

        internalModify(left);
        internalModify(right);
    }

    private void internalModify(BlockPos pos) {
        assert world != null;
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null && tile.getCapability(WIND_ENERGY_CAPABILITY).isPresent()) {
            burnSpeed += tile.getCapability(WIND_ENERGY_CAPABILITY).orElse(CapabilityWindEnergy.NoWind).getWindEnergy();
        }
    }

    private void burn() {
        assert world != null;
        if (burnTime > 0) {
            burnTime = Math.max(0, burnTime - burnSpeed);
        }
        heat.addHeat(burnSpeed);

        BlockPos up = getPos().offset(Direction.UP, 1);
        TileEntity tile = world.getTileEntity(up);
        ModCapabilities.getHeat(tile).setHeat(heat.getHeat());
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

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        burnTime = nbt.getInt("burnTime");
        if (nbt.contains("burnSpeed", Constants.NBT.TAG_INT)) {
            burnSpeed = nbt.getInt("burnSpeed");
        }
        cooldown = nbt.getInt("cooldown");
        heat.setHeat(nbt.getInt("heat"));
        heat.setMaxHeat(nbt.getInt("maxHeat"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = super.write(compound);
        compound.putInt("burnTime", burnTime);
        compound.putInt("burnSpeed", burnSpeed);
        compound.putInt("cooldown", cooldown);
        compound.putInt("heat", heat.getHeat());
        compound.putInt("maxHeat", heat.getMaxHeat());
        return compound;
    }
}
