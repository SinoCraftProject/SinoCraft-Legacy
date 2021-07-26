package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipeSerializer;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

import javax.annotation.Nullable;

public class MillSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IModRecipeSerializer<MillRecipe> {

    static final MillSerializer SERIALIZER = new MillSerializer();

    @Override
    public MillRecipe read(ResourceLocation recipeId, JsonObject json) {
        return (MillRecipe) MillRecipe.builder(recipeId)
                .setInput(CraftingHelper.deserializeVanillaIngredient(json.get("input")))
                .setOutput(CraftingHelper.deserializeItem(json.get("result")))
                .setTime(json.get("time").getAsInt())
                .build();
    }

    @Nullable
    @Override
    public MillRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return (MillRecipe) MillRecipe.builder(recipeId)
                .setInput(Ingredient.read(buffer))
                .setOutput(buffer.readItemStack())
                .setTime(buffer.readVarInt())
                .build();
    }

    @Override
    public void write(PacketBuffer buffer, MillRecipe recipe) {
        recipe.getInput().write(buffer);
        buffer.writeItemStack(recipe.getRecipeOutput());
        buffer.writeVarInt(recipe.getTime());
    }

    @Override
    public void write(JsonObject json, MillRecipe recipe) {
        json.add("input", recipe.input.serialize());
        json.add("result", CraftingHelper.serializeItem(recipe.getRecipeOutput()));
        json.addProperty("time", recipe.getTime());
    }
}
