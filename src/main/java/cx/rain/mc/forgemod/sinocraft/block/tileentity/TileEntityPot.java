package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityHeat;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.capability.Heat;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nullable;
import java.util.List;

/**
 * @author NmmOC7
 */
public class TileEntityPot extends BaseTileEntityUpdatable implements cx.rain.mc.forgemod.sinocraft.api.block.ITileEntityPot {
    private final PotItemHandler itemHandler = new PotItemHandler(this);
    private final Heat heat = new Heat() {
        @Override
        public void onHeatChanged() {
            markDirty();
            reloadRecipe();
        }
    };
    private final ExtendedInventory inv = new ExtendedInventory();

    private int cooldown = 0;
    private int progress = 0;
    private int maxHeat = 0;
    private ICookingRecipe currentRecipe = null;
    private ResourceLocation recoveryRecipeLocation = null;

    private int[] slotMap = new int[6];
    private boolean isAdust = false;

    public TileEntityPot() {
        super(ModTileEntities.IRON_POT.get());
        for (int i = 0; i < slotMap.length; i++) {
            slotMap[i] = i;
        }
    }

    @Override
    public void onTick() {
        if (world == null || world.isRemote) {
            return;
        }
        if (cooldown > 0) {
            cooldown--;
        } else {
            heat.subHeat(1);
            cooldown = 40;
        }
        if (world != null && recoveryRecipeLocation != null) {
            world.getRecipeManager().getRecipe(recoveryRecipeLocation)
                    .filter(recipe -> recipe instanceof ICookingRecipe)
                    .ifPresent(recipe -> currentRecipe = (ICookingRecipe) recipe);
            recoveryRecipeLocation = null;
        }
        if (currentRecipe != null) {
            if (heat.getHeat() > currentRecipe.getMaxHeat()) {
                isAdust = true;
            }
            if (heat.getHeat() > currentRecipe.getMinHeat() && progress < currentRecipe.getCookingTime())
                progress ++;
        }
    }

    @Override
    public ItemStack extractInput() {
        for (int i = 5; i >= 0; i--) {
            ItemStack stack = itemHandler.getStackInSlot(i);
            if (!stack.isEmpty()) {
                itemHandler.setStackInSlot(i, ItemStack.EMPTY);
                return stack;
            }
        }
        return ItemStack.EMPTY;
    }

    @Override
    public ItemStack extractOutput(PlayerEntity player, Hand handIn) {
        if (currentRecipe == null || progress < currentRecipe.getCookingTime())
            return ItemStack.EMPTY;
        if (player.getHeldItem(handIn).getItem().getRegistryName().equals(currentRecipe.getContainer().getItem().getRegistryName())) {
            ItemStack stack = currentRecipe.getCraftingResult(inv).copy();
            inv.clear();
            if (isAdust) {
                isAdust = false;
                return currentRecipe.getAdustOutput().copy();
            }
            if (! stack.isEmpty()) {
                player.getHeldItem(handIn).shrink(1);
                player.inventory.addItemStackToInventory(stack);
            }
        }
        return player.getHeldItem(handIn);
    }

    @Override
    public ItemStack insertInput(ItemStack stack) {
        return itemHandler.addStack(stack);
    }

    @Override
    public List<ItemStack> getInputs() {
        return itemHandler.getInputs();
    }

    @Override
    public ItemStack getOutputs() {
        return currentRecipe == null ? ItemStack.EMPTY : currentRecipe.getRecipeOutput();
    }

    @Override
    public void reloadRecipe() {
        if (world == null) return;
        ICookingRecipe old;
        if (recoveryRecipeLocation != null) {
            old = world.getRecipeManager().getRecipe(recoveryRecipeLocation)
                    .filter(recipe -> recipe instanceof ICookingRecipe)
                    .map(recipe -> (ICookingRecipe) recipe)
                    .orElse(currentRecipe);
            recoveryRecipeLocation = null;
        } else old = currentRecipe;
        currentRecipe = world.getRecipeManager().getRecipe(ModRecipes.COOKING, inv, world).orElse(null);
        if (old != currentRecipe) {
            progress = 0;
            markDirty();
        }
    }

    private void setMaxHeat(int heat) {
        maxHeat = heat;
        markDirty();
    }

    @Override
    public int getMaxHeat() {
        return maxHeat;
    }

    @Override
    @Nullable
    public ICookingRecipe getCurrentRecipe() {
        if (world != null && recoveryRecipeLocation != null) {
            world.getRecipeManager().getRecipe(recoveryRecipeLocation)
                    .filter(recipe -> recipe instanceof ICookingRecipe)
                    .ifPresent(recipe -> currentRecipe = (ICookingRecipe) recipe);
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
        if (compound.contains("recipe", Constants.NBT.TAG_STRING)) {
            recoveryRecipeLocation = new ResourceLocation(compound.getString("recipe"));
        }
        progress = compound.getInt("process");
        slotMap = compound.getIntArray("slotMap");
        if (slotMap.length != 6) {
            slotMap = new int[6];
            for (int i = 0; i < 6; i++)
                slotMap[i] = i;
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
