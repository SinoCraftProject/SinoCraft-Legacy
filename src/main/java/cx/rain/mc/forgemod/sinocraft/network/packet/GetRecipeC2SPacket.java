package cx.rain.mc.forgemod.sinocraft.network.packet;

import cx.rain.mc.forgemod.sinocraft.network.BaseMessage;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.network.PacketDistributor;

public class GetRecipeC2SPacket extends BaseMessage {

    private final Pack pack;

    public GetRecipeC2SPacket(Pack pack) {
        this.pack = pack;
    }

    public GetRecipeC2SPacket(PacketBuffer buffer) {
        this.pack = new Pack(new ResourceLocation(""));
        this.pack.deserialize(buffer);
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        this.pack.serialize(buffer);
    }

    @Override
    public void handleServer(NetworkEvent.Context context, ServerPlayerEntity sender) {
        IRecipe<?> recipe = sender.world.getRecipeManager()
                .getRecipe(this.pack.id)
                .orElseThrow(() -> new IllegalStateException("Not found recipe " + this.pack.id.toString()));
        Networks.INSTANCE.send(PacketDistributor.PLAYER.with(context::getSender), new GetRecipeS2CPacket(new GetRecipeS2CPacket.Pack(recipe)));
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
