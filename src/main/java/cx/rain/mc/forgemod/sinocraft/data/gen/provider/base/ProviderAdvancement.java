package cx.rain.mc.forgemod.sinocraft.data.gen.provider.base;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ProviderAdvancement extends ProviderBaseAdvancement {
    public static final String ID = SinoCraft.MODID;

    public ProviderAdvancement(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerAdvancements() {
        Advancements.put(new ResourceLocation(ID,"basic/root"),RootAdvancement(
                new ItemStack(BlockItems.WHITE_MARBLE.get()),"!","!",
                new ResourceLocation(ID,"textures/gui/advancements/backgrounds/white_marble.png"),
                FrameType.TASK,false,true,true,new AdvancementRewards.Builder().addExperience(0))
        );
    }

    @Override
    public String getName() {
        return "Advancements";
    }
}
