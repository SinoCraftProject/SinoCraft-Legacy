package cx.rain.mc.forgemod.sinocraft.api.crafting;

import com.google.gson.*;
import cx.rain.mc.forgemod.sinocraft.api.crafting.cooking.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.cooking.ICookingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.grinding.IGrindingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.grinding.IGrindingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.steaming.ISteamingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.steaming.ISteamingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public interface ISinoRecipes {

    Lazy<ISinoRecipes> INSTANCE = new Lazy<>();

    IRecipeType<ISoakingRecipe> getSoakingRecipe();
    IRecipeType<ICookingRecipe> getCookingRecipe();
    IRecipeType<ISteamingRecipe> getSteamerRecipe();
    IRecipeType<IGrindingRecipe> getMillRecipe();

    IModRecipeSerializer<? extends ISoakingRecipe> getSoakingSerializer();
    IModRecipeSerializer<? extends ICookingRecipe> getCookingSerializer();
    IModRecipeSerializer<? extends ISteamingRecipe> getSteamerSerializer();
    IModRecipeSerializer<? extends IGrindingRecipe> getMillSerializer();

    ICookingRecipeBuilder newCookingRecipe(ResourceLocation id);
    ISoakingRecipeBuilder newSoakingRecipe(ResourceLocation id);
    ISteamingRecipeBuilder newSteamerRecipe(ResourceLocation id);
    IGrindingRecipeBuilder newMillRecipe(ResourceLocation id);

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
