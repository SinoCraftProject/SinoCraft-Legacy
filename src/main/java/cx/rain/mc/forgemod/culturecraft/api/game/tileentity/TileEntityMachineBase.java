package cx.rain.mc.forgemod.culturecraft.api.game.tileentity;

import cx.rain.mc.forgemod.culturecraft.api.game.interfaces.IMachine;
import cx.rain.mc.forgemod.culturecraft.block.BlockStove;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.server.management.PlayerChunkMapEntry;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.WorldServer;

import javax.annotation.Nullable;

/**
 * machine's default tile entity
 * implements some basic method
 */
public abstract class TileEntityMachineBase extends TileEntity implements IMachine, ITickable {
    protected MachineState state;
    protected boolean isChange=false;

    /**
     * if <code>isChange</code> is true,it will send the packet to client and saved to disk later
     */
    protected void updateData(){
        if(isChange){
            this.syncToTrackingClients();
            this.markDirty();
            isChange=false;
        }
    }

    @Override
    public MachineState getWorkingState() {
        return state;
    }

    @Override
    public void setWorkingState(MachineState state) {
        this.state=state;
        isChange=true;
        this.updateData();
    }

    /**
     * Get the facing of the specified block
     * @param worldIn the world of the block
     * @param pos the position of the block
     * @return The facing of the specified block
     */
    public static EnumFacing getFacing(IBlockAccess worldIn, BlockPos pos){
        return worldIn.getBlockState(pos).getValue(BlockStove.FACING);
    }

    @Override
    public void readFromNBT(NBTTagCompound compound) {
        super.readFromNBT(compound);
        this.state=MachineState.valueOf(compound.getString("MachineState")==""?"CLOSE":compound.getString("MachineState"));
    }

    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound compound) {
        compound.setString("MachineState",state.toString());
        return super.writeToNBT(compound);
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return this.writeToNBT(new NBTTagCompound());
    }

    @Nullable
    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.getPos(),1,this.getUpdateTag());
    }

    protected void syncToTrackingClients() {
        if (!this.world.isRemote) {
            SPacketUpdateTileEntity packet = this.getUpdatePacket();
            PlayerChunkMapEntry trackingEntry = ((WorldServer)this.world).getPlayerChunkMap().getEntry(this.pos.getX() >> 4, this.pos.getZ() >> 4);
            if (trackingEntry != null) {
                for (EntityPlayerMP player : trackingEntry.getWatchingPlayers()) {
                    player.connection.sendPacket(packet);
                }
            }
        }
    }

    @Override
    public void onDataPacket(NetworkManager manager, SPacketUpdateTileEntity packet) {
        this.readFromNBT(packet.getNbtCompound());
    }
}
