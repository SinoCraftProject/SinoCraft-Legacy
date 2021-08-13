package cx.rain.mc.forgemod.sinocraft.api.crafting.steaming;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;

public interface ISteamingRecipeBuilder {

    ISteamingRecipeBuilder setInput(Ingredient ingredient);

    ISteamingRecipeBuilder setOutput(ItemStack stack);

    ISteamingRecipeBuilder setAdustOutput(ItemStack stack);

    ISteamingRecipeBuilder setTime(int time);

    ISteamingRecipeBuilder setHeat(int min, int max);

    ISteamingRecipe build();
}
