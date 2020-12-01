package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.data.TagItem;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseRecipe;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.data.CookingRecipeBuilder;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.PotionUtils;
import net.minecraft.potion.Potions;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class ProviderRecipe extends ProviderBaseRecipe {
    public static final String ID = SinoCraft.MODID;

    public ProviderRecipe(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes() {
        ShapedRecipeBuilder.shapedRecipe(Items.KNIFE_IRON.get()).
                key('@', net.minecraft.item.Items.STICK).
                key('#', net.minecraft.item.Items.IRON_INGOT).
                patternLine("  @").
                patternLine(" # ").
                patternLine("@  ").
                addCriterion("has_iron", this.hasItem(net.minecraft.item.Items.IRON_INGOT)).
                build(consumer, new ResourceLocation(ID, "iron_knife"));

        ShapedRecipeBuilder.shapedRecipe(Items.KNIFE_GOLD.get()).
                key('@', net.minecraft.item.Items.STICK).
                key('#', net.minecraft.item.Items.GOLD_INGOT).
                patternLine("  @").
                patternLine(" # ").
                patternLine("@  ").
                addCriterion("has_gold", this.hasItem(net.minecraft.item.Items.GOLD_INGOT)).
                build(consumer, new ResourceLocation(ID, "gold_knife"));

        ShapedRecipeBuilder.shapedRecipe(Items.KNIFE_DIAMOND.get()).
                key('@', net.minecraft.item.Items.STICK).
                key('#', net.minecraft.item.Items.DIAMOND).
                patternLine("  @").
                patternLine(" # ").
                patternLine("@  ").
                addCriterion("has_diamond", this.hasItem(net.minecraft.item.Items.DIAMOND)).
                build(consumer, new ResourceLocation(ID, "diamond_knife"));

        ShapedRecipeBuilder.shapedRecipe(Items.INK_STONE.get()).
                key('#', net.minecraft.block.Blocks.STONE.asItem()).
                patternLine("# #").
                patternLine("###").
                addCriterion("has_stone", this.hasItem(net.minecraft.block.Blocks.STONE.asItem())).
                build(consumer, new ResourceLocation(ID, "ink_stone"));

        ShapedRecipeBuilder.shapedRecipe(Blocks.STONE_MILL.get()).
                key('#', net.minecraft.block.Blocks.STONE.asItem()).
                key('-', net.minecraft.item.Items.STICK.asItem()).
                patternLine(" #-").
                patternLine("###").
                addCriterion("has_stone", this.hasItem(net.minecraft.block.Blocks.STONE.asItem())).
                build(consumer, new ResourceLocation(ID, "stone_mill"));

        ShapedRecipeBuilder.shapedRecipe(Items.CHINESE_BRUSH.get()).
                key('@', net.minecraft.item.Items.STICK).
                key('#', net.minecraft.item.Items.FEATHER).
                patternLine("  @").
                patternLine(" @ ").
                patternLine("#  ").
                addCriterion("has_stick", this.hasItem(net.minecraft.item.Items.STICK)).
                build(consumer, new ResourceLocation(ID, "chinese_brush"));

        ShapedRecipeBuilder.shapedRecipe(Blocks.VAT.get()).
                key('#', ItemTags.PLANKS).
                patternLine("# #").
                patternLine("# #").
                patternLine("###").
                addCriterion("has_plank", this.hasItem(ItemTags.PLANKS)).
                build(consumer, new ResourceLocation(ID, "vat"));

        ShapedRecipeBuilder.shapedRecipe(Blocks.PAPER_DRYING_RACK.get()).
                key('#', ItemTags.PLANKS).
                key('|', net.minecraft.item.Items.STICK).
                patternLine("  #").
                patternLine(" #|").
                patternLine("# |").
                addCriterion("has_plank", this.hasItem(ItemTags.PLANKS)).
                build(consumer, new ResourceLocation(ID, "paper_drying_rack"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_MULBERRY.get(), 4).
                addIngredient(TagItem.LOG_MULBERRY).
                addCriterion("has_log", this.hasItem(Blocks.LOG_MULBERRY.get())).
                build(consumer, new ResourceLocation(ID, "plank_mulberry"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_PLUM.get(), 4).
                addIngredient(TagItem.LOG_PLUM).
                addCriterion("has_log", this.hasItem(Blocks.LOG_PLUM.get())).
                build(consumer, new ResourceLocation(ID, "plank_plum"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_PEACH.get(), 4).
                addIngredient(TagItem.LOG_PEACH).
                addCriterion("has_log", this.hasItem(Blocks.LOG_PEACH.get())).
                build(consumer, new ResourceLocation(ID, "plank_peach"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_WALNUT.get(), 4).
                addIngredient(TagItem.LOG_WALNUT).
                addCriterion("has_log", this.hasItem(Blocks.LOG_WALNUT.get())).
                build(consumer, new ResourceLocation(ID, "plank_walnut"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.CHINA_INK.get()).
                addIngredient(Items.INK_STONE.get()).
                addIngredient(Items.CHARCOAL_BLACK.get()).
                addIngredient(Items.CHARCOAL_BLACK.get()).
                addIngredient(net.minecraft.item.Items.SLIME_BALL).
                addIngredient(Ingredient.fromStacks(PotionUtils.addPotionToItemStack(new ItemStack(net.minecraft.item.Items.POTION), Potions.WATER))).
                addCriterion("has_charcoal_black", this.hasItem(Items.CHARCOAL_BLACK.get())).
                build(consumer, new ResourceLocation(ID, "china_ink"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.DUMPLING_WRAPPER.get()).
                addIngredient(Items.DOUGH.get()).
                addCriterion("has_dough", this.hasItem(Items.DOUGH.get())).
                build(consumer, new ResourceLocation(ID, "dumpling_wrapper"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.STUFFING.get()).
                addIngredient(net.minecraft.item.Items.POTATO).
                addIngredient(net.minecraft.item.Items.PORKCHOP).
                addCriterion("has_potato", this.hasItem(net.minecraft.item.Items.POTATO)).
                build(consumer, new ResourceLocation(ID, "stuffing"));

        ShapelessRecipeBuilder.shapelessRecipe(Items.DUMPLING.get()).
                addIngredient(Items.STUFFING.get()).
                addIngredient(Items.DUMPLING_WRAPPER.get()).
                addCriterion("has_stuffing", this.hasItem(Items.STUFFING.get())).
                build(consumer, new ResourceLocation(ID, "dumpling"));

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(net.minecraft.item.Items.CHARCOAL), Items.CHARCOAL_BLACK.get(), 2.0f, 400).
                addCriterion("has_charcoal", this.hasItem(net.minecraft.item.Items.CHARCOAL)).build(consumer, new ResourceLocation(ID, "charcoal_black_smelt"));

        CookingRecipeBuilder.smeltingRecipe(Ingredient.fromItems(Items.DUMPLING.get()), Items.COOKED_DUMPLING.get(), 2.0f, 400).
                addCriterion("has_dumpling", this.hasItem(Items.DUMPLING.get())).build(consumer, new ResourceLocation(ID, "dumpling_smelt"));
    }

    private void RegisterShapedRecipes() {

    }
}
