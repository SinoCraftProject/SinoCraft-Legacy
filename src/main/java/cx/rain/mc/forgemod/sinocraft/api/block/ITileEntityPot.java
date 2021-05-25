package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

/**
 * An interface to invoke the tile entity.
 */
public interface ITileEntityPot {

    /**
     * Return the last input item and remove it from pot.
     */
    ItemStack extractInput();

    /**
     * Return the output item and remove it from pot.
     */
    ItemStack extractOutput();

    /**
     * Add an input item to the pot, return the item can not insert to the pot.
     */
    ItemStack insertInput(ItemStack stack);

    /**
     * Get all input items in the pot.
     */
    List<ItemStack> getInputs();

    /**
     * Get the output item in the pot.
     */
    ItemStack getOutputs();

    /**
     * Check input and reset current recipe.
     */
    void reloadRecipe();

    /**
     * Get max heat during the crafting.
     */
    int getMaxHeat();

    /**
     * Get the current recipe.
     */
    @Nullable
    ICookingRecipe getCurrentRecipe();

    /**
     * Get the time during the crafting.
     */
    int getProgress();

    /**
     * Set the time during the crafting.
     */
    void setProgress(int progress);
}
