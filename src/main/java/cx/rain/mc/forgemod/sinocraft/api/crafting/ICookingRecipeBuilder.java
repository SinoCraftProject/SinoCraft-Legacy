package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public interface ICookingRecipeBuilder {

    ICookingRecipeBuilder setHeat(int min, int max);

    ICookingRecipeBuilder setOutput(ItemStack output);

    ICookingRecipeBuilder setAdustOutput(ItemStack output);

    ICookingRecipeBuilder setTime(int time);

    ICookingRecipeBuilder addInput(ICountIngredient ingredient);

    ICookingRecipeBuilder addInput(Ingredient item);

    ICookingRecipeBuilder addInput(Ingredient item, int count);

    ICookingRecipe build();
}
