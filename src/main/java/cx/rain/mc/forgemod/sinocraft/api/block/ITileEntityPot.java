package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.List;

public interface ITileEntityPot {

    ItemStack extractInput();

    ItemStack extractOutput();

    int insertInput(ItemStack stack);

    List<ItemStack> getInputs();

    ItemStack getOutputs();

    void reloadRecipe();

    int getMaxHeat();

    @Nullable
    ICookingRecipe getCurrentRecipe();

    int getProgress();

    void setProgress(int progress);
}
