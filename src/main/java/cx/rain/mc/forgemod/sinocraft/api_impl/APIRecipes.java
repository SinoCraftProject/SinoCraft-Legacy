package cx.rain.mc.forgemod.sinocraft.api_impl;

import com.google.gson.JsonElement;
import cx.rain.mc.forgemod.sinocraft.api.crafting.*;
import cx.rain.mc.forgemod.sinocraft.api.crafting.cooking.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.cooking.ICookingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.grinding.IGrindingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.grinding.IGrindingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.steaming.ISteamingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.steaming.ISteamingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.crafting.*;
import cx.rain.mc.forgemod.sinocraft.crafting.cooking.CookingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.grinding.GrindingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.SoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.steaming.SteamingRecipe;
import cx.rain.mc.forgemod.sinocraft.utility.CraftingHelper;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

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
    public IRecipeType<ISteamingRecipe> getSteamerRecipe() {
        return ModRecipes.STEAMER;
    }

    @Override
    public IRecipeType<IGrindingRecipe> getMillRecipe() {
        return ModRecipes.MILL;
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
    public IModRecipeSerializer<? extends ISteamingRecipe> getSteamerSerializer() {
        return ModRecipes.STEAMER_SERIALIZER.get();
    }

    @Override
    public IModRecipeSerializer<? extends IGrindingRecipe> getMillSerializer() {
        return ModRecipes.MILL_SERIALIZER.get();
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
    public ISteamingRecipeBuilder newSteamerRecipe(ResourceLocation id) {
        return SteamingRecipe.builder(id);
    }

    @Override
    public IGrindingRecipeBuilder newMillRecipe(ResourceLocation id) {
        return GrindingRecipe.builder(id);
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

    @Override
    public JsonElement serializeItem(ItemStack stack) {
        return CraftingHelper.serializeItem(stack);
    }

    @Override
    public JsonElement serializeFluid(FluidStack stack) {
        return CraftingHelper.serializeFluid(stack);
    }

    @Override
    public JsonElement serializeItemIngredient(ICountIngredient ingredient) {
        return CraftingHelper.serializeIngredient(ingredient);
    }

    @Override
    public JsonElement serializeFluidIngredient(IFluidIngredient ingredient) {
        return CraftingHelper.serializeFluidIngredient(ingredient);
    }

    @Override
    public ItemStack deserializeItem(JsonElement json) {
        return CraftingHelper.deserializeItem(json);
    }

    @Override
    public FluidStack deserializeFluid(JsonElement json) {
        return CraftingHelper.deserializeFluid(json);
    }

    @Override
    public Ingredient deserializeVanillaIngredient(JsonElement json) {
        return CraftingHelper.deserializeVanillaIngredient(json);
    }

    @Override
    public ICountIngredient deserializeItemIngredient(JsonElement json) {
        return CraftingHelper.deserializeIngredient(json);
    }

    @Override
    public IFluidIngredient deserializeFluidIngredient(JsonElement json) {
        return CraftingHelper.deserializeFluidIngredient(json);
    }
}
