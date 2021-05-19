package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityHeat;
import cx.rain.mc.forgemod.sinocraft.capability.Heat;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipes;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

/**
 * @author NmmOC7
 */
public class TileEntityPot extends TileEntityUpdatableBase {
    private final PotItemHandler itemHandler = new PotItemHandler(this);
    private final Heat heat = new Heat() {
        @Override
        public void onHeatChanged() {
            markDirty();
            updateRecipe();
        }
    };
    private final ExtendedInventory inv = new ExtendedInventory();

    private int cooldown = 0;
    private int progress = 0;
    private int maxHeat = 0;
    private ItemStack outputItem = ItemStack.EMPTY;
    private ICookingRecipe currentRecipe = null;

    private int[] slotMap = new int[6];

    public TileEntityPot() {
        super(ModTileEntities.IRON_POT.get());
        for (int i = 0; i < slotMap.length; i++) {
            slotMap[i] = i;
        }
    }

    @Override
    public void tick() {
        if (world == null || world.isRemote) {
            return;
        }
        if (cooldown > 0) {
            cooldown--;
        } else {
            heat.subHeat(1);
            cooldown = 40;
        }
        if (!outputItem.isEmpty()) {
            outputItem = itemHandler.insertItem(6, outputItem, false);
        }
        ICookingRecipe recipe = currentRecipe;
        if (recipe != null) {
            if (progress >= recipe.getCookingTime()) {
                if (!outputItem.isEmpty()) return;
                int[] map = Arrays.copyOf(slotMap, recipe.getInputSlotCount());
                // 消耗
                for (int i = 0; i < recipe.getInputSlotCount(); i++) {
                    int inputCount = recipe.getInput(map[i]).getCount();
                    ItemStack extractItem = itemHandler.extractItem(i, inputCount, true);
                    if (extractItem.getCount() < inputCount) {
                        return;
                    }
                }
                for (int i = 0; i < recipe.getInputSlotCount(); i++) {
                    itemHandler.extractItem(i, recipe.getInput(map[i]).getCount(), false);
                }
                // 产出
                ItemStack itemOutput = recipe.getCraftingResult(inv);
                if (!itemOutput.isEmpty()) {
                    itemOutput = itemOutput.copy();
                    ItemStack insert = itemHandler.insertItem(6, itemOutput, false);
                    if (!insert.isEmpty()) {
                        outputItem = insert;
                        markDirty();
                    }
                }

                progress = 0;
                currentRecipe = null;
                setMaxHeat(0);
            } else {
                if (heat.getHeat() < recipe.getMinHeat()) {
                    progress = 0;
                    setMaxHeat(0);
                } else {
                    progress++;
                    int heat = this.heat.getHeat();
                    if (heat > maxHeat) {
                        setMaxHeat(heat);
                    }
                }
            }
        }
    }

    public ItemStack removeStackOnInput() {
        for (int i = 5; i >= 0; i--) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                itemHandler.setStackInSlot(i, ItemStack.EMPTY);
                return stack.copy();
            }
        }
        return ItemStack.EMPTY;
    }

    public ItemStack removeStackOnOutput() {
        ItemStack stack = itemHandler.getStackInSlot(6);
        if (stack.isEmpty()) {
            return ItemStack.EMPTY;
        }
        itemHandler.setStackInSlot(6, ItemStack.EMPTY);
        return stack.copy();
    }

    public int addStackToInput(ItemStack stack) {
        return itemHandler.addStack(stack);
    }

    public List<ItemStack> getInput() {
        return itemHandler.getInputs();
    }

    public ItemStack getOutput() {
        return itemHandler.getStackInSlot(6);
    }

    public void updateRecipe() {
        ICookingRecipe old = currentRecipe;
        currentRecipe = null;
        if (world == null) return;
        currentRecipe = world.getRecipeManager().getRecipe(IModRecipes.getInstance().getCookingRecipe(), inv, world).orElse(null);
        if (old != currentRecipe) {
            progress = 0;
            markDirty();
        }
    }

    private void setMaxHeat(int heat) {
        maxHeat = heat;
        markDirty();
    }

    public int getMaxHeat() {
        return maxHeat;
    }

    @Nullable
    public ICookingRecipe getRecipe() {
        return currentRecipe;
    }

    public int getProgress() {
        return progress;
    }

    @Override
    public CompoundNBT getUpdateTag() {
        return super.getUpdateTag().merge(itemHandler.serializeNBT());
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT tag) {
        super.handleUpdateTag(state, tag);
        itemHandler.deserializeNBT(tag);
    }

    @Nullable
    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        return new SUpdateTileEntityPacket(pos, 1, itemHandler.serializeNBT());
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt) {
        itemHandler.deserializeNBT(pkt.getNbtCompound());
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        super.write(compound);
        compound = compound.merge(itemHandler.serializeNBT());
        compound.putInt("cooldown", cooldown);
        if (!outputItem.isEmpty()) {
            compound.put("outputItem", outputItem.write(new CompoundNBT()));
        }
        if (currentRecipe != null) {
            compound.putString("recipe", currentRecipe.getId().toString());
            compound.putInt("progress", progress);
            compound.putIntArray("slotMap", slotMap);
        }
        compound.putInt("heat", heat.getHeat());
        compound.putInt("maxHeat", maxHeat);
        return compound;
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        super.read(state, compound);
        itemHandler.deserializeNBT(compound);
        cooldown = compound.getInt("cooldown");
        if (compound.contains("outputItem", Constants.NBT.TAG_COMPOUND)) {
            outputItem = ItemStack.read(compound.getCompound("outputItem"));
        } else {
            outputItem = ItemStack.EMPTY;
        }
        if (compound.contains("recipe", Constants.NBT.TAG_STRING)) {
            assert world != null;
            world.getRecipeManager().getRecipe(new ResourceLocation(compound.getString("recipe")))
                    .filter(recipe -> recipe instanceof ICookingRecipe)
                    .ifPresent(recipe -> {
                        currentRecipe = (ICookingRecipe) recipe;
                        progress = compound.getInt("process");
                        slotMap = compound.getIntArray("slotMap");
                    });
        }
        heat.setHeat(compound.getInt("heat"));
        maxHeat = compound.getInt("maxHeat");
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap) {
        if (cap == CapabilityHeat.CAPABILITY) {
            return LazyOptional.of(() -> heat).cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY) {
            return LazyOptional.of(() -> itemHandler).cast();
        }
        return super.getCapability(cap);
    }

    class ExtendedInventory extends RecipeWrapper implements IExtendedRecipeInventory {

        public ExtendedInventory() {
            super(itemHandler);
        }

        @Override
        public ItemStack getInputItem(int index) {
            return itemHandler.getStackInSlot(index);
        }

        @Override
        public int getItemCount() {
            return 6;
        }

        @Override
        public int getCurrentHeat() {
            return heat.getHeat();
        }

        @Override
        public int getMaxHeat() {
            return maxHeat;
        }

        @Override
        public void setItemSlotMap(int input, int recipe) {
            slotMap[input] = recipe;
        }

        @Override
        public void removeAllSlotMap() {
            for (int i = 0; i < slotMap.length; i++) {
                slotMap[i] = i;
            }
        }
    }
}
