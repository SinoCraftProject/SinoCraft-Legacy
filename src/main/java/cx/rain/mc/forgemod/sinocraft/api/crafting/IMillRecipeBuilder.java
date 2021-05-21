package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public interface IMillRecipeBuilder {

    IMillRecipeBuilder setInput(ICountIngredient input);

    IMillRecipeBuilder setInput(ItemStack input);

    IMillRecipeBuilder setInput(Ingredient input, int count);

    IMillRecipeBuilder setOutput(ItemStack output);

    IMillRecipeBuilder setTime(int time);

    IMillRecipe build();
}
