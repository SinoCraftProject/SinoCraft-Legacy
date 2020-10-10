package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.data.TagBlock;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.BlockTags;

public class ProviderBlockTag extends BlockTagsProvider {
    public ProviderBlockTag(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        getBuilder(TagBlock.LOG_PEACH).add(Blocks.LOG_PEACH.get(), Blocks.LOG_PEACH_BARK.get(), Blocks.LOG_PEACH_STRIPPED.get(), Blocks.LOG_PEACH_STRIPPED_BARK.get());
        getBuilder(TagBlock.LOG_WALNUT).add(Blocks.LOG_WALNUT.get(), Blocks.LOG_WALNUT_BARK.get(), Blocks.LOG_WALNUT_STRIPPED.get(), Blocks.LOG_WALNUT_STRIPPED_BARK.get());
        getBuilder(TagBlock.LOG_PLUM).add(Blocks.LOG_PLUM.get(), Blocks.LOG_PLUM_BARK.get(), Blocks.LOG_PLUM_STRIPPED.get(), Blocks.LOG_PLUM_STRIPPED_BARK.get());
        getBuilder(TagBlock.LOG_MULBERRY).add(Blocks.LOG_MULBERRY.get(), Blocks.LOG_MULBERRY_BARK.get(), Blocks.LOG_MULBERRY_STRIPPED.get(), Blocks.LOG_MULBERRY_STRIPPED_BARK.get());
        getBuilder(BlockTags.LOGS).add(TagBlock.LOG_PEACH, TagBlock.LOG_WALNUT, TagBlock.LOG_PLUM, TagBlock.LOG_MULBERRY);
        getBuilder(BlockTags.PLANKS).add(Blocks.PLANK_PEACH.get(), Blocks.LOG_WALNUT.get(), Blocks.LOG_PLUM.get(), Blocks.LOG_MULBERRY.get());
        getBuilder(BlockTags.LEAVES).add(Blocks.LEAVES_PEACH.get(), Blocks.LEAVES_WALNUT.get(), Blocks.LEAVES_PLUM.get(), Blocks.LEAVES_MULBERRY.get());
    }
}
