package cx.rain.mc.forgemod.sinocraft.api.crafting;

import cx.rain.mc.forgemod.sinocraft.api.SinoCraftAPI;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;

/**
 * An item ingredient base on {@link Ingredient} and count.
 */
public interface ICountIngredient {

    ICountIngredient EMPTY = SinoCraftAPI.getRecipes().newCountIngredient(Ingredient.EMPTY, 0);

    Ingredient getIngredient();

    int getCount();

    /**
     * test input item base on ingredient and count.
     */
    boolean match(ItemStack stack);

    /**
     * write the ingredient to network packet.
     */
    void write(PacketBuffer buffer);
}
