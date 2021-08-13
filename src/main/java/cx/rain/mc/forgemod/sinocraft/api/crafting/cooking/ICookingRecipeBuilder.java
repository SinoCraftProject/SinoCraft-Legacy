package cx.rain.mc.forgemod.sinocraft.api.crafting.cooking;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ICountIngredient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

/**
 * A cooking recipe's builder
 */
public interface ICookingRecipeBuilder {

    /**
     * Set the min and max heat.
     * @see ICookingRecipe#getMinHeat()
     * @see ICookingRecipe#getMaxHeat()
     */
    ICookingRecipeBuilder setHeat(int min, int max);

    /**
     * Set the output item.
     * @see ICookingRecipe#getRecipeOutput()
     */
    ICookingRecipeBuilder setOutput(ItemStack output);

    /**
     * Set the output if heat is too hot.
     * @see ICookingRecipe#getAdustOutput()
     */
    ICookingRecipeBuilder setAdustOutput(ItemStack output);

    /**
     * Set cooking time.
     */
    ICookingRecipeBuilder setTime(int time);

    /**
     * Add one item input.
     */
    ICookingRecipeBuilder addInput(ICountIngredient ingredient);

    /**
     * Add one item input. The count of the item is 1.
     */
    ICookingRecipeBuilder addInput(Ingredient item);

    /**
     * Add one item input with item type and count.
     */
    ICookingRecipeBuilder addInput(Ingredient item, int count);

    /**
     * Set the container for dish
     */
    ICookingRecipeBuilder setContainer(ItemStack container);

    /**
     * Build and return the recipe.
     */
    ICookingRecipe build();
}
