package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.CountIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.FluidIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipeSerializer;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.registries.ForgeRegistryEntry;

public class SoakingSerializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IModRecipeSerializer<SoakingRecipe> {

    public static final SoakingSerializer SERIALIZER = new SoakingSerializer();

    @Override
    public SoakingRecipe read(ResourceLocation id, JsonObject json) {
        SoakingRecipe.Builder builder = new SoakingRecipe.Builder(id);
        builder.setTime(json.get("time").getAsInt());
        JsonObject ingredients = json.getAsJsonObject("ingredients");
        builder.setInput(CraftingHelper.deserializeIngredient(ingredients.get("item")));
        builder.setInput(CraftingHelper.deserializeFluidIngredient(ingredients.get("fluid")));
        JsonObject results = json.getAsJsonObject("results");
        if (results.has("item")) {
            builder.setOutput(CraftingHelper.deserializeItem(results.get("item")));
        }
        if (results.has("fluid")) {
            builder.setOutput(CraftingHelper.deserializeFluid(results.get("fluid")));
        }
        return builder.build();
    }

    @Override
    public SoakingRecipe read(ResourceLocation id, PacketBuffer buffer) {
        SoakingRecipe.Builder builder = new SoakingRecipe.Builder(id);
        builder.setTime(buffer.readVarInt());
        builder.setInput(CountIngredient.read(buffer));
        builder.setInput(FluidIngredient.read(buffer));
        byte b = buffer.readByte();
        if ((b & 0b01) == 0b01) {
            builder.setOutput(buffer.readItemStack());
        }
        if ((b & 0b10) == 0b10) {
            builder.setOutput(buffer.readFluidStack());
        }
        return builder.build();
    }

    @Override
    public void write(PacketBuffer buffer, SoakingRecipe recipe) {
        buffer.writeVarInt(recipe.time);
        recipe.inputItem.write(buffer);
        recipe.inputFluid.write(buffer);
        boolean hasItem = !recipe.outputItem.isEmpty();
        boolean hasFluid = !recipe.outputFluid.isEmpty();
        byte b = 0b00;
        if (hasItem) b |= 0b01;
        if (hasFluid) b |= 0b10;
        buffer.writeByte(b);
        if (hasItem) {
            buffer.writeItemStack(recipe.outputItem, false);
        }
        if (hasFluid) {
            buffer.writeFluidStack(recipe.outputFluid);
        }
    }

    @Override
    public void write(JsonObject json, SoakingRecipe recipe) {
        json.addProperty("time", recipe.time);
        JsonObject ingredients = new JsonObject();
        ingredients.add("item", CraftingHelper.serializeIngredient(recipe.inputItem));
        ingredients.add("fluid", CraftingHelper.serializeFluidIngredient(recipe.inputFluid));
        json.add("ingredients", ingredients);
        JsonObject results = new JsonObject();
        if (!recipe.outputItem.isEmpty()) {
            results.add("item", CraftingHelper.serializeItem(recipe.outputItem));
        }
        if (!recipe.outputFluid.isEmpty()) {
            results.add("fluid", CraftingHelper.serializeFluid(recipe.outputFluid));
        }
        json.add("results", results);
    }
}
