package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.base.TileEntityMachineBase;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.IronPotRecipes;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.ModIronPotRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;

import java.util.ArrayList;

/**
 * @author NmmOC7
 */
public class TileEntityIronPot extends TileEntityMachineBase implements IInventory {
    private final NonNullList<ItemStack> INPUT_LIST = NonNullList.withSize(6, ItemStack.EMPTY);
    private ItemStack output = ItemStack.EMPTY;

    public TileEntityIronPot() {
        super(ModTileEntities.IRON_POT.get());
    }

    @Override
    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.addAll(INPUT_LIST);
        list.add(output);
        return list;
    }

    @Override
    public void tick() {
        if (output.isEmpty()) {
            for (IronPotRecipes recipe: ModIronPotRecipes.IRON_POT_RECIPES) {
                if (recipe.matches(this, this.world)) {
                    output = recipe.getCraftingResult(this);
                }
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return INPUT_LIST.size();
    }

    @Override
    public boolean isEmpty() {
        boolean result = true;

        for (ItemStack stack: INPUT_LIST) {
            if (!stack.isEmpty()) {
                result = false;
                break;
            }
        }

        if (output.isEmpty()) {
            result = false;
        }

        return result;
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        if (index == 7) {
            return output;
        }

        return index >= this.getSizeInventory() ? ItemStack.EMPTY : this.INPUT_LIST.get(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        if (index == 7) {
            return this.output.split(count);
        }

        ItemStack itemstack = ItemStackHelper.getAndSplit(this.INPUT_LIST, index, count);
        return itemstack;
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        if (index == 7) {
            this.output = ItemStack.EMPTY;
        }

        return ItemStackHelper.getAndRemove(this.INPUT_LIST, index);
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        if (index == 7) {
            this.output = stack;
            return;
        }

        this.INPUT_LIST.set(index, stack);
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.INPUT_LIST.clear();
        this.output = ItemStack.EMPTY;
    }

    public boolean hasItemStack(ItemStack stack) {
        for (ItemStack input: this.INPUT_LIST) {
            if (stack.isItemEqual(input)
                    && stack.getCount() >= input.getCount()) {
                return true;
            }
        }

        return false;
    }

    public int addStackToInput(ItemStack stack) {
        int remain = stack.getCount();

        for (int i = 0; i < INPUT_LIST.size(); i++) {
            ItemStack input = INPUT_LIST.get(i);

            if (input.isEmpty()) {
                ItemStack newStack = stack.copy();
                newStack.setCount(remain);

                INPUT_LIST.set(i, newStack);
                remain = 0;
                break;
            }
            else if (input.getItem() == stack.getItem()) {
                if (input.getMaxStackSize() - remain >= stack.getCount()) {
                    ItemStack newStack = input.copy();
                    newStack.setCount(remain + stack.getCount());

                    INPUT_LIST.set(i, newStack);
                    remain = 0;
                    break;
                }
                else {
                    ItemStack newStack = stack.copy();
                    newStack.setCount(stack.getMaxStackSize());

                    INPUT_LIST.set(i, newStack);
                    remain -= input.getMaxStackSize() - input.getCount();
                }
            }
        }

        return remain;
    }

    public ItemStack removeStackOnInput() {
        ItemStack result = ItemStack.EMPTY;

        for (int i = 0; i < 5; i++) {
            if (!this.INPUT_LIST.get(i).isEmpty()) {
                result = INPUT_LIST.get(i).copy();
                INPUT_LIST.set(i, ItemStack.EMPTY);

                break;
            }
        }

        return result;
    }

    public ItemStack removeStackOnOutput() {
        ItemStack result = ItemStack.EMPTY;

        if (!this.output.isEmpty()) {
            result = this.output.copy();
            this.output = ItemStack.EMPTY;
        }

        return result;
    }
}
