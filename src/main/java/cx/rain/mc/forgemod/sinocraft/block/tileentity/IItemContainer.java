package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

public interface IItemContainer {
    public NonNullList<ItemStack> getItems();

    public int getCount();

    public int getCapacity();
}
