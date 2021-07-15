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
        consumer.accept(CookingRecipe.builder(new ResourceLocation("sinocraft:pot_rice"))
                .addInput(Ingredient.fromItems(ModItems.RICE.get()))
                .addInput(Ingredient.fromItems(ModItems.RICE.get()))
                .addInput(Ingredient.fromItems(ModItems.RICE.get()))
                .setOutput(new ItemStack(ModItems.BOWL_WITH_RICE.get()))
                .setHeat(1, Integer.MAX_VALUE)
                .setContainer(new ItemStack(Items.BOWL))
                .setTime(40)
                .build());
        consumer.accept(CookingRecipe.builder(new ResourceLocation("sinocraft:pot_porridge"))
                .addInput(Ingredient.fromItems(ModItems.MILLET.get()))
                .addInput(Ingredient.fromItems(ModItems.MILLET.get()))
                .addInput(Ingredient.fromItems(ModItems.MILLET.get()))
                .setOutput(new ItemStack(ModItems.BOWL_WITH_PORRIDGE.get()))
                .setHeat(1, Integer.MAX_VALUE)
                .setContainer(new ItemStack(ModItems.BOWL_WITH_WATER.get()))
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
                .setInput(Ingredient.fromItems(Items.WHEAT))
                .setOutput(new ItemStack(ModItems.FLOUR.get()))
                .setTime(20)
                .build());
        consumer.accept(MillRecipe.builder(new ResourceLocation("sinocraft:mill_rice"))
                .setInput(Ingredient.fromItems(ModItems.RICE_SEED.get()))
                .setOutput(new ItemStack(ModItems.RICE.get()))
                .setTime(20)
                .build());
        consumer.accept(MillRecipe.builder(new ResourceLocation("sinocraft:mill_millet"))
                .setInput(Ingredient.fromItems(ModItems.MILLET_SEED.get()))
                .setOutput(new ItemStack(ModItems.MILLET.get()))
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
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.PAPER_DRYING_RACK.get())
                .patternLine("  S")
                .patternLine(" Ss")
                .patternLine("S s")
                .key('S', ItemTags.SLABS)
                .key('s', Items.STICK)
                .addCriterion("got_slab", hasItem(ItemTags.SLABS))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModBlocks.STONE_MILL.get())
                .patternLine("   ")
                .patternLine("SSs")
                .patternLine("SS ")
                .key('S', Items.SMOOTH_STONE_SLAB)
                .key('s', Items.STICK)
                .addCriterion("got_slab", hasItem(Items.SMOOTH_STONE_SLAB))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.STOVE.get())
                .patternLine("BBB")
                .patternLine("B B")
                .patternLine("BFB")
                .key('B', Items.BRICK)
                .key('F', Items.FURNACE)
                .addCriterion("got_furnace", hasItem(Items.FURNACE))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.BELLOWS.get())
                .patternLine("WWW")
                .patternLine(" PL")
                .patternLine("WWW")
                .key('P', Items.PISTON)
                .key('W', ItemTags.PLANKS)
                .key('L', Items.LEVER)
                .addCriterion("got_piston", hasItem(Items.PISTON))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModBlocks.POT.get())
                .patternLine("I I")
                .patternLine("III")
                .key('I', Items.IRON_INGOT)
                .addCriterion("got_iron", hasItem(Items.IRON_INGOT))
                .build(consumer);

        ShapedRecipeBuilder.shapedRecipe(ModItems.KNIFE_DIAMOND.get())
                .patternLine("  M")
                .patternLine(" M ")
                .patternLine("S  ")
                .key('S', Items.STICK)
                .key('M', Items.DIAMOND)
                .addCriterion("got_material", hasItem(Items.DIAMOND))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.KNIFE_GOLD.get())
                .patternLine("  M")
                .patternLine(" M ")
                .patternLine("S  ")
                .key('S', Items.STICK)
                .key('M', Items.GOLD_INGOT)
                .addCriterion("got_material", hasItem(Items.GOLD_INGOT))
                .build(consumer);
        ShapedRecipeBuilder.shapedRecipe(ModItems.KNIFE_IRON.get())
                .patternLine("  M")
                .patternLine(" M ")
                .patternLine("S  ")
                .key('S', Items.STICK)
                .key('M', Items.IRON_INGOT)
                .addCriterion("got_material", hasItem(Items.IRON_INGOT))
                .build(consumer);
    }

    private void registerShapelessRecipes(Consumer<IFinishedRecipe> consumer) {

    }

    private void registerFurnaceRecipes(Consumer<IFinishedRecipe> consumer) {

    }
}
