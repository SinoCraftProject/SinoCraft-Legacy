package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.block.IWindEnergyReceiver;
import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import static cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityWindEnergy.CAPABILITY;

public class TileEntityBellows extends BaseTileEntityUpdatable implements IWindEnergy {
    private int we = 0;
    private int cdLowerTemp = 0;
    private int cdSendEnergy = 0;
    private boolean shouldLink = true;
    private Direction linked = null;

    public TileEntityBellows() {
        super(ModTileEntities.BELLOWS.get());
    }

    public void addWe() {
        we++;
    }

    @Override
    @Nonnull
    public <T> LazyOptional<T> getCapability(Capability<T> cap, @Nullable Direction side) {
        if (cap == CAPABILITY) {
            return LazyOptional.of(() -> this).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void onTick() {
        if (world == null) {
            return;
        }
        if (cdLowerTemp > 0) {
            cdLowerTemp--;
        }
        if (we > 0 && cdLowerTemp <= 0) {
            we--;
            cdLowerTemp = 200;
        }
        if (shouldLink && !world.isRemote) {
            shouldLink = false;
            System.out.println("tryLinkAround");
            Direction direction = getBlockState().get(HorizontalBlock.HORIZONTAL_FACING);
            if (!tryLink(direction)
                    && !tryLink(direction.getOpposite())
                    && !tryLink(direction.rotateY())
                    && !tryLink(direction.rotateYCCW())) {
                linked = null;
            }
        }
        if (linked != null) {
            TileEntity te = world.getTileEntity(pos.offset(linked));
            if (te instanceof IWindEnergyReceiver) {
                cdSendEnergy++;
                IWindEnergyReceiver receiver = (IWindEnergyReceiver) te;
                if (cdSendEnergy >= Math.max(3, receiver.getWindEnergyReceiveInterval())) {
                    cdLowerTemp -= receiver.receiveWindEnergy(linked.getOpposite(), 1);
                }
            } else {
                linked = null;
                shouldLink = true;
            }
        }
        markDirty();
    }

    public boolean tryLink(Direction direction) {
        if (world == null) {
            return false;
        }
        TileEntity te = world.getTileEntity(pos.offset(direction));
        if (te instanceof IWindEnergyReceiver && ((IWindEnergyReceiver) te).canSupportWindEnergyProvider(direction.getOpposite(), this)) {
            this.linked = direction;
            updateBlockState(HorizontalBlock.HORIZONTAL_FACING, direction);
            System.out.println("link succeed: " + linked);
            return true;
        }
        return false;
    }

    public boolean shouldLink() {
        return shouldLink;
    }

    @Override
    public int getWindEnergy() {
        return we;
    }

    @Override
    public void setWindEnergy(int we) {
        this.we = we;
    }

    @Override
    public void resetWindEnergy() {
        we = 0;
        cdSendEnergy = 0;
        cdLowerTemp = 200;
    }

    @Override
    public void addWindEnergy(int we) {
        this.we += we;
    }

    @Override
    public void subWindEnergy(int we) {
        this.we = Math.max(0, this.we - we);
    }

    @Nullable
    public Direction getLinkedDirection() {
        if (world == null) {
            return linked;
        }
        if (linked == null) {
            return null;
        }
        if (world.getTileEntity(pos.offset(linked)) instanceof IWindEnergyReceiver) {
            return linked;
        }
        return null;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        shouldLink = nbt.getBoolean("shouldLink");
        if (!shouldLink) {
            linked = nbt.contains("linked", Constants.NBT.TAG_INT) ? Direction.byHorizontalIndex(nbt.getInt("linked")) : null;
        }
        we = nbt.getInt("we");
        cdLowerTemp = nbt.getInt("cdLowerTemp");
        cdSendEnergy = nbt.getInt("cdSendEnergy");
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putBoolean("shouldLink", shouldLink);
        if (!shouldLink && linked != null) {
            compound.putInt("linked", linked.getHorizontalIndex());
        }
        compound.putInt("we", we);
        compound.putInt("cdLowerTemp", cdLowerTemp);
        compound.putInt("cdSendEnergy", cdSendEnergy);
        return super.write(compound);
    }
}
