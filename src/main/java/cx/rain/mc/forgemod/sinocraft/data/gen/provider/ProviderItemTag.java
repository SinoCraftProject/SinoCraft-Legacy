package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.data.TagBlock;
import cx.rain.mc.forgemod.sinocraft.data.TagItem;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

import static cx.rain.mc.forgemod.sinocraft.data.TagItem.KNIFE;

public class ProviderItemTag extends ItemTagsProvider {
    public ProviderItemTag(DataGenerator generatorIn, ExistingFileHelper helper, BlockTagsProvider blockTagsProvider) {
        super(generatorIn, blockTagsProvider, SinoCraft.MODID, helper);
    }

    @Override
    protected void registerTags() {
        this.copy(TagBlock.LOG_MULBERRY,TagItem.LOG_MULBERRY);
        this.copy(TagBlock.LOG_PEACH,TagItem.LOG_PEACH);
        this.copy(TagBlock.LOG_PLUM,TagItem.LOG_PLUM);
        this.copy(TagBlock.LOG_WALNUT,TagItem.LOG_WALNUT);
        this.copy(BlockTags.LOGS, ItemTags.LOGS);
        this.copy(BlockTags.PLANKS, ItemTags.PLANKS);
        getOrCreateBuilder(KNIFE).add(Items.KNIFE_IRON.get(),Items.KNIFE_GOLD.get(),Items.KNIFE_DIAMOND.get());
    }
}
