package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

/**
 * A stone mill recipe's builder
 */
public interface IMillRecipeBuilder {

    IMillRecipeBuilder setInput(IItemProvider input);

    IMillRecipeBuilder setInput(Ingredient input);

    IMillRecipeBuilder setOutput(ItemStack output);

    IMillRecipeBuilder setTime(int time);

    IMillRecipe build();
}
