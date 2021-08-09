package cx.rain.mc.forgemod.sinocraft.gui.container;

import cx.rain.mc.forgemod.sinocraft.gui.slot.SlotInk;
import cx.rain.mc.forgemod.sinocraft.gui.slot.SlotXuanPaperIn;
import cx.rain.mc.forgemod.sinocraft.gui.slot.SlotXuanPaperOut;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Container;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.IIntArray;
import net.minecraft.world.World;

public class ContainerChineseBrush extends Container implements IIntArray {
    public IInventory inventory;
    public byte color;

    protected ContainerChineseBrush(int id, IInventory itemInventory, IInventory playerInventory) {
        super(ModContainers.CHINESE_BRUSH.get(), id);
        this.inventory = itemInventory;
        addSlots();
        layoutPlayerInventorySlots(playerInventory, 45, 155);
        color = 0;
    }

    private void addSlots() {
        this.addSlot(new SlotXuanPaperIn(inventory, 0, 14, 23) {
            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                inventory.setInventorySlotContents(2, ItemStack.EMPTY);
                return super.onTake(thePlayer, stack);
            }
        });
        this.addSlot(new SlotInk(inventory, 1, 14, 66));
        this.addSlot(new SlotXuanPaperOut(inventory, 2, 14, 203) {
            @Override
            public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
                ItemStack paper = inventory.getStackInSlot(0);
                paper.setCount(paper.getCount() - 1);
                ItemStack ink = inventory.getStackInSlot(1);
                ink.setCount(ink.getCount() - 1);
                return super.onTake(thePlayer, stack);
            }
        });
    }

    @Override
    public void onContainerClosed(PlayerEntity playerIn) {
        super.onContainerClosed(playerIn);
        clearContainer(playerIn, playerIn.world, this.inventory);
    }

    // qyl: Override this for preventing the item stack in output slot drops, then duplicate xuan paper.
    @Override
    protected void clearContainer(PlayerEntity playerIn, World worldIn, IInventory inventoryIn) {
        if (!playerIn.isAlive()
                || playerIn instanceof ServerPlayerEntity
                && ((ServerPlayerEntity) playerIn).hasDisconnected()) {
            for (int j = 0; j < 2; ++j) {
                playerIn.dropItem(inventoryIn.removeStackFromSlot(j), false);
            }
        } else {
            for (int i = 0; i < 2; ++i) {
                playerIn.inventory.placeItemBackInInventory(worldIn, inventoryIn.removeStackFromSlot(i));
            }
        }
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
        return playerIn.getHeldItem(Hand.MAIN_HAND).getItem() == ModItems.CHINESE_BRUSH.get()
                || playerIn.getHeldItem(Hand.OFF_HAND).getItem() == ModItems.CHINESE_BRUSH.get();
    }

    @Override
    public ItemStack transferStackInSlot(PlayerEntity playerIn, int index) {
        ItemStack result = ItemStack.EMPTY;

        Slot slot = inventorySlots.get(index);
        if (slot != null && slot.getHasStack()) {
            ItemStack stackInSlot = slot.getStack();

            result = stackInSlot.copy();
            if (index < 3) {
                if (!this.mergeItemStack(stackInSlot, 3, this.inventorySlots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(stackInSlot, 0, 3, false)) {
                return ItemStack.EMPTY;
            }

            if (stackInSlot.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
        }

        return result;
    }

    public void incColor() {
        color = (byte) Math.max(inventory.getStackInSlot(1).getDamage(), Math.min(16, color + 1));
    }

    public void decColor() {
        color = (byte) Math.max(0, color - 1);
    }

    // qyl: Don't delete lines below, used for auth sync data.
    @Override
    public int get(int index) {
        return 0;
    }

    @Override
    public void set(int index, int value) {
    }

    @Override
    public int size() {
        return 0;
    }
}
