package cx.rain.mc.forgemod.sinocraft.network.packet;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.network.IMessage;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Consumer;
import java.util.function.Supplier;

//TODO
public class GetRecipeS2CPacket implements IMessage {
    private static Consumer<IRecipe<?>> callback;
    private Pack pack;

    public static void setCallback(Consumer<IRecipe<?>> call) {
        callback = call;
    }

    public GetRecipeS2CPacket(Pack pack) {
        this.pack = pack;
    }

    public GetRecipeS2CPacket(PacketBuffer buffer) {
        this.pack = new Pack(null);
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
            if (callback != null) {
                callback.accept(pack.recipe);
                callback = null;
            }
            else
                SinoCraft.getLogger().warn("Do you forget add a callback to me?");
        });
        context.get().setPacketHandled(true);
    }

    public static class Pack implements IPack {
        private IRecipe recipe;

        public Pack(IRecipe<?> recipe) {
            this.recipe = recipe;
        }

        public void serialize(PacketBuffer buffer) {
            buffer.writeResourceLocation(recipe.getSerializer().getRegistryName());
            buffer.writeResourceLocation(recipe.getId());
            recipe.getSerializer().write(buffer, recipe);
        }

        @Override
        public void deserialize(PacketBuffer buffer) {
            recipe = Registry.RECIPE_SERIALIZER.getOrDefault(buffer.readResourceLocation()).read(buffer.readResourceLocation(), buffer);
        }
    }
}
