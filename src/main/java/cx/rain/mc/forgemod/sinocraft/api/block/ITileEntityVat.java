package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ISoakingRecipe;

import javax.annotation.Nullable;

public interface ITileEntityVat {
    void updateRecipe();

    @Nullable
    ISoakingRecipe getCurrentRecipe();

    int getProgress();

    void setProgress(int progress);
}
