package cx.rain.mc.forgemod.sinocraft.crafting.vat;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

public interface ISoakRecipe extends IRecipe<IInventory> {
    ItemStack getResult();

    ItemStack getItem();

    int get();
}
