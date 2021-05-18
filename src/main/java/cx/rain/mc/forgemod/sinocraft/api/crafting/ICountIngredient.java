package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.network.PacketBuffer;

public interface ICountIngredient {

    ICountIngredient EMPTY = IModRecipes.getInstance().newCountIngredient(Ingredient.EMPTY, 0);

    Ingredient getIngredient();

    int getCount();

    boolean match(ItemStack stack);

    void write(PacketBuffer buffer);
}
