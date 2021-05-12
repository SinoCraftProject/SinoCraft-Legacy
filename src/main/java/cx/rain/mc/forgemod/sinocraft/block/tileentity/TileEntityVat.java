package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.crafting.soaking.ISoakingRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityVat extends TileEntityUpdatableBase {

    private final VatItemHandler itemHandler = new VatItemHandler(this);
    private final VatFluidHandler fluidHandler = new VatFluidHandler(this);

    ISoakingRecipe currentRecipe;
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
            return LazyOptional.of(() -> fluidHandler).cast();
        } else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void tick() {
        if (world == null || world.isRemote) {
            return;
        }
        FluidStack fluid = fluidHandler.getFluid();
        if (currentRecipe != null && fluid.getAmount() >= 1000 && fluid.getFluid() == Fluids.WATER) {
            progress++;
            if (progress == 400) {
                progress = 0;
                if (currentRecipe.getRecipeOutput() != null) {
                    itemHandler.setResult(currentRecipe.getCraftingResult(new RecipeWrapper(itemHandler)));
                    fluidHandler.setFluid(FluidStack.EMPTY);
                } else {
                    fluidHandler.setFluid(currentRecipe.getFluidResult());
                }
                this.itemHandler.extractItem(1, currentRecipe.getItem().getCount(), false);
            }
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluid", fluidHandler.serializeNBT());
        compound.put("stacks", itemHandler.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        fluidHandler.deserializeNBT(compound.getCompound("fluid"));
        itemHandler.deserializeNBT(compound.getCompound("stacks"));
        super.read(state, compound);
    }


    public VatFluidHandler getFluidHandler() {
        return fluidHandler;
    }

    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.add(itemHandler.getStackInSlot(0));
        list.add(itemHandler.getStackInSlot(1));
        return list;
    }
}
