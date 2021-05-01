package cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * @author NmmOC7
 */
public class ModIronPotRecipes {
    public static final ArrayList<IronPotRecipes> IRON_POT_RECIPES = new ArrayList<>();

    public static final IronPotRecipes APPLE_RECIPE = new IronPotRecipes(
            new ItemStack[]{new ItemStack(Items.RED_DYE)},
            new ItemStack(Items.APPLE),
            getRecipeResource("pot_apple")
    );

    private static ResourceLocation getRecipeResource(String name) {
        return new ResourceLocation(SinoCraft.MODID, name);
    }
}
