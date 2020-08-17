package cx.rain.mc.forgemod.sinocraft.gui;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ContainerChineseBrush extends Container {
    public IInventory inventory;

    protected ContainerChineseBrush(int id, IInventory itemInventory, IInventory playerInventory) {
        super(Containers.CHINESE_BRUSH.get(), id);
        this.inventory = itemInventory;
        this.addSlot(new Slot(itemInventory, 0, 10, 21));
        this.addSlot(new Slot(itemInventory, 1, 10, 60));
        this.addSlot(new Slot(itemInventory, 2, 10, 198));
        itemInventory.setInventorySlotContents(0, new ItemStack(Items.DIAMOND));
        itemInventory.setInventorySlotContents(1, new ItemStack(Items.IRON_AXE));
        itemInventory.setInventorySlotContents(2, new ItemStack(Items.RED_SAND));
        layoutPlayerInventorySlots(playerInventory, 16, 154);
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        this.clearContainer(playerIn, playerIn.world, this.inventory);
    }

    private int addSlotRange(IInventory inventory, int index, int x, int y, int amount, int dx) {
        for (int i = 0; i < amount; i++) {
            addSlot(new Slot(inventory, index, x, y));
            x += dx;
            index++;
        }
        return index;
    }

    private int addSlotBox(IInventory inventory, int index, int x, int y, int horAmount, int dx, int verAmount, int dy) {
        for (int j = 0; j < verAmount; j++) {
            index = addSlotRange(inventory, index, x, y, horAmount, dx);
            y += dy;
        }
        return index;
    }


    private void layoutPlayerInventorySlots(IInventory inventory, int leftCol, int topRow) {
        addSlotBox(inventory, 9, leftCol, topRow, 9, 18, 3, 18);

        topRow += 58;
        addSlotRange(inventory, 0, leftCol, topRow, 9, 18);
    }

    @Override
    public boolean canInteractWith(PlayerEntity playerIn) {
        return true;
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        return ItemStack.EMPTY;
    }
}
