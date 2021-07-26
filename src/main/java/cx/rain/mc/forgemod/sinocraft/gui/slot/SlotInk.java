package cx.rain.mc.forgemod.sinocraft.gui.slot;

import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotInk extends Slot {
    public SlotInk(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem().equals(ModItems.CHINESE_INK.get()) || stack.getItem().equals(ModItems.INK_STONE.get()) || stack == ItemStack.EMPTY;
    }
}
