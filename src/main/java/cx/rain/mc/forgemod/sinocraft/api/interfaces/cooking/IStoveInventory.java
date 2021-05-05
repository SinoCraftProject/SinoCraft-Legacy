package cx.rain.mc.forgemod.sinocraft.api.interfaces.cooking;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IStoveInventory extends IInventory, IHeat {
    NonNullList<ItemStack> getAllStacks();
}
