package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseAdvancement;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import static net.minecraft.advancements.criterion.MinMaxBounds.IntBound.UNBOUNDED;

public class ProviderAdvancement extends ProviderBaseAdvancement {
    public static final String ID = SinoCraft.MODID;

    public ProviderAdvancement(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerAdvancements() {//如果有依赖关系请把子进度放在副进度后put
        Advancements.put(new ResourceLocation(ID,"basic/root"),RootAdvancement(
                new ItemStack(BlockItems.WHITE_MARBLE.get()),"advancement.sinocraft.basic.root.title","advancement.sinocraft.basic.root.description",
                new ResourceLocation(ID,"textures/gui/advancements/backgrounds/white_marble.png"),
                FrameType.TASK,false,true,true,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("only",new TickTrigger.Instance())
        );
        Advancements.put(new ResourceLocation(ID,"basic/knife"),ChildAdvancement(
                new ItemStack(Items.KNIFE.get()),"advancement.sinocraft.basic.knife.title","advancement.sinocraft.basic.knife.description",
                new ResourceLocation(ID,"basic/root"),FrameType.TASK,false,true,true,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife",this.hasItem(Items.KNIFE.get()))
        );

        Advancements.put(new ResourceLocation(ID,"basic/knife_killed"),ChildAdvancement(
                new ItemStack(Items.KNIFE.get()),"advancement.sinocraft.basic.knife_killed.title","advancement.sinocraft.basic.knife_killed.description",
                new ResourceLocation(ID,"basic/knife"),FrameType.GOAL,false,true,true,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife", new KilledTrigger.Instance(
                        CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(),EntityPredicate.ANY,new DamageSourcePredicate(
                                false,false,false,false, false,
                        false,false,false,EntityPredicate.ANY,
                        EntityPredicate.Builder.create().equipment(new EntityEquipmentPredicate(
                                ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,
                                this.baseProvider(Items.KNIFE.get()),ItemPredicate.ANY)).build()
                )
                ))
        );
    }

    @Override
    public String getName() {
        return "Advancements";
    }
}
