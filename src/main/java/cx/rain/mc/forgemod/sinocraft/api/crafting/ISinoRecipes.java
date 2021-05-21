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

    /**
     * Create new count ingredient instance,
     */
    ICountIngredient newCountIngredient(Ingredient ingredient, int count);

    /**
     * Create new fluid ingredient instance by fluid tag and amount
     */
    IFluidIngredient newFluidIngredient(ITag<Fluid> tag, int amount);

    /**
     * Create new fluid ingredient instance by fluid and amount
     */
    IFluidIngredient newFluidIngredient(Fluid fluid, int amount);

    /**
     * Write an item stack to json.
     */
    JsonElement serializeItem(ItemStack stack);

    /**
     * Write a fluid stack to json.
     */
    JsonElement serializeFluid(FluidStack stack);

    /**
     * Write an item ingredient to json.
     */
    JsonElement serializeItemIngredient(ICountIngredient ingredient);

    /**
     * Write a fluid ingredient to json.
     */
    JsonElement serializeFluidIngredient(IFluidIngredient ingredient);

    /**
     * Read item stack from json.
     */
    ItemStack deserializeItem(JsonElement json);

    /**
     * Read fluid stack from json.
     */
    FluidStack deserializeFluid(JsonElement json);

    /**
     * Read ingredient from json.
     */
    Ingredient deserializeVanillaIngredient(JsonElement json);

    /**
     * Read count ingredient from json.
     */
    ICountIngredient deserializeItemIngredient(JsonElement json);

    /**
     * Read fluid ingredient from json.
     */
    IFluidIngredient deserializeFluidIngredient(JsonElement json);

}
