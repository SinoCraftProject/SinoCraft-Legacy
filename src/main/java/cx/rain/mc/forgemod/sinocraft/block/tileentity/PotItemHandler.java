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

    public ItemStack addStack(ItemStack stack) {
        for (int i = 0; i <= 5; i++) {
            ItemStack input = this.getStackInSlot(i);
            if (input.isEmpty() || input.getItem() == stack.getItem()) {
                return insertItem(i, stack, false);
            }
        }
        return stack;
    }
}
