package cx.rain.mc.forgemod.sinocraft.network.packet;

import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeapot;
import cx.rain.mc.forgemod.sinocraft.network.BaseMessage;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import cx.rain.mc.forgemod.sinocraft.utility.PlayerSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.UUID;

public class TeapotPacket extends BaseMessage {

    public UUID playerId;
    public int slot;
    public boolean hasData;
    public boolean pour;
    public int tea, water;
    public float leaves;

    public TeapotPacket(UUID player, int slot) {
        this.playerId = player;
        this.slot = slot;
        this.pour = false;
        this.hasData = false;
    }

    public TeapotPacket(UUID player, int slot, CapabilityTeapot.CapTeapot cap) {
        this.playerId = player;
        this.slot = slot;
        this.pour = cap.isPouring();
        this.tea = cap.getTea();
        this.water = cap.getWater();
        this.leaves = cap.getLeaves();
        this.hasData = true;
    }

    public TeapotPacket(PacketBuffer buffer) {
        playerId = buffer.readUniqueId();
        int i = buffer.readVarInt();
        hasData = (i & 0b1) == 0b1;
        pour = (i & 0b10) == 0b10;
        slot = (i >> 2) - 1;

        if (hasData) {
            tea = buffer.readInt();
            water = buffer.readInt();
            leaves = buffer.readFloat();
        }
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        int result = (slot < 0 ? 0 : (slot + 1)) << 2;
        if (hasData) {
            result |= 0b1;
        }
        if (pour) {
            result |= 0b10;
        }
        buffer.writeUniqueId(playerId);
        buffer.writeVarInt(result);
        if (hasData) {
            buffer.writeInt(tea);
            buffer.writeInt(water);
            buffer.writeFloat(leaves);
        }
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void handleClient(NetworkEvent.Context context) {
        World world = net.minecraft.client.Minecraft.getInstance().world;
        if (world != null) {
            PlayerEntity player = world.getPlayerByUuid(playerId);
            if (player != null) {
                ItemStack stack;
                if (slot < 0) {
                    stack = player.getHeldItemOffhand();
                } else {
                    stack = player.inventory.mainInventory.get(slot);
                }
                CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
                teapot.setPouring(pour);
                if (hasData) {
                    teapot.setTea(tea);
                    teapot.setWater(water);
                    teapot.setLeaves(leaves);
                }
            }
        }
    }

    public static void syncToClient(PlayerEntity player, PlayerSlot<?> slot, CapabilityTeapot.CapTeapot cap) {
        UUID id = player.getGameProfile().getId();
        int slotIndex = slot.hand == Hand.MAIN_HAND ? slot.index : -1;
        TeapotPacket packet = new TeapotPacket(id, slotIndex, cap);
        Networks.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), packet);
    }

    public static void stopToClient(PlayerEntity player, PlayerSlot<?> slot) {
        UUID id = player.getGameProfile().getId();
        int slotIndex = slot.hand == Hand.MAIN_HAND ? slot.index : -1;
        TeapotPacket packet = new TeapotPacket(id, slotIndex);
        Networks.INSTANCE.send(PacketDistributor.TRACKING_ENTITY_AND_SELF.with(() -> player), packet);
    }
}
