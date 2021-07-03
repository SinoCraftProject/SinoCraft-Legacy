package cx.rain.mc.forgemod.sinocraft.network.packet;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.network.BaseMessage;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.fml.network.NetworkEvent;

import java.util.function.Consumer;

public class GetRecipeS2CPacket extends BaseMessage {

    private static Consumer<IRecipe<?>> callback;

    private final Pack pack;

    public static void setCallback(Consumer<IRecipe<?>> call) {
        callback = call;
    }

    public GetRecipeS2CPacket(Pack pack) {
        this.pack = pack;
    }

    public GetRecipeS2CPacket(PacketBuffer buffer) {
        this.pack = new Pack(null);
        this.pack.deserialize(buffer);
    }

    @Override
    public void serialize(PacketBuffer buffer) {
        this.pack.serialize(buffer);
    }

    @Override
    public void handleClient(NetworkEvent.Context context) {
        if (callback != null) {
            callback.accept(pack.recipe);
            callback = null;
        }
        else
            SinoCraft.getLogger().warn("Do you forget add a callback to me?");
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
