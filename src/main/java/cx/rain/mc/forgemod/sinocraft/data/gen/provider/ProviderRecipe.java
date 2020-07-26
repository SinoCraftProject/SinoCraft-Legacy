package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.data.TagItem;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseRecipe;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.util.ResourceLocation;

public class ProviderRecipe extends ProviderBaseRecipe {
    public static final String ID = SinoCraft.MODID;

    public ProviderRecipe(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes() {
        ShapedRecipeBuilder.shapedRecipe(Items.KNIFE_IRON.get()).
                key('@',net.minecraft.item.Items.STICK.getItem()).
                key('#',net.minecraft.item.Items.IRON_INGOT.getItem()).
                patternLine("  @").
                patternLine(" # ").
                patternLine("@  ").
                addCriterion("has_iron", this.hasItem(net.minecraft.item.Items.IRON_INGOT.getItem())).
                build(consumer,new ResourceLocation(ID,"knife1"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_MULBERRY.get(),4).
                addIngredient(TagItem.LOG_MULBERRY).
                addCriterion("has_log", this.hasItem(Blocks.LOG_MULBERRY.get())).
                build(consumer,new ResourceLocation(ID,"plank_mulberry1"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_PLUM.get(),4).
                addIngredient(TagItem.LOG_PLUM).
                addCriterion("has_log", this.hasItem(Blocks.LOG_PLUM.get())).
                build(consumer,new ResourceLocation(ID,"plank_plum1"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_PEACH.get(),4).
                addIngredient(TagItem.LOG_PEACH).
                addCriterion("has_log", this.hasItem(Blocks.LOG_PEACH.get())).
                build(consumer,new ResourceLocation(ID,"plank_peach1"));

        ShapelessRecipeBuilder.shapelessRecipe(Blocks.PLANK_WALNUT.get(),4).
                addIngredient(TagItem.LOG_WALNUT).
                addCriterion("has_log", this.hasItem(Blocks.LOG_WALNUT.get())).
                build(consumer,new ResourceLocation(ID,"plank_walnut1"));
    }
}
