package cx.rain.mc.forgemod.sinocraft.api.capability;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

/**
 * Fuel Item Stack Handler
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

        if (FurnaceTileEntity.isFuel(stack) && (stacks.get(slot).isEmpty() || stacks.get(slot).getItem().equals(stack.getItem()))) {
            super.insertItem(slot, new ItemStack(stack.getItem(), 1), simulate);
        }
        return stack;
    }

    @Override
    public int getSlotLimit(int slot) {
        return 64;
    }
}
