package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipeSerializer;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class SteamerSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IModRecipeSerializer<SteamerRecipe> {

    public static final SteamerSerializer SERIALIZER = new SteamerSerializer();

    @Override
    public void write(JsonObject json, SteamerRecipe recipe) {
        json.addProperty("time", recipe.time);
        json.add("ingredient", recipe.input.serialize());
        json.add("result", CraftingHelper.serializeItem(recipe.output));
        json.add("adust", CraftingHelper.serializeItem(recipe.adust));
        JsonArray heat = new JsonArray();
        heat.add(recipe.minHeat);
        heat.add(recipe.maxHeat);
        json.add("heat", heat);
    }

    @Override
    public SteamerRecipe read(ResourceLocation recipeId, JsonObject json) {
        SteamerRecipe.Builder builder = SteamerRecipe.builder(recipeId);
        if (json.has("adust")) {
            builder.setAdustOutput(CraftingHelper.deserializeItem(json.get("adust")));
        }
        JsonArray heat = json.getAsJsonArray("heat");
        return builder.setTime(json.get("time").getAsInt())
                .setInput(CraftingHelper.deserializeVanillaIngredient(json.get("ingredient")))
                .setOutput(CraftingHelper.deserializeItem(json.get("result")))
                .setHeat(heat.get(0).getAsInt(), heat.get(1).getAsInt())
                .build();
    }

    @Nullable
    @Override
    public SteamerRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        return SteamerRecipe.builder(recipeId)
                .setTime(buffer.readVarInt())
                .setInput(Ingredient.read(buffer))
                .setOutput(buffer.readItemStack())
                .setAdustOutput(buffer.readItemStack())
                .setHeat(buffer.readVarInt(), buffer.readVarInt())
                .build();
    }

    @Override
    public void write(PacketBuffer buffer, SteamerRecipe recipe) {
        buffer.writeVarInt(recipe.time);
        recipe.input.write(buffer);
        buffer.writeItemStack(recipe.output);
        buffer.writeItemStack(recipe.adust);
        buffer.writeVarInt(recipe.minHeat);
        buffer.writeVarInt(recipe.maxHeat);
    }
}
