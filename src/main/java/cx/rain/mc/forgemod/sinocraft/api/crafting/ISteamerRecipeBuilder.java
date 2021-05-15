package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public interface ISteamerRecipeBuilder {

    ISteamerRecipeBuilder setInput(Ingredient ingredient);

    ISteamerRecipeBuilder setOutput(ItemStack stack);

    ISteamerRecipeBuilder setAdustOutput(ItemStack stack);

    ISteamerRecipeBuilder setTime(int time);

    ISteamerRecipeBuilder setHeat(int min, int max);

    ISteamerRecipe build();
}
