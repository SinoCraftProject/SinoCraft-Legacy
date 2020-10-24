package cx.rain.mc.forgemod.sinocraft.network;

import cx.rain.mc.forgemod.sinocraft.gui.container.ContainerChineseBrush;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec2;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public class CDrawPaper implements IMessage{
    private Pack pack;

    public CDrawPaper(Pack pack) {
        this.pack = pack;
    }

    public CDrawPaper(PacketBuffer buffer) {
        this.pack = new Pack(new Vec2<>(0,0), (byte)0);
        deserialize(buffer);
    }

    @Override
    public void deserialize(PacketBuffer buffer) {
        this.pack.deserialize(buffer);
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        this.pack.serialize(buffer);
    }

    @Override
    public void handler(Supplier<NetworkEvent.Context> context) {
        context.get().enqueueWork(() -> {
            if (context.get().getSender().openContainer instanceof ContainerChineseBrush) {
                ContainerChineseBrush container = (ContainerChineseBrush) context.get().getSender().openContainer;
                CompoundNBT nbt = container.inventory.getStackInSlot(0).getOrCreateTag();
                if (! nbt.contains("pixels")) {
                    nbt.putByteArray("pixels", new byte[16 * 16]);
                }
                int inknum = container.inventory.getStackInSlot(1).getMaxDamage() - container.inventory.getStackInSlot(1).getDamage();
                if (inknum >= this.pack.color && this.pack.pos.x * 16 + this.pack.pos.y < 256) {
                    nbt.getByteArray("pixels")[this.pack.pos.x * 16 + this.pack.pos.y] = this.pack.color;
                    container.inventory.getStackInSlot(1).damageItem(inknum, context.get().getSender(),
                            e -> container.inventory.setInventorySlotContents(1, new ItemStack(Items.INK_STONE.get()))
                    );
                }
                container.inventory.getStackInSlot(0).setTag(nbt);
            }
        });
        context.get().setPacketHandled(true);
    }

    public static class Pack implements IPack{
        private Vec2<Integer> pos;
        private byte color;

        public Pack(Vec2<Integer> pos, byte color) {
            this.pos = pos;
            this.color = color;
        }

        public void serialize(PacketBuffer buffer) {
            buffer.writeInt(pos.x);
            buffer.writeInt(pos.y);
            buffer.writeByte(color);
        }

        @Override
        public void deserialize(PacketBuffer buffer) {
            pos = new Vec2<>(buffer.readInt(), buffer.readInt());
            color = buffer.readByte();
        }
    }
}
