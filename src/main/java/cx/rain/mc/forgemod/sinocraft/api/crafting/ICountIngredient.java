package cx.rain.mc.forgemod.sinocraft.api.crafting;

import cx.rain.mc.forgemod.sinocraft.api.SinoCraftAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;

public interface ICountIngredient {

    ICountIngredient EMPTY = SinoCraftAPI.getRecipes().newCountIngredient(Ingredient.EMPTY, 0);

    Ingredient getIngredient();

    int getCount();

    boolean match(ItemStack stack);

    void write(PacketBuffer buffer);
}
