package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseRecipe;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;

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
