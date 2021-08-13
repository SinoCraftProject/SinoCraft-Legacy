package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.crafting.soaking.ISoakingRecipe;

import javax.annotation.Nullable;

public interface ITileEntityVat {

    /**
     * Check input and reset current recipe.
     */
    void reloadRecipe();

    /**
     * Get the current recipe.
     */
    @Nullable
    ISoakingRecipe getCurrentRecipe();

    /**
     * Get the time during the crafting.
     */
    int getProgress();

    /**
     * Set the time during the crafting.
     */
    void setProgress(int progress);
}
