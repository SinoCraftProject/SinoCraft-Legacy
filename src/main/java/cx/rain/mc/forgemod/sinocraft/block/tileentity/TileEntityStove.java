package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityHeat;
import cx.rain.mc.forgemod.sinocraft.capability.Heat;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.capability.empty.NoWind;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityWindEnergy.CAPABILITY;
import static cx.rain.mc.forgemod.sinocraft.block.BlockStove.BURNING;

public class TileEntityStove extends TileEntity implements ITickableTileEntity, cx.rain.mc.forgemod.sinocraft.api.block.ITileEntityStove {

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
            if (! isBurning() && world.getBlockState(pos).get(BURNING))
                    world.setBlockState(pos, world.getBlockState(pos).with(BURNING, false));
            if (isBurning() && ! world.getBlockState(pos).get(BURNING))
                world.setBlockState(pos, world.getBlockState(pos).with(BURNING, true));
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
        BlockPos left = getPos().offset(getBlockState().get(BlockStove.HORIZONTAL_FACING).rotateY(), 1);
        BlockPos right = getPos().offset(getBlockState().get(BlockStove.HORIZONTAL_FACING).rotateYCCW(), 1);

        internalModify(left);
        internalModify(right);
    }

    private void internalModify(BlockPos pos) {
        assert world != null;
        TileEntity tile = world.getTileEntity(pos);
        if (tile != null && tile.getCapability(CAPABILITY).isPresent()) {
            burnSpeed += tile.getCapability(CAPABILITY).orElse(NoWind.INSTANCE).getWindEnergy();
        }
    }

    private void burn() {
        assert world != null;
        if (isBurning()) {
            burnTime = Math.max(0, burnTime - burnSpeed);
        }
        heat.addHeat(burnSpeed);
        burnSpeed = 1;

        BlockPos up = getPos().offset(Direction.UP, 1);
        TileEntity tile = world.getTileEntity(up);
        CapabilityHelper.getHeat(tile).setHeat(heat.getHeat());
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

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        burnTime = nbt.getInt("burnTime");
        if (nbt.contains("burnSpeed", Constants.NBT.TAG_INT)) {
            burnSpeed = nbt.getInt("burnSpeed");
        }
        cooldown = nbt.getInt("cooldown");
        heat.setHeat(nbt.getInt("heat"));
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = super.write(compound);
        compound.putInt("burnTime", burnTime);
        compound.putInt("burnSpeed", burnSpeed);
        compound.putInt("cooldown", cooldown);
        compound.putInt("heat", heat.getHeat());
        return compound;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityHeat.CAPABILITY) {
            return LazyOptional.of(()->heat).cast();
        }
        return super.getCapability(cap, side);
    }
}
