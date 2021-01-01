package cx.rain.mc.forgemod.sinocraft.api.base;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IMachine;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;

public abstract class TileEntityMachineBase extends TileEntity implements ITickableTileEntity, IMachine {
    protected IMachine.MachineState state;

    public TileEntityMachineBase(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public MachineState getWorkingState() {
        return state;
    }

    @Override
    public void setWorkingState(MachineState state) {
        this.state=state;
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos,1,getUpdateTag());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        handleUpdateTag(getBlockState(), pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return this.write(new CompoundNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        this.read(state, tag);
    }

    @Override
    public void markDirty() {
        world.notifyBlockUpdate(pos, world.getBlockState(pos), world.getBlockState(pos), 2);
        super.markDirty();
    }
}
