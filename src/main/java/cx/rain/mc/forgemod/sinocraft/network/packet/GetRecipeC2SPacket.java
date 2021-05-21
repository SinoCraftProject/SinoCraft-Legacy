package cx.rain.mc.forgemod.sinocraft.network.packet;

import cx.rain.mc.forgemod.sinocraft.network.IMessage;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

import java.util.function.Supplier;

public class GetRecipeC2SPacket implements IMessage {
    private Pack pack;

    public GetRecipeC2SPacket(Pack pack) {
        this.pack = pack;
    }

    public GetRecipeC2SPacket(PacketBuffer buffer) {
        this.pack = new Pack(new ResourceLocation(""));
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
            IRecipe<?> recipe = context.get().getSender().world.getRecipeManager().getRecipe(this.pack.id)
                    .orElseThrow(() -> new IllegalStateException("Not found recipe " + this.pack.id.toString()));
            Networks.INSTANCE.send(PacketDistributor.PLAYER.with(context.get()::getSender),new GetRecipeS2CPacket(new GetRecipeS2CPacket.Pack(recipe)));
        });
        context.get().setPacketHandled(true);
    }

    public static class Pack implements IPack {
        private ResourceLocation id;

        public Pack(ResourceLocation id) {
            this.id = id;
        }

        public void serialize(PacketBuffer buffer) {
            buffer.writeResourceLocation(id);
        }

        @Override
        public void deserialize(PacketBuffer buffer) {
            id = buffer.readResourceLocation();
        }
    }
}
