package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.crafting.IMillRecipe;

import javax.annotation.Nullable;

public interface ITileEntityStoneMill {

    /**
     * Do rotate.
     */
    void rotate();

    /**
     * Get the current recipe.
     */
    @Nullable
    IMillRecipe getCurrentRecipe();

    /**
     * Check input and reset current recipe.
     */
    void reloadRecipe();

    /**
     * Get the rotate times.
     */
    int getProgress();

    /**
     * Set the rotate times.
     */
    void setProgress(int progress);
}
