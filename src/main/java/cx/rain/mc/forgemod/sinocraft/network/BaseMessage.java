package cx.rain.mc.forgemod.sinocraft.network;

import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.network.PacketBuffer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public abstract class BaseMessage {

    public BaseMessage(PacketBuffer buffer) {}

    public BaseMessage() {}

    public abstract void serialize(PacketBuffer buffer);

    @OnlyIn(Dist.CLIENT)
    public void handleClient(NetworkEvent.Context context) {
        throw new IllegalStateException("Handle a server packet " + getClass().getSimpleName() + " on client side.");
    }

    public void handleServer(NetworkEvent.Context context, ServerPlayerEntity sender) {
        throw new IllegalStateException("Handle a client packet " + getClass().getSimpleName() + " on server side.");
    }

    public void handler(Supplier<NetworkEvent.Context> context) {
        NetworkEvent.Context ctx = context.get();
        if (ctx.getDirection().getReceptionSide().isServer()) {
            ctx.enqueueWork(() -> handleServer(ctx, ctx.getSender()));
        } else {
            ctx.enqueueWork(() -> handleClient(ctx));
        }
        ctx.setPacketHandled(true);
    }

    public interface IPack {
        void serialize(PacketBuffer buffer);
        void deserialize(PacketBuffer buffer);
    }
}
