package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.ISoakingRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;

import javax.annotation.Nonnull;

public class VatItemHandler  extends ItemStackHandler {

    private final TileEntityVat te;

    public VatItemHandler(TileEntityVat te) {
        super(2);
        this.te = te;
    }

    public void setResult(ItemStack result) {
        stacks.set(0, result);
    }

    @Override
    protected void onContentsChanged(int slot) {
        World world = te.getWorld();
        if (world != null) {
            ISoakingRecipe old = te.currentRecipe;
            te.currentRecipe = null;
            for (ISoakingRecipe recipe : world.getRecipeManager().getRecipesForType(ModRecipes.SOAKING)) {
                if (recipe.matches(new RecipeWrapper(this), world)) {
                    te.currentRecipe = recipe;
                }
            }
            if (old != te.currentRecipe) {
                te.progress = 0;
            }
        }
        te.markDirty();
    }

    @Override
    public boolean isItemValid(int slot, @Nonnull ItemStack stack) {
        return true;
    }
}