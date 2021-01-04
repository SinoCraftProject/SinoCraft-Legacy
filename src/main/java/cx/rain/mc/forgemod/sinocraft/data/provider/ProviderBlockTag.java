package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.data.tag.TagBlock;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderBlockTag extends BlockTagsProvider {
    public ProviderBlockTag(DataGenerator generatorIn, ExistingFileHelper helper) {
        super(generatorIn, SinoCraft.MODID, helper);
    }

    @Override
    protected void registerTags() {
        getOrCreateBuilder(TagBlock.LOG_PEACH).add(ModBlocks.PEACH_LOG.get(), ModBlocks.PEACH_LOG_BARK.get(), ModBlocks.PEACH_LOG_STRIPPED.get(), ModBlocks.PEACH_LOG_STRIPPED_BARK.get());
        getOrCreateBuilder(TagBlock.LOG_WALNUT).add(ModBlocks.WALNUT_LOG.get(), ModBlocks.WALNUT_LOG_BARK.get(), ModBlocks.WALNUT_LOG_STRIPPED.get(), ModBlocks.WALNUT_LOG_STRIPPED_BARK.get());
        getOrCreateBuilder(TagBlock.LOG_PLUM).add(ModBlocks.PLUM_LOG.get(), ModBlocks.PLUM_LOG_BARK.get(), ModBlocks.PLUM_LOG_STRIPPED.get(), ModBlocks.PLUM_LOG_STRIPPED_BARK.get());
        getOrCreateBuilder(TagBlock.LOG_MULBERRY).add(ModBlocks.MULBERRY_LOG.get(), ModBlocks.MULBERRY_LOG_BARK.get(), ModBlocks.MULBERRY_LOG_STRIPPED.get(), ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get());
        getOrCreateBuilder(BlockTags.LOGS).addTags(TagBlock.LOG_PEACH, TagBlock.LOG_WALNUT, TagBlock.LOG_PLUM, TagBlock.LOG_MULBERRY);
        getOrCreateBuilder(BlockTags.PLANKS).add(ModBlocks.PEACH_PLANK.get(), ModBlocks.WALNUT_LOG.get(), ModBlocks.PLUM_LOG.get(), ModBlocks.MULBERRY_LOG.get());
        getOrCreateBuilder(BlockTags.LEAVES).add(ModBlocks.PEACH_LEAVES.get(), ModBlocks.WALNUT_LEAVES.get(), ModBlocks.PLUM_LEAVES.get(), ModBlocks.MULBERRY_LEAVES.get());
    }
}
