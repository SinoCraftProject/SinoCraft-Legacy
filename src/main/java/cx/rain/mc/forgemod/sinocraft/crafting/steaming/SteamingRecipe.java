package cx.rain.mc.forgemod.sinocraft.crafting.steaming;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SteamingRecipe implements ISteamerRecipe {

    private final ResourceLocation id;
    int time, minHeat, maxHeat;
    Ingredient input;
    ItemStack output, adust;

    public static ISteamerRecipeBuilder builder(ResourceLocation id) {
        return new Builder(id);
    }

    SteamingRecipe(ResourceLocation id) {
        this.id = id;
        this.adust = new ItemStack(ModItems.ADUST_FOOD.get());
    }

    @Override
    public int getCookingTime() {
        return time;
    }

    @Override
    public int getMinHeat() {
        return minHeat;
    }

    @Override
    public int getMaxHeat() {
        return maxHeat;
    }

    @Override
    public void serialize(JsonObject json) {
        SteamingSerializer.SERIALIZER.write(json, this);
    }

    @Override
    public boolean matches(IExtendedRecipeInventory inv, World worldIn) {
        return inv.getItemCount() > 0 && input.test(inv.getInputItem(0));
    }

    @Override
    public ItemStack getCraftingResult(IExtendedRecipeInventory inv) {
        return inv.getMaxHeat() > maxHeat ? adust : output;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ItemStack getAdustOutput() {
        return adust;
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return NonNullList.withSize(1, input);
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SteamingSerializer.SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.STEAMER;
    }

    static class Builder implements ISteamerRecipeBuilder {
        private final SteamingRecipe recipe;

        Builder(ResourceLocation id) {
            recipe = new SteamingRecipe(id);
        }

        @Override
        public ISteamerRecipeBuilder setInput(Ingredient ingredient) {
            recipe.input = ingredient;
            return this;
        }

        @Override
        public ISteamerRecipeBuilder setOutput(ItemStack stack) {
            recipe.output = stack;
            return this;
        }

        @Override
        public ISteamerRecipeBuilder setAdustOutput(ItemStack stack) {
            recipe.adust = stack;
            return this;
        }

        @Override
        public ISteamerRecipeBuilder setTime(int time) {
            recipe.time = time;
            return this;
        }

        @Override
        public ISteamerRecipeBuilder setHeat(int min, int max) {
            recipe.minHeat = min;
            recipe.maxHeat = max;
            return this;
        }

        @Override
        public ISteamerRecipe build() {
            return recipe;
        }
    }
}
