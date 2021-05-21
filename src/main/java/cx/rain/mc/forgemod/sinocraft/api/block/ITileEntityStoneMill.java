package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.crafting.IMillRecipe;

import javax.annotation.Nullable;

public interface ITileEntityStoneMill {
    void rotate();

    @Nullable
    IMillRecipe getCurrentRecipe();

    void reloadRecipe();

    int getProgress();

    void setProgress(int progress);
}
