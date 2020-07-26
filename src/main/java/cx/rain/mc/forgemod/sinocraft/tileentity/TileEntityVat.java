package cx.rain.mc.forgemod.sinocraft.tileentity;

import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemHandlerHelper;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TileEntityVat extends TileEntity implements ITickableTileEntity {
    private static Map<ItemStack,ItemStack> recipes = new HashMap<>();
    private static Map<Item,Boolean> canInsert = new HashMap<>();

    private ItemStack item = ItemStack.EMPTY;
    private FluidStack fluid = FluidStack.EMPTY;

    public static void registerRecipe(ItemStack material,ItemStack result){
        recipes.put(material,result);
        canInsert.put(material.getItem(),true);
    }

    private void registerDefaultRecipes(){
        registerRecipe(new ItemStack(Items.BARK.get(),3),new ItemStack(Items.WOOD_PULP.get()));
    }

    public TileEntityVat() {
        super(TileEntities.VAT.get());
        registerDefaultRecipes();
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap==CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(()-> new IItemHandler(){
                @Override
                public int getSlots() {
                    return 1;
                }

                @Nonnull
                @Override
                public ItemStack getStackInSlot(int slot) {
                    return item;
                }

                protected void validateSlotIndex(int slot)
                {
                    if (slot != 0)
                        throw new RuntimeException("Slot " + slot + " not in valid range - [0," + 1 + ")");
                }

                @Nonnull
                @Override
                public ItemStack insertItem(int slot, @Nonnull ItemStack stack, boolean simulate) {
                    if (stack.isEmpty())
                        return ItemStack.EMPTY;

                    if (!isItemValid(slot, stack)||canInsert.containsKey(stack.getItem())&&canInsert.get(stack.getItem()))
                        return stack;

                    validateSlotIndex(slot);

                    int limit = Math.min(getSlotLimit(slot), stack.getMaxStackSize());

                    if (!item.isEmpty())
                    {
                        if (!ItemHandlerHelper.canItemStacksStack(stack, item))
                            return stack;

                        limit -= item.getCount();
                    }

                    if (limit <= 0)
                        return stack;

                    boolean reachedLimit = stack.getCount() > limit;

                    if (!simulate)
                    {
                        if (item.isEmpty())
                        {
                            item = reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, limit) : stack;
                        }
                        else
                        {
                            item.grow(reachedLimit ? limit : stack.getCount());
                        }
                    }

                    return reachedLimit ? ItemHandlerHelper.copyStackWithSize(stack, stack.getCount()- limit) : ItemStack.EMPTY;
                }

                @Nonnull
                @Override
                public ItemStack extractItem(int slot, int amount, boolean simulate) {
                    if (amount == 0)
                        return ItemStack.EMPTY;

                    validateSlotIndex(slot);

                    if (item.isEmpty())
                        return ItemStack.EMPTY;

                    int toExtract = Math.min(amount, item.getMaxStackSize());

                    if (item.getCount() <= toExtract)
                    {
                        if (!simulate)
                        {
                            item=ItemStack.EMPTY;
                            return item;
                        }
                        else
                        {
                            return item.copy();
                        }
                    }
                    else
                    {
                        if (!simulate)
                        {
                            item = ItemHandlerHelper.copyStackWithSize(item, item.getCount() - toExtract);
                        }

                        return ItemHandlerHelper.copyStackWithSize(item, toExtract);
                    }
                }

                @Override
                public int getSlotLimit(int slot) {
                    return 64;
                }

                @Override
                public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
                    return true;
                }
            }).cast();
        }
        else if(cap== CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY){
            return LazyOptional.of(()-> new IFluidHandler(){
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
                    return 2000;
                }

                @Override
                public boolean isFluidValid(int tank, @Nonnull FluidStack stack) {
                    return true;
                }

                @Override
                public int fill(FluidStack resource, FluidAction action) {
                    if (resource.isEmpty()||resource.getFluid()!= Fluids.WATER)
                    {
                        return 0;
                    }
                    if (action.simulate())
                    {
                        if (fluid.isEmpty())
                        {
                            return Math.min(2000, resource.getAmount());
                        }
                        if (!fluid.isFluidEqual(resource))
                        {
                            return 0;
                        }
                        return Math.min(2000 - fluid.getAmount(), resource.getAmount());
                    }
                    if (fluid.isEmpty())
                    {
                        fluid = new FluidStack(resource, Math.min(2000, resource.getAmount()));
                        return fluid.getAmount();
                    }
                    if (!fluid.isFluidEqual(resource))
                    {
                        return 0;
                    }
                    int filled = 2000 - fluid.getAmount();

                    if (resource.getAmount() < filled)
                    {
                        fluid.grow(resource.getAmount());
                        filled = resource.getAmount();
                    }
                    else
                    {
                        fluid.setAmount(2000);
                    }
                    return filled;
                }

                @Nonnull
                @Override
                public FluidStack drain(FluidStack resource, FluidAction action) {
                    if (resource.isEmpty() || !resource.isFluidEqual(fluid))
                    {
                        return FluidStack.EMPTY;
                    }
                    return drain(resource.getAmount(), action);
                }

                @Nonnull
                @Override
                public FluidStack drain(int maxDrain, FluidAction action) {
                    int drained = maxDrain;
                    if (fluid.getAmount() < drained)
                    {
                        drained = fluid.getAmount();
                    }
                    FluidStack stack = new FluidStack(fluid, drained);
                    if (action.execute() && drained > 0)
                    {
                        fluid.shrink(drained);
                    }
                    return stack;
                }
            }).cast();
        }
        else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void tick() {
        if(!(recipes.containsKey(item)&&canInsert.get(item)&&fluid.getAmount()>500)){
            return;
        }
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("fluid",fluid.writeToNBT(new CompoundNBT()));
        compound.put("stacks",item.write(new CompoundNBT()));
        return super.write(compound);
    }

    @Override
    public void read(CompoundNBT compound) {
        fluid=FluidStack.loadFluidStackFromNBT(compound.getCompound("fluid"));
        item=ItemStack.read(compound.getCompound("stacks"));
        super.read(compound);
    }
}
