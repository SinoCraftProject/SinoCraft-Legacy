package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.IronPotRecipes;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.ModIronPotRecipes;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.SoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.data.provider.base.ProviderBaseRecipe;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.function.Consumer;

public class ProviderRecipe extends ProviderBaseRecipe {
    public static final String ID = SinoCraft.MODID;

    public ProviderRecipe(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(Consumer<IFinishedRecipe> consumer) {
        registerShapedRecipes(consumer);
        registerShapelessRecipes(consumer);
        registerFurnaceRecipes(consumer);
        registerSoakRecipes(consumer);
        registerIronPotRecipes(consumer);
    }

    private void registerSoakRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(new SoakingRecipe(
                new ItemStack(ModItems.FLOUR.get(), 2),
                new ItemStack(ModItems.DOUGH.get()),
                new ResourceLocation(ID + ":dough")
        ));
        consumer.accept(new SoakingRecipe(
                new ItemStack(ModItems.BARK.get(), 3),
                new FluidStack(ModFluids.WOOD_PULP.get(), 1000),
                new ResourceLocation(ID + ":wood_pulp")
        ));
    }

    private void registerIronPotRecipes(Consumer<IFinishedRecipe> consumer) {
        for (IronPotRecipes recipe: ModIronPotRecipes.IRON_POT_RECIPES) {
            consumer.accept(recipe);
        }
    }

    private void registerShapedRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.VAT.get())
                .patternLine("W W")
                .patternLine("W W")
                .patternLine("WSW")
                .key('W', ItemTags.PLANKS)
                .key('S', ItemTags.SLABS)
                .addCriterion("got_wood", hasItem(ItemTags.PLANKS))
                .build(consumer);
    }

    private void registerShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

    }

    private void registerFurnaceRecipes(Consumer<IFinishedRecipe> consumer) {

    }
}
