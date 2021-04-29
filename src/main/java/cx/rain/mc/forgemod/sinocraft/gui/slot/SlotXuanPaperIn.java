package cx.rain.mc.forgemod.sinocraft.gui.slot;

import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class SlotXuanPaperIn extends Slot {
    public SlotXuanPaperIn(IInventory inventoryIn, int index, int xPosition, int yPosition) {
        super(inventoryIn, index, xPosition, yPosition);
    }

    @Override
    public boolean isItemValid(ItemStack stack) {
        return stack.getItem().equals(ModItems.EMPTY_XUAN_PAPER.get());
    }
}
