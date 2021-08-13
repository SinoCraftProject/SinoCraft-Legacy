package cx.rain.mc.forgemod.sinocraft.api.crafting.grinding;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;

/**
 * A stone mill recipe's builder
 */
public interface IGrindingRecipeBuilder {

    IGrindingRecipeBuilder setInput(IItemProvider input);

    IGrindingRecipeBuilder setInput(Ingredient input);

    IGrindingRecipeBuilder setOutput(ItemStack output);

    IGrindingRecipeBuilder setTime(int time);

    IGrindingRecipe build();
}
