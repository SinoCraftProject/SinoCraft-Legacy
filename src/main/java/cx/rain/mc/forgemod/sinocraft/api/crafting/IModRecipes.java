package cx.rain.mc.forgemod.sinocraft.api.crafting;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;

public interface IModRecipes {

    Lazy<IModRecipes> INSTANCE = new Lazy<>("未找到 SinoCraft Mod");

    static IModRecipes getInstance() {
        return INSTANCE.get();
    }

    IRecipeType<ISoakingRecipe> getSoakingRecipe();
    IRecipeType<ICookingRecipe> getCookingRecipe();
    IRecipeType<ISteamerRecipe> getSteamerRecipe();

    IModRecipeSerializer<? extends ISoakingRecipe> getSoakingSerializer();
    IModRecipeSerializer<? extends ICookingRecipe> getCookingSerializer();
    IModRecipeSerializer<? extends ISteamerRecipe> getSteamerSerializer();

    ICookingRecipeBuilder newCookingRecipe(ResourceLocation id);
    ISoakingRecipeBuilder newSoakingRecipe(ResourceLocation id);
    ISteamerRecipeBuilder newSteamerRecipe(ResourceLocation id);
}
