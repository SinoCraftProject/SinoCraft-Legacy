package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.IRecipe;

public interface IMillRecipe extends IRecipe<IExtendedRecipeInventory>, IFinishedRecipe {

    int getTime();

    ICountIngredient getInput();

}
