package cx.rain.mc.forgemod.sinocraft.network;

import net.minecraft.network.PacketBuffer;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Supplier;

public interface IMessage {
    void deserialize(PacketBuffer buffer);
    void serialize(PacketBuffer buffer);
    void handler(Supplier<NetworkEvent.Context> context);

    interface IPack {
        void serialize(PacketBuffer buffer);
        void deserialize(PacketBuffer buffer);
    }
}
