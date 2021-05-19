package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.SinoCraftAPI;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public class SteamerRecipe implements ISteamerRecipe {

    private final ResourceLocation id;
    int time, minHeat, maxHeat;
    Ingredient input;
    ItemStack output, adust;

    static Builder builder(ResourceLocation id) {
        return new Builder(id);
    }

    SteamerRecipe(ResourceLocation id) {
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
        SteamerSerializer.SERIALIZER.write(json, this);
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
        return SteamerSerializer.SERIALIZER;
    }

    @Override
    public IRecipeType<?> getType() {
        return SinoCraftAPI.getRecipes().getSteamerRecipe();
    }

    public static class Builder implements ISteamerRecipeBuilder {
        private final SteamerRecipe recipe;

        Builder(ResourceLocation id) {
            recipe = new SteamerRecipe(id);
        }

        @Override
        public Builder setInput(Ingredient ingredient) {
            recipe.input = ingredient;
            return this;
        }

        @Override
        public Builder setOutput(ItemStack stack) {
            recipe.output = stack;
            return this;
        }

        @Override
        public Builder setAdustOutput(ItemStack stack) {
            recipe.adust = stack;
            return this;
        }

        @Override
        public Builder setTime(int time) {
            recipe.time = time;
            return this;
        }

        @Override
        public Builder setHeat(int min, int max) {
            recipe.minHeat = min;
            recipe.maxHeat = max;
            return this;
        }

        @Override
        public SteamerRecipe build() {
            return recipe;
        }
    }
}
