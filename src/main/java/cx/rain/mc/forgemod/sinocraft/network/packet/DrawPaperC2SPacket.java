package cx.rain.mc.forgemod.sinocraft.network.packet;

import cx.rain.mc.forgemod.sinocraft.gui.container.ContainerChineseBrush;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.network.BaseMessage;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.awt.*;
import java.util.Arrays;

public class DrawPaperC2SPacket extends BaseMessage {
    private final Pack pack;

    public DrawPaperC2SPacket(Pack pack) {
        this.pack = pack;
    }

    public DrawPaperC2SPacket(PacketBuffer buffer) {
        this.pack = new Pack(new Point(0, 0), (byte) 0);
        this.pack.deserialize(buffer);
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        this.pack.serialize(buffer);
    }

    @Override
    public void handleServer(NetworkEvent.Context context, ServerPlayerEntity sender) {
        if (sender.openContainer instanceof ContainerChineseBrush) {
            ContainerChineseBrush container = (ContainerChineseBrush) sender.openContainer;
            ItemStack paper = container.inventory.getStackInSlot(0);
            ItemStack ink = container.inventory.getStackInSlot(1);

            if (paper.isEmpty() || ink.isEmpty()) {
                return;
            }

            ItemStack output = container.inventory.getStackInSlot(2);

            if (output.isEmpty()) {
                output = new ItemStack(ModItems.XUAN_PAPER.get());
            }

            CompoundNBT nbt = output.getOrCreateTag();
            if (!nbt.contains("pixels")) {
                nbt.putByteArray("pixels", new byte[32 * 32]);
            }

            if (this.pack.pos.x * 32 + this.pack.pos.y < 1024) {
                nbt.getByteArray("pixels")[this.pack.pos.x * 32 + this.pack.pos.y] = this.pack.color;
                // Todo: Add brush effect.
            }

            if (this.pack.color == 0) {
                if (Arrays.equals(nbt.getByteArray("pixels"), new byte[32 * 32])) {
                    return;
                }
            }

            output.setTag(nbt);
            container.inventory.setInventorySlotContents(2, output);
        }
    }

    public static class Pack implements IPack {
        private Point pos;
        private byte color;

        public Pack(Point pos, byte color) {
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
            pos = new Point(buffer.readInt(), buffer.readInt());
            color = buffer.readByte();
        }
    }
}
