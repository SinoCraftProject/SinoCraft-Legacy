package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;

class MillItemHandler extends ItemStackHandler {
    private final TileEntityStoneMill te;
    private ItemStack testItem = ItemStack.EMPTY;

    public MillItemHandler(TileEntityStoneMill te) {
        super(1);
        this.te = te;
    }

    @Override
    protected void onContentsChanged(int slot) {
        te.reloadRecipe();
        te.markDirty();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        World world = te.getWorld();
        if (world == null) {
            return false;
        }
        testItem = stack.copy();
        return world.getRecipeManager().getRecipe(ModRecipes.MILL, tester, world).isPresent();
    }

    private final IExtendedRecipeInventory tester = new IExtendedRecipeInventory() {
        @Override
        public ItemStack getInputItem(int index) {
            return testItem;
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public int getSizeInventory() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return testItem.isEmpty();
        }

        @Override
        public ItemStack getStackInSlot(int index) {
            return testItem;
        }

        @Override
        public ItemStack decrStackSize(int index, int count) {
            return ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeStackFromSlot(int index) {
            return ItemStack.EMPTY;
        }

        @Override
        public void setInventorySlotContents(int index, ItemStack stack) {

        }

        @Override
        public void markDirty() {

        }

        @Override
        public boolean isUsableByPlayer(PlayerEntity player) {
            return false;
        }

        @Override
        public void clear() {

        }
    };
}
