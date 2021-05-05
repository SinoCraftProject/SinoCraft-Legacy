package cx.rain.mc.forgemod.sinocraft.crafting.potcooking;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

/**
 * @author NmmOC7
 */
public class ModPotCookingRecipes {
    public static final ArrayList<PotCookingRecipe> POT_COOKING_RECIPES = new ArrayList<>();

    public static final PotCookingRecipe APPLE_RECIPE = new PotCookingRecipe(
            new ItemStack[]{new ItemStack(Items.RED_DYE)},
            new ItemStack(Items.APPLE),
            getRecipeResource("pot_apple")
    );

    private static ResourceLocation getRecipeResource(String name) {
        return new ResourceLocation(SinoCraft.MODID, name);
    }
}
