package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.crafting.vat.ISoakRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.vat.SoakRecipeSerializer;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.*;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityVat extends TileEntityUpdatableBase {
    private class VatItemHandler extends ItemStackHandler {
        public VatItemHandler() {
            super(2);
        }

        public void setResult(ItemStack result) {
            stacks.set(0, result);
        }

        @Override
        protected void onContentsChanged(int slot) {
            ISoakRecipe old = cur_recipe;
            cur_recipe = null;
            for (ISoakRecipe recipe : SoakRecipeSerializer.recipes.values()) {
                if (recipe.matches(new RecipeWrapper(this), world)) {
                    cur_recipe = recipe;
                }
            }
            if (old != cur_recipe) {
                progress = 0;
            }
            markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return true;
        }
    }

    private VatItemHandler itemHandler = new VatItemHandler();

    private FluidStack fluid = FluidStack.EMPTY;
    private ISoakRecipe cur_recipe;
    int progress = 0;

    public TileEntityVat() {
        super(ModTileEntities.VAT.get());
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> itemHandler).cast();
        } else if (cap == CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> new IFluidHandler() {
                @Override
                public int getTanks() {
                    return 1;
                }

                @Nonnull
                @Override
                public FluidStack getFluidInTank(int tank) {
                    return fluid;
                }

                @Override
                public int getTankCapacity(int tank) {
                    return 1000;
                }

                @Override
                public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
                    return true;
                }

                @Override
                public int fill(FluidStack resource, FluidAction action) {
                    if (resource.isEmpty()) {
                        return 0;
                    }
                    if (action.simulate()) {
                        if (fluid.isEmpty()) {
                            return Math.min(1000, resource.getAmount());
                        }
                        if (!fluid.isFluidEqual(resource)) {
                            return 0;
                        }
                        return Math.min(1000 - fluid.getAmount(), resource.getAmount());
                    }
                    markDirty();
                    if (fluid.isEmpty()) {
                        fluid = new FluidStack(resource, Math.min(1000, resource.getAmount()));
                        return fluid.getAmount();
                    }
                    if (!fluid.isFluidEqual(resource)) {
                        return 0;
                    }
                    int filled = 1000 - fluid.getAmount();

                    if (resource.getAmount() < filled) {
                        fluid.grow(resource.getAmount());
                        filled = resource.getAmount();
                    } else {
                        fluid.setAmount(1000);
                    }
                    return filled;
                }

                @Nonnull
                @Override
                public FluidStack drain(FluidStack resource, FluidAction action) {
                    if (resource.isEmpty() || !resource.isFluidEqual(fluid)) {
                        return FluidStack.EMPTY;
                    }
                    return drain(resource.getAmount(), action);
                }

                @Nonnull
                @Override
                public FluidStack drain(int maxDrain, FluidAction action) {
                    markDirty();
                    int drained = maxDrain;
                    if (fluid.getAmount() < drained) {
                        drained = fluid.getAmount();
                    }
                    FluidStack stack = new FluidStack(fluid, drained);
                    if (action.execute() && drained > 0) {
                        fluid.shrink(drained);
                    }
                    return stack;
                }
            }).cast();

        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void tick() {
        if (this.world.isRemote) {
            return;
        }
        if (cur_recipe != null && fluid.getAmount() >= 1000 && fluid.getFluid() == net.minecraft.fluid.Fluids.WATER) {
            progress ++;
            if (progress == 400) {
                progress = 0;
                if (cur_recipe.getRecipeOutput() != null) {
                    itemHandler.setResult(cur_recipe.getCraftingResult(new RecipeWrapper(itemHandler)));
                    this.fluid = FluidStack.EMPTY;
                }
                else {
                    this.fluid = cur_recipe.getFluidResult();
                }
                this.itemHandler.extractItem(1,cur_recipe.getItem().getCount(),false);
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluid", fluid.writeToNBT(new CompoundNBT()));
        compound.put("stacks", itemHandler.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        fluid = FluidStack.loadFluidStackFromNBT(compound.getCompound("fluid"));
        itemHandler.deserializeNBT(compound.getCompound("stacks"));
        super.read(state, compound);
    }
}
