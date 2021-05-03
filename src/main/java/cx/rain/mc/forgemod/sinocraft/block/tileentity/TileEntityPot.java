package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.base.TileEntityMachineBase;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.IronPotRecipes;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.ModIronPotRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;

/**
 * @author NmmOC7
 */
public class TileEntityPot extends TileEntityMachineBase implements IInventory {
    private final IronPotItemHandler ITEM_HANDLER = new IronPotItemHandler();

    @OnlyIn(Dist.CLIENT)
    public NonNullList<ItemStack> clientInput = ITEM_HANDLER.getInput();
    @OnlyIn(Dist.CLIENT)
    public ItemStack clientOutput = ITEM_HANDLER.getOutput();

    public TileEntityPot() {
        super(ModTileEntities.IRON_POT.get());
    }

    @Override
    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.addAll(ITEM_HANDLER.getInput());
        list.add(ITEM_HANDLER.getOutput());
        return list;
    }

    private int progress = 0;

    @Override
    public void tick() {
        if (!this.world.isRemote) {
            progress++;

            if (progress >= 40 && ITEM_HANDLER.getOutput().isEmpty()) {
                for (IronPotRecipes recipe : ModIronPotRecipes.IRON_POT_RECIPES) {
                    if (recipe.matches(this, this.world)) {
                        for (ItemStack input : recipe.input) {
                            for (int i = 0; i < ITEM_HANDLER.getSlots() - 1; i++) {
                                ItemStack stack = ITEM_HANDLER.getStackInSlot(i);

                                if (stack.isItemEqual(input) && stack.getCount() >= input.getCount()) {
                                    ITEM_HANDLER.decrStackSize(i, input.getCount());
                                    break;
                                }
                            }
                        }

                        this.ITEM_HANDLER.setOutput(recipe.getCraftingResult(this));
                    }
                }

                this.progress = 0;
            }
        }
    }

    @Override
    public int getSizeInventory() {
        return ITEM_HANDLER.getSlots();
    }

    @Override
    public boolean isEmpty() {
        return ITEM_HANDLER.isEmpty();
    }

    @Override
    public ItemStack getStackInSlot(int index) {
        return ITEM_HANDLER.getStackInSlot(index);
    }

    @Override
    public ItemStack decrStackSize(int index, int count) {
        return ITEM_HANDLER.decrStackSize(index, count);
    }

    @Override
    public ItemStack removeStackFromSlot(int index) {
        ItemStack result = ITEM_HANDLER.getStackInSlot(index).copy();

        this.ITEM_HANDLER.setStackInSlot(index, ItemStack.EMPTY);

        return result;
    }

    @Override
    public void setInventorySlotContents(int index, ItemStack stack) {
        this.ITEM_HANDLER.setStackInSlot(index, stack);
    }

    @Override
    public boolean isUsableByPlayer(PlayerEntity player) {
        return true;
    }

    @Override
    public void clear() {
        this.ITEM_HANDLER.clear();
    }

    public boolean hasItemStack(ItemStack stack) {
        for (ItemStack input: ITEM_HANDLER.getInput()) {
            if (stack.isItemEqual(input) && stack.getCount() >= input.getCount()) {
                return true;
            }
        }

        return false;
    }

    public int addStackToInput(ItemStack stack) {
        return ITEM_HANDLER.addStack(stack);
    }

    public ItemStack removeStackOnInput() {
        ItemStack result = ItemStack.EMPTY;

        for (int i = 0; i < 6; i++) {
            if (!this.getStackInSlot(i).isEmpty()) {
                result = this.getStackInSlot(i).copy();
                this.setInventorySlotContents(i, ItemStack.EMPTY);

                break;
            }
        }

        return result;
    }

    public ItemStack removeStackOnOutput() {
        ItemStack result = ItemStack.EMPTY;

        if (!ITEM_HANDLER.getOutput().isEmpty()) {
            result = ITEM_HANDLER.getOutput();
            ITEM_HANDLER.setOutput(ItemStack.EMPTY);
        }

        return result;
    }

    public NonNullList<ItemStack> getInput() {
        return ITEM_HANDLER.getInput();
    }

    public ItemStack getOutput() {
        return ITEM_HANDLER.getOutput();
    }

    private class IronPotItemHandler extends ItemStackHandler {
        public IronPotItemHandler() {
            super(7);
        }

        public NonNullList<ItemStack> getInput() {
            NonNullList<ItemStack> result = NonNullList.withSize(6, ItemStack.EMPTY);

            for (int i = 0; i < 6; i++) {
                result.set(i, this.stacks.get(i).copy());
            }

            return result;
        }

        public ItemStack getOutput() {
            return this.stacks.get(6).copy();
        }

        public void setOutput(ItemStack output) {
            this.setStackInSlot(6, output);
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

        public void clear() {
            this.stacks.clear();
        }

        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @NotNull ItemStack stack) {
            return true;
        }

        public boolean isEmpty() {
            boolean result = true;

            for (ItemStack stack: this.stacks) {
                if (!stack.isEmpty()) {
                    result = false;
                    break;
                }
            }

            return result;
        }

        public ItemStack decrStackSize(int index, int count) {
            return ItemStackHelper.getAndSplit(this.stacks, index, count);
        }
    }
}
