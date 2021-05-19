package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.api.crafting.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public enum APIRecipes implements ISinoRecipes {

    INSTANCE;

    @Override
    public IRecipeType<ISoakingRecipe> getSoakingRecipe() {
        return ModRecipes.SOAKING;
    }

    @Override
    public IRecipeType<ICookingRecipe> getCookingRecipe() {
        return ModRecipes.COOKING;
    }

    @Override
    public IRecipeType<ISteamerRecipe> getSteamerRecipe() {
        return ModRecipes.STEAMER;
    }

    @Override
    public IModRecipeSerializer<? extends ISoakingRecipe> getSoakingSerializer() {
        return ModRecipes.SOAKING_SERIALIZER.get();
    }

    @Override
    public IModRecipeSerializer<? extends ICookingRecipe> getCookingSerializer() {
        return ModRecipes.COOKING_SERIALIZER.get();
    }

    @Override
    public IModRecipeSerializer<? extends ISteamerRecipe> getSteamerSerializer() {
        return ModRecipes.STEAMER_SERIALIZER.get();
    }

    @Override
    public ICookingRecipeBuilder newCookingRecipe(ResourceLocation id) {
        return CookingRecipe.builder(id);
    }

    @Override
    public ISoakingRecipeBuilder newSoakingRecipe(ResourceLocation id) {
        return SoakingRecipe.builder(id);
    }

    @Override
    public ISteamerRecipeBuilder newSteamerRecipe(ResourceLocation id) {
        return SteamerRecipe.builder(id);
    }

    @Override
    public ICountIngredient newCountIngredient(Ingredient ingredient, int count) {
        return new CountIngredient(ingredient, count);
    }

    @Override
    public IFluidIngredient newFluidIngredient(ITag<Fluid> tag, int amount) {
        return new FluidIngredient(tag, amount);
    }

    @Override
    public IFluidIngredient newFluidIngredient(Fluid fluid, int amount) {
        return new FluidIngredient(fluid, amount);
    }
}
