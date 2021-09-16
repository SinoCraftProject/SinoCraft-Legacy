package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.state.Property;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import javax.annotation.Nullable;
import java.util.Objects;

public abstract class BaseTileEntityUpdatable extends TileEntity implements ITickableTileEntity {

    private boolean isDirty = false;
    private BlockState newState;

    public BaseTileEntityUpdatable(TileEntityType<?> tileEntityTypeIn) {
        super(tileEntityTypeIn);
    }

    @Override
    public final void tick() {
        if (world != null && !world.isRemote) {
            newState = null;
        }
        onTick();
        checkDirtyAndUpdate();
    }

    public abstract void onTick();

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, getUpdateTag());
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
        isDirty = true;
    }

    protected <T extends Comparable<T>> BaseTileEntityUpdatable updateBlockState(Property<T> property, T value) {
        if (newState == null) {
            newState = getBlockState();
        }
        newState = newState.with(property, value);
        markDirty();
        return this;
    }

    protected void checkDirtyAndUpdate() {
        if (world != null && isDirty && !world.isRemote) {
            BlockState oldState = world.getBlockState(pos);
            if (newState != null && !Objects.equals(oldState, newState)) {
                world.setBlockState(pos, newState, 2);
                world.notifyBlockUpdate(pos, oldState, newState, 2);
                System.out.println("Update State from " + oldState + " to " + newState);
            } else {
                world.notifyBlockUpdate(pos, oldState, oldState, 2);
            }
        }
        super.markDirty();
        isDirty = false;
    }
}
