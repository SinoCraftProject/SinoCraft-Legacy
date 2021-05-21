package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.block.ITileEntityStoneMill;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IMillRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityStoneMill extends TileEntityUpdatableBase implements ITileEntityStoneMill {

    private final ItemStackHandler itemHandler = new MillItemHandler(this);
    private final ExtendedInventory inv = new ExtendedInventory();

    private int progress = 0;
    private IMillRecipe currentRecipe = null;
    private ItemStack outputStack = ItemStack.EMPTY;

    public TileEntityStoneMill() {
        super(ModTileEntities.STONE_MILL.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> itemHandler).cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void tick() { }

    @Override
    public void rotate() {
        if (!outputStack.isEmpty()) {
            outputStack = itemHandler.insertItem(0, outputStack, false);
            if (outputStack.isEmpty()) {
                return;
            }
        }
        if (currentRecipe == null || world == null) {
            return;
        }
        progress++;
        if (progress == currentRecipe.getTime()) {
            progress = 0;
            InventoryHelper.spawnItemStack(world, pos.getX(), pos.getY(), pos.getZ(), currentRecipe.getRecipeOutput().copy());
            int count = currentRecipe.getInput().getCount();
            ItemStack simulate = itemHandler.extractItem(0, count, true);
            if (simulate.getCount() >= count) {
                outputStack = itemHandler.insertItem(0, currentRecipe.getRecipeOutput(), false);
            }
        }
        markDirty();
    }

    @Override
    public IMillRecipe getCurrentRecipe() {
        return currentRecipe;
    }

    @Override
    public void reloadRecipe() {
        if (itemHandler.getStackInSlot(0).isEmpty() || world == null) {
            if (currentRecipe != null) {
                currentRecipe = null;
            }
            return;
        }
        IMillRecipe old = currentRecipe;
        currentRecipe = world.getRecipeManager().getRecipe(ModRecipes.MILL, inv, world).orElse(null);
        if (old != currentRecipe) {
            progress = 0;
            markDirty();
        }
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public void setProgress(int progress) {
        if (this.progress != progress) {
            this.progress = progress;
            markDirty();
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("stacks", itemHandler.serializeNBT());
        compound.putInt("progress", progress);
        compound.put("output", outputStack.serializeNBT());
        if (currentRecipe != null) {
            compound.putString("recipe", currentRecipe.getId().toString());
        }
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        itemHandler.deserializeNBT(compound.getCompound("stacks"));
        progress = compound.getInt("progress");
        outputStack = ItemStack.read(compound.getCompound("output"));
        if (world != null && compound.contains("recipe", Constants.NBT.TAG_STRING)) {
            ResourceLocation recipeId = new ResourceLocation(compound.getString("recipe"));
            currentRecipe = (IMillRecipe) world.getRecipeManager().getRecipe(recipeId)
                    .filter(r -> r instanceof IMillRecipe)
                    .orElse(null);
        } else {
            currentRecipe = null;
        }
        super.read(state, compound);
    }

    class ExtendedInventory extends RecipeWrapper implements IExtendedRecipeInventory {

        public ExtendedInventory() {
            super(itemHandler);
        }

        @Override
        public ItemStack getInputItem(int index) {
            return itemHandler.getStackInSlot(0);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public void setItemSlotMap(int input, int recipe) {

        }
    }
}
