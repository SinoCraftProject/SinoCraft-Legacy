package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.crafting.CookingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.MillRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.SoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.SteamerRecipe;
import cx.rain.mc.forgemod.sinocraft.data.provider.base.ProviderBaseRecipe;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
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
        registerSteamerRecipes(consumer);
        registerStoneMillRecipe(consumer);
    }

    private void registerSoakRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(SoakingRecipe.builder(new ResourceLocation("sinocraft:dough"))
                .setInput(Ingredient.fromItems(ModItems.FLOUR.get()), 2)
                .setInput(Fluids.WATER)
                .setOutput(new ItemStack(ModItems.DOUGH.get()))
                .setTime(400).build());
        consumer.accept(SoakingRecipe.builder(new ResourceLocation("sinocraft:wood_pulp"))
                .setInput(Ingredient.fromItems(ModItems.BARK.get()), 3)
                .setInput(Fluids.WATER)
                .setOutput(new FluidStack(ModFluids.WOOD_PULP.get(), 1000))
                .setTime(400).build());
    }

    private void registerIronPotRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(CookingRecipe.builder(new ResourceLocation("sinocraft:pot_apple"))
                .addInput(Ingredient.fromItems(Items.RED_DYE))
                .setOutput(new ItemStack(Items.APPLE))
                .setHeat(1, Integer.MAX_VALUE)
                .setTime(40)
                .build());
    }

    private void registerSteamerRecipes(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(SteamerRecipe.builder(new ResourceLocation("sinocraft:steamer_test"))
                .setInput(Ingredient.fromItems(Items.APPLE))
                .setOutput(new ItemStack(Items.ACACIA_LOG))
                .setTime(100)
                .setHeat(10, 100)
                .build());
    }

    private void registerStoneMillRecipe(Consumer<IFinishedRecipe> consumer) {
        consumer.accept(MillRecipe.builder(new ResourceLocation("sinocraft:mill_wheat"))
                .setInput(Ingredient.fromItems(Items.WHEAT), 1)
                .setOutput(new ItemStack(ModItems.FLOUR.get()))
                .setTime(20)
                .build());
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
