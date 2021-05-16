package cx.rain.mc.forgemod.sinocraft.plugin.jei;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipes;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.plugin.jei.category.CookingCategory;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.RecipeManager;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.server.ServerLifecycleHooks;

@JeiPlugin
public class JEISupport implements IModPlugin {

    private static final ResourceLocation UID = new ResourceLocation(SinoCraft.MODID, "jei");

    @Override
    public ResourceLocation getPluginUid() {
        return UID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new CookingCategory(registration.getJeiHelpers()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        World world = null;
        if (FMLEnvironment.dist.isClient()) {
            world = net.minecraft.client.Minecraft.getInstance().world;
        } else {
            for (ServerWorld serverWorld : ServerLifecycleHooks.getCurrentServer().getWorlds()) {
                world = serverWorld;
                break;
            }
        }
        if (world != null) {
            RecipeManager recipeManager = world.getRecipeManager();
            registration.addRecipes(recipeManager.getRecipesForType(IModRecipes.getInstance().getCookingRecipe()), CookingCategory.ID);
        }
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registration) {
        registration.addRecipeCatalyst(new ItemStack(ModBlocks.POT.get()), CookingCategory.ID);
    }
}
