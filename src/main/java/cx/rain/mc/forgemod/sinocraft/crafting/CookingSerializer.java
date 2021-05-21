package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICountIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipeSerializer;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

public class CookingSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IModRecipeSerializer<CookingRecipe> {

    static final CookingSerializer SERIALIZER = new CookingSerializer();

    @Override
    public CookingRecipe read(ResourceLocation recipeId, JsonObject json) {
        CookingRecipe.Builder builder = new CookingRecipe.Builder(recipeId);
        JsonArray ingredients = json.getAsJsonArray("ingredients");
        for (JsonElement element : ingredients) {
            builder.addInput(CraftingHelper.deserializeIngredient(element));
        }
        builder.setTime(json.get("time").getAsInt());
        builder.setOutput(CraftingHelper.deserializeItem(json.get("result")));
        if (json.has("adust")) {
            builder.setAdustOutput(CraftingHelper.deserializeItem(json.get("adust")));
        }
        if (json.has("heat")) {
            JsonArray heat = json.getAsJsonArray("heat");
            builder.setHeat(heat.get(0).getAsInt(), heat.get(1).getAsInt());
        } else if (json.has("minThermal") && json.has("maxThermal")) {
            builder.setHeat(json.get("minThermal").getAsInt(), json.get("maxThermal").getAsInt());
        } else {
            throw new JsonSyntaxException("Not found heat range in recipe " + recipeId + ".");
        }
        return (CookingRecipe) builder.build();
    }

    @Nullable
    @Override
    public CookingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
        CookingRecipe.Builder builder = new CookingRecipe.Builder(recipeId);

        int size = buffer.readVarInt();
        for (int i = 0; i < size; i++) {
            Ingredient ingredient = Ingredient.read(buffer);
            int count = buffer.readVarInt();
            builder.addInput(ingredient, count);
        }

        return (CookingRecipe) builder.setOutput(buffer.readItemStack())
                .setAdustOutput(buffer.readItemStack())
                .setHeat(buffer.readVarInt(), buffer.readVarInt())
                .setTime(buffer.readVarInt()).build();
    }

    @Override
    public void write(PacketBuffer buffer, CookingRecipe recipe) {
        buffer.writeVarInt(recipe.stacks.size());
        for (ICountIngredient stack : recipe.stacks) {
            stack.getIngredient().write(buffer);
            buffer.writeVarInt(stack.getCount());
        }
        buffer.writeItemStack(recipe.recipeResult);
        buffer.writeItemStack(recipe.adustResult);
        buffer.writeVarInt(recipe.minThermal);
        buffer.writeVarInt(recipe.maxThermal);
        buffer.writeVarInt(recipe.time);
    }

    @Override
    public void write(JsonObject json, CookingRecipe recipe) {
        JsonArray ingredients = new JsonArray();
        recipe.stacks.stream()
                .map(CraftingHelper::serializeIngredient)
                .forEach(ingredients::add);
        json.add("ingredients", ingredients);
        json.addProperty("time", recipe.time);
        json.add("result", CraftingHelper.serializeItem(recipe.recipeResult));
        json.add("adust", CraftingHelper.serializeItem(recipe.adustResult));
        JsonArray heat = new JsonArray();
        heat.add(recipe.minThermal);
        heat.add(recipe.maxThermal);
        json.add("heat", heat);
    }
}
