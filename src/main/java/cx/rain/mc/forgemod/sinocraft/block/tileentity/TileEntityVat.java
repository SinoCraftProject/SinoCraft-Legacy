package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityVat extends TileEntityUpdatableBase implements cx.rain.mc.forgemod.sinocraft.api.block.ITileEntityVat {

    private final VatItemHandler itemHandler = new VatItemHandler(this);
    private final VatFluidHandler fluidHandler = new VatFluidHandler(this);
    private final IExtendedRecipeInventory inv = new ExtendedInventory();

    private ISoakingRecipe currentRecipe;
    private ResourceLocation recoveryRecipeLocation = null;
    private int progress = 0;
    // 未能完全输出的合成产物
    private ItemStack outputItem = ItemStack.EMPTY;
    private FluidStack outputFluid = FluidStack.EMPTY;

    public TileEntityVat() {
        super(ModTileEntities.VAT.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> itemHandler).cast();
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> fluidHandler).cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void onTick() {
        if (world == null || world.isRemote) {
            return;
        }
        if (!outputItem.isEmpty()) {
            outputItem = itemHandler.insertItem(0, outputItem, false);
        }
        if (!outputFluid.isEmpty()) {
            int filled = fluidHandler.fill(outputFluid.copy(), IFluidHandler.FluidAction.EXECUTE);
            outputFluid.shrink(filled);
        }
        if (world != null && recoveryRecipeLocation != null) {
            world.getRecipeManager().getRecipe(recoveryRecipeLocation)
                    .filter(recipe -> recipe instanceof ISoakingRecipe)
                    .ifPresent(recipe -> currentRecipe = (ISoakingRecipe) recipe);
            recoveryRecipeLocation = null;
        }
        ISoakingRecipe recipe = currentRecipe;
        if (recipe != null) {
            if (progress >= recipe.getSoakingTime()) {
                if (!outputItem.isEmpty() || !outputFluid.isEmpty()) return;
                progress = 0;
                currentRecipe = null;
                // 消耗
                int inputCount = recipe.getInputItem().getCount();
                ItemStack extractItem = itemHandler.extractItem(1, inputCount, true);
                if (extractItem.getCount() < inputCount) {
                    return;
                }
                int fluidAmount = recipe.getInputFluid().getAmount();
                FluidStack drain = fluidHandler.drain(fluidAmount, IFluidHandler.FluidAction.SIMULATE);
                if (drain.getAmount() < fluidAmount) {
                    return;
                }
                itemHandler.extractItem(1, inputCount, false);
                fluidHandler.drain(fluidAmount, IFluidHandler.FluidAction.EXECUTE);
                // 产出
                ItemStack itemOutput = recipe.getRecipeOutput();
                if (!itemOutput.isEmpty()) {
                    itemOutput = itemOutput.copy();
                    ItemStack insert = ItemHandlerHelper.insertItem(itemHandler, itemOutput, false);
                    if (!insert.isEmpty()) {
                        outputItem = insert;
                        markDirty();
                    }
                }
                FluidStack fluidOutput = recipe.getFluidOutput();
                if (!fluidOutput.isEmpty()) {
                    int fill = fluidHandler.fill(fluidOutput.copy(), IFluidHandler.FluidAction.EXECUTE);
                    if (fill < fluidOutput.getAmount()) {
                        outputFluid = fluidOutput.copy();
                        outputFluid.shrink(fill);
                        markDirty();
                    }
                }
            } else {
                progress++;
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluid", fluidHandler.serializeNBT());
        compound.put("stacks", itemHandler.serializeNBT());
        if (!outputFluid.isEmpty()) {
            compound.put("outFluid", outputFluid.writeToNBT(new CompoundNBT()));
        }
        if (!outputItem.isEmpty()) {
            compound.put("outItem", outputItem.write(new CompoundNBT()));
        }
        if (currentRecipe != null) {
            compound.putString("recipe", currentRecipe.getId().toString());
            compound.putInt("process", progress);
        }
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        fluidHandler.deserializeNBT(compound.getCompound("fluid"));
        itemHandler.deserializeNBT(compound.getCompound("stacks"));
        if (compound.contains("outFluid", Constants.NBT.TAG_COMPOUND)) {
            outputFluid = FluidStack.loadFluidStackFromNBT(compound.getCompound("outFluid"));
        } else {
            outputFluid = FluidStack.EMPTY;
        }
        if (compound.contains("outItem", Constants.NBT.TAG_COMPOUND)) {
            outputItem = ItemStack.read(compound.getCompound("outItem"));
        } else {
            outputItem = ItemStack.EMPTY;
        }
        currentRecipe = null;
        progress = 0;
        if (compound.contains("recipe", Constants.NBT.TAG_STRING)) {
            recoveryRecipeLocation = new ResourceLocation(compound.getString("recipe"));
        }
        progress = compound.getInt("process");
        super.read(state, compound);
    }

    public VatFluidHandler getFluidHandler() {
        return fluidHandler;
    }

    public VatItemHandler getItemHandler() {
        return itemHandler;
    }

    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.add(itemHandler.getStackInSlot(0));
        list.add(itemHandler.getStackInSlot(1));
        return list;
    }

    @Override
    public void reloadRecipe() {
        if (world == null) return;
        ISoakingRecipe old;
        if (recoveryRecipeLocation != null) {
            old = world.getRecipeManager().getRecipe(recoveryRecipeLocation)
                    .filter(recipe -> recipe instanceof ISoakingRecipe)
                    .map(recipe -> (ISoakingRecipe) recipe)
                    .orElse(currentRecipe);
            recoveryRecipeLocation = null;
        } else old = currentRecipe;
        currentRecipe = world.getRecipeManager().getRecipe(ModRecipes.SOAKING, inv, world).orElse(null);
        if (old != currentRecipe) {
            progress = 0;
            markDirty();
        }
    }

    @Override
    @Nullable
    public ISoakingRecipe getCurrentRecipe() {
        if (world != null && recoveryRecipeLocation != null) {
            world.getRecipeManager().getRecipe(recoveryRecipeLocation)
                    .filter(recipe -> recipe instanceof ISoakingRecipe)
                    .ifPresent(recipe -> currentRecipe = (ISoakingRecipe) recipe);
            recoveryRecipeLocation = null;
        }
        return currentRecipe;
    }

    @Override
    public int getProgress() {
        return progress;
    }

    @Override
    public void setProgress(int progress) {
        if (progress != this.progress) {
            this.progress = progress;
            markDirty();
        }
    }

    class ExtendedInventory extends RecipeWrapper implements IExtendedRecipeInventory {

        ExtendedInventory() {
            super(itemHandler);
        }

        @Override
        public ItemStack getInputItem(int index) {
            return itemHandler.getStackInSlot(1);
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        @Override
        public FluidStack getInputFluid(int index) {
            return fluidHandler.getFluidInTank(0);
        }

        @Override
        public int getFluidCount() {
            return 1;
        }
    }
}
