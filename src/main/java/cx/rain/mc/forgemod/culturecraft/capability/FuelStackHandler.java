package cx.rain.mc.forgemod.culturecraft.capability;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Fuel Item Stack Handler
 * Each of slots leave with 1 fuel
 */
public class FuelStackHandler extends ItemStackHandler {
    public FuelStackHandler(int size) {
        super(size);
    }

    @Nonnull
    @Override
    public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
        if (stack.isEmpty()) {
            stacks.set(slot, ItemStack.EMPTY);
            return ItemStack.EMPTY;
        }

        if (FurnaceTileEntity.isFuel(stack) && stacks.get(slot).isEmpty()) {
            super.insertItem(slot, new ItemStack(stack.getItem(), 1), simulate);
            CultureCraft.getInstance().getLog().info("Added " + stack.getDisplayName() + " in slot " + slot);
            stack.shrink(1);
        }
        return stack;
    }

    @Nonnull
    @Override
    public ItemStack extractItem(int slot, int amount, boolean simulate) {
        return stacks.get(slot);
    }

    @Override
    public int getSlotLimit(int slot) {
        return 1;
    }
}
