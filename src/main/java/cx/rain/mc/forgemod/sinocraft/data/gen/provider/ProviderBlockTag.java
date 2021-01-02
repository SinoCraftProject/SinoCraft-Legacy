package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

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
        getOrCreateBuilder(TagBlock.LOG_PEACH).add(ModBlocks.LOG_PEACH.get(), ModBlocks.LOG_PEACH_BARK.get(), ModBlocks.LOG_PEACH_STRIPPED.get(), ModBlocks.LOG_PEACH_STRIPPED_BARK.get());
        getOrCreateBuilder(TagBlock.LOG_WALNUT).add(ModBlocks.LOG_WALNUT.get(), ModBlocks.LOG_WALNUT_BARK.get(), ModBlocks.LOG_WALNUT_STRIPPED.get(), ModBlocks.LOG_WALNUT_STRIPPED_BARK.get());
        getOrCreateBuilder(TagBlock.LOG_PLUM).add(ModBlocks.LOG_PLUM.get(), ModBlocks.LOG_PLUM_BARK.get(), ModBlocks.LOG_PLUM_STRIPPED.get(), ModBlocks.LOG_PLUM_STRIPPED_BARK.get());
        getOrCreateBuilder(TagBlock.LOG_MULBERRY).add(ModBlocks.LOG_MULBERRY.get(), ModBlocks.LOG_MULBERRY_BARK.get(), ModBlocks.LOG_MULBERRY_STRIPPED.get(), ModBlocks.LOG_MULBERRY_STRIPPED_BARK.get());
        getOrCreateBuilder(BlockTags.LOGS).addTags(TagBlock.LOG_PEACH, TagBlock.LOG_WALNUT, TagBlock.LOG_PLUM, TagBlock.LOG_MULBERRY);
        getOrCreateBuilder(BlockTags.PLANKS).add(ModBlocks.PLANK_PEACH.get(), ModBlocks.LOG_WALNUT.get(), ModBlocks.LOG_PLUM.get(), ModBlocks.LOG_MULBERRY.get());
        getOrCreateBuilder(BlockTags.LEAVES).add(ModBlocks.LEAVES_PEACH.get(), ModBlocks.LEAVES_WALNUT.get(), ModBlocks.LEAVES_PLUM.get(), ModBlocks.LEAVES_MULBERRY.get());
    }
}
