package cx.rain.mc.forgemod.sinocraft.api.crafting;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;

public interface ISinoRecipes {

    Lazy<ISinoRecipes> INSTANCE = new Lazy<>();

    IRecipeType<ISoakingRecipe> getSoakingRecipe();
    IRecipeType<ICookingRecipe> getCookingRecipe();
    IRecipeType<ISteamerRecipe> getSteamerRecipe();

    IModRecipeSerializer<? extends ISoakingRecipe> getSoakingSerializer();
    IModRecipeSerializer<? extends ICookingRecipe> getCookingSerializer();
    IModRecipeSerializer<? extends ISteamerRecipe> getSteamerSerializer();

    ICookingRecipeBuilder newCookingRecipe(ResourceLocation id);
    ISoakingRecipeBuilder newSoakingRecipe(ResourceLocation id);
    ISteamerRecipeBuilder newSteamerRecipe(ResourceLocation id);

    ICountIngredient newCountIngredient(Ingredient ingredient, int count);
    IFluidIngredient newFluidIngredient(ITag<Fluid> tag, int amount);
    IFluidIngredient newFluidIngredient(Fluid fluid, int amount);
}
