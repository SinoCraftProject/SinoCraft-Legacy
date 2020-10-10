package cx.rain.mc.forgemod.sinocraft.gui.container;

import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;

public class ContainerChineseBrush extends Container {
    public IInventory inventory;
    public int color;

    protected ContainerChineseBrush(int id, IInventory itemInventory, IInventory playerInventory) {
        super(Containers.CHINESE_BRUSH.get(), id);
        this.inventory = itemInventory;
        this.addSlot(new Slot(itemInventory, 0, 14, 23) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().equals(Items.XUAN_PAPER.get());
            }
        });
        this.addSlot(new Slot(itemInventory, 1, 14, 66) {
            @Override
            public boolean isItemValid(ItemStack stack) {
                return stack.getItem().equals(Items.CHINA_INK.get());
            }
        });
        this.addSlot(new Slot(itemInventory, 2, 14, 203) {
            @Override
            public int getSlotStackLimit() {
                return 1;
            }
        });
        layoutPlayerInventorySlots(playerInventory, 45, 155);
        color = 0;
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

    public void incColor() {
        color = Math.min(16, color + 1);
    }

    public void decColor() {
        color = Math.max(0, color - 1);
    }
}
