package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.ItemStackHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.Map;

public class TileEntityStoneMill extends TileEntityUpdatableBase {
    private static Map<String,ItemStack> recipes = new HashMap<>();

    private ItemStackHandler itemHandler = new ItemStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }

        @Override
        public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
            return recipes.containsKey(stack.getItem().getRegistryName().toString());
        }
    };

    private int progress=0;

    public static void registerRecipe(Item material,ItemStack result){
        recipes.put(material.getRegistryName().toString(),result);
    }

    private void registerDefaultRecipes(){
        registerRecipe(net.minecraft.item.Items.WHEAT, new ItemStack(ModItems.FLOUR.get()));
    }

    public TileEntityStoneMill() {
        super(ModTileEntities.STONE_MILL.get());
        registerDefaultRecipes();
        state=MachineState.CLOSE;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if(cap== CapabilityItemHandler.ITEM_HANDLER_CAPABILITY){
            return LazyOptional.of(()-> itemHandler).cast();
        }
        else {
            return super.getCapability(cap, side);
        }
    }

    @Override
    public void tick() {
    }

    public void onRotate() {
        if(itemHandler.getStackInSlot(0) == ItemStack.EMPTY) {
            return;
        }
        progress ++;
        if (progress == 20) {
            progress = 0;
            InventoryHelper.spawnItemStack(world,pos.getX(),pos.getY(),pos.getZ(),recipes.get(itemHandler.getStackInSlot(0).getItem().getRegistryName().toString()).copy());
            itemHandler.extractItem(0,1,false);
        }
        markDirty();
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.put("stacks",itemHandler.serializeNBT());
        compound.putInt("progress",progress);
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        itemHandler.deserializeNBT(compound.getCompound("stacks"));
        progress = compound.getInt("progress");
        super.read(state, compound);
    }

    @Override
    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.add(itemHandler.getStackInSlot(0));
        return list;
    }
}
