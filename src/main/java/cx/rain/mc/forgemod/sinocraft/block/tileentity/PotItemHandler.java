package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import net.minecraft.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;

import java.util.List;

class PotItemHandler extends ItemStackHandler {

    private final TileEntityPot te;

    // 6 - output
    public PotItemHandler(TileEntityPot te) {
        super(7);
        this.te = te;
    }

    @Override
    protected void onLoad() {
        super.onLoad();
        te.reloadRecipe();
        te.markDirty();
    }

    @Override
    protected void onContentsChanged(int slot) {
        super.onContentsChanged(slot);
        te.reloadRecipe();
        te.markDirty();
    }

    public List<ItemStack> getInputs() {
        return stacks.subList(0, 6);
    }

    public int addStack(ItemStack stack) {
        int remain = stack.getCount();

        for (int i = 0; i <= 5; i++) {
            ItemStack input = this.getStackInSlot(i);

            if (input.isEmpty()) {
                ItemStack newStack = stack.copy();
                newStack.setCount(remain);

                setStackInSlot(i, newStack);
                remain = 0;
                break;
            }
            else if (input.getItem() == stack.getItem()) {
                if (input.getMaxStackSize() - remain >= stack.getCount()) {
                    ItemStack newStack = input.copy();
                    newStack.setCount(remain + stack.getCount());

                    setStackInSlot(i, newStack);
                    remain = 0;
                    break;
                }
                else {
                    ItemStack newStack = stack.copy();
                    newStack.setCount(stack.getMaxStackSize());

                    setStackInSlot(i, newStack);
                    remain -= input.getMaxStackSize() - input.getCount();
                }
            }
        }

        return remain;
    }
}
