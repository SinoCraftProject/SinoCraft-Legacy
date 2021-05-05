package cx.rain.mc.forgemod.sinocraft.api.crafting.cooking;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.cooking.IStoveInventory;
import net.minecraft.world.World;

/**
 * Recipe interface
 * The advanced recipe must implements it
 * @author Infinity_rain
 */
public abstract class CookingRecipeBase implements ICookingRecipe {
    @Override
    public boolean matches(IStoveInventory inv, World worldIn) {
        return matches(inv.getAllStacks(), worldIn, inv.getHeat());
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }
}
