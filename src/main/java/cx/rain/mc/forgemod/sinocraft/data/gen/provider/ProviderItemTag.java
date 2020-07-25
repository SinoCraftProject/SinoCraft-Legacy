package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.data.TagBlock;
import cx.rain.mc.forgemod.sinocraft.data.TagItem;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;

public class ProviderItemTag extends ItemTagsProvider {
    public ProviderItemTag(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTags() {
        this.copy(TagBlock.LOG_MULBERRY,TagItem.LOG_MULBERRY);
        this.copy(TagBlock.LOG_PEACH,TagItem.LOG_PEACH);
        this.copy(TagBlock.LOG_PLUM,TagItem.LOG_PLUM);
        this.copy(TagBlock.LOG_WALNUT,TagItem.LOG_WALNUT);
    }
}
