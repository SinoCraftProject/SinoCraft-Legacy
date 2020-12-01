package cx.rain.mc.forgemod.sinocraft.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.base.TileEntityMachineBase;
import cx.rain.mc.forgemod.sinocraft.fluid.Fluids;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.item.Item;
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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TileEntityVat extends TileEntityMachineBase {
    private static Map<Item, ItemStack> recipes = new HashMap<>();
    private static Map<Item, FluidStack> recipes2 = new HashMap<>();
    private static Map<Item, Integer> consume = new HashMap<>();

    private class VatItemHandler extends ItemStackHandler {
        public VatItemHandler() {
            super(2);
        }

        public void setResult(ItemStack result) {
            stacks.set(0, result);
        }

        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return consume.containsKey(stack.getItem());
        }
    }

    private VatItemHandler itemHandler = new VatItemHandler();

    private FluidStack fluid = FluidStack.EMPTY;
    int progress = 0;

    public static void registerRecipe(ItemStack material, ItemStack result) {
        recipes.put(material.getItem(), result);
        consume.put(material.getItem(), material.getCount());
    }

    public static void registerRecipe(ItemStack material, FluidStack result) {
        recipes2.put(material.getItem(), result);
        consume.put(material.getItem(), material.getCount());
    }

    private void registerDefaultRecipes() {
        registerRecipe(new ItemStack(Items.BARK.get(), 3), new FluidStack(Fluids.WOOD_PULP.get(), 1000));
        registerRecipe(new ItemStack(Items.FLOUR.get(), 2), new ItemStack(Items.DOUGH.get()));
    }

    public TileEntityVat() {
        super(TileEntities.VAT.get());
        registerDefaultRecipes();
        state = MachineState.CLOSE;
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
        ItemStack stack = itemHandler.getStackInSlot(1);
        if (consume.containsKey(stack.getItem()) && fluid.getAmount() >= 1000 && fluid.getFluid() == net.minecraft.fluid.Fluids.WATER) {
            if (consume.get(stack.getItem()) <= stack.getCount()) {
                progress++;
                if (progress == 400) {
                    progress = 0;
                    if (recipes.containsKey(stack.getItem())) {
                        itemHandler.setResult(recipes.get(stack.getItem()).copy());
                        this.itemHandler.extractItem(1,consume.get(stack.getItem()),false);
                        this.fluid = FluidStack.EMPTY;
                    } else if (recipes2.containsKey(stack.getItem())) {
                        this.fluid = recipes2.get(stack.getItem()).copy();
                        this.itemHandler.extractItem(1,consume.get(stack.getItem()),false);
                    }
                }
            }
        } else {
            progress = 0;
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluid", fluid.writeToNBT(new CompoundNBT()));
        compound.put("stacks", itemHandler.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        fluid = FluidStack.loadFluidStackFromNBT(compound.getCompound("fluid"));
        itemHandler.deserializeNBT(compound.getCompound("stacks"));
        super.read(compound);
    }

    @Override
    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.add(itemHandler.getStackInSlot(0));
        list.add(itemHandler.getStackInSlot(1));
        return list;
    }
}
