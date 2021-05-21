package cx.rain.mc.forgemod.sinocraft.api.crafting;

import com.google.gson.*;
import cx.rain.mc.forgemod.sinocraft.api.SinoCraftAPI;
import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.registries.ForgeRegistries;

public interface ISinoRecipes {

    Lazy<ISinoRecipes> INSTANCE = new Lazy<>();

    IRecipeType<ISoakingRecipe> getSoakingRecipe();
    IRecipeType<ICookingRecipe> getCookingRecipe();
    IRecipeType<ISteamerRecipe> getSteamerRecipe();
    IRecipeType<IMillRecipe> getMillRecipe();

    IModRecipeSerializer<? extends ISoakingRecipe> getSoakingSerializer();
    IModRecipeSerializer<? extends ICookingRecipe> getCookingSerializer();
    IModRecipeSerializer<? extends ISteamerRecipe> getSteamerSerializer();
    IModRecipeSerializer<? extends IMillRecipe> getMillSerializer();

    ICookingRecipeBuilder newCookingRecipe(ResourceLocation id);
    ISoakingRecipeBuilder newSoakingRecipe(ResourceLocation id);
    ISteamerRecipeBuilder newSteamerRecipe(ResourceLocation id);
    IMillRecipeBuilder newMillRecipe(ResourceLocation id);

    ICountIngredient newCountIngredient(Ingredient ingredient, int count);
    IFluidIngredient newFluidIngredient(ITag<Fluid> tag, int amount);
    IFluidIngredient newFluidIngredient(Fluid fluid, int amount);

    JsonElement serializeItem(ItemStack stack);
    JsonElement serializeFluid(FluidStack stack);
    JsonElement serializeItemIngredient(ICountIngredient ingredient);
    JsonElement serializeFluidIngredient(IFluidIngredient ingredient);

    ItemStack deserializeItem(JsonElement json);
    FluidStack deserializeFluid(JsonElement json);
    Ingredient deserializeVanillaIngredient(JsonElement json);
    ICountIngredient deserializeItemIngredient(JsonElement json);
    IFluidIngredient deserializeFluidIngredient(JsonElement json);

}
