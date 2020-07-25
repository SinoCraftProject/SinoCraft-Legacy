package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseRecipe;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.util.ResourceLocation;

public class ProviderRecipe extends ProviderBaseRecipe {
    public static final String ID = SinoCraft.MODID;

    public ProviderRecipe(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes() {
        ShapedRecipeBuilder.shapedRecipe(Items.KNIFE.get()).
                key('@',net.minecraft.item.Items.STICK.getItem()).
                key('#',net.minecraft.item.Items.IRON_INGOT.getItem()).
                patternLine("  @").
                patternLine(" # ").
                patternLine("@  ").
                addCriterion("has_iron", this.hasItem(net.minecraft.item.Items.IRON_INGOT.getItem())).
                build(consumer,new ResourceLocation(ID,"knife_get1"));
    }
}
