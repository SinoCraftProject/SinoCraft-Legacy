package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.advanement.RegistryTrigger;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import cx.rain.mc.forgemod.sinocraft.data.TagItem;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseAdvancement;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;

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
                FrameType.TASK,false,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("only",new TickTrigger.Instance())
        );

        Advancements.put(new ResourceLocation(ID,"basic/knife"),ChildAdvancement(
                new ItemStack(Items.KNIFE_IRON.get()),"advancement.sinocraft.basic.knife.title","advancement.sinocraft.basic.knife.description",
                new ResourceLocation(ID,"basic/root"),FrameType.TASK,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife",this.hasItem(TagItem.KNIFE))
        );

        Advancements.put(new ResourceLocation(ID,"basic/knife_killed"),ChildAdvancement(
                new ItemStack(Items.KNIFE_IRON.get()),"advancement.sinocraft.basic.knife_killed.title","advancement.sinocraft.basic.knife_killed.description",
                new ResourceLocation(ID,"basic/knife"),FrameType.GOAL,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife", new KilledTrigger.Instance(
                        CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(),EntityPredicate.ANY,new DamageSourcePredicate(
                                false,false,false,false, false,
                        false,false,false,EntityPredicate.ANY,
                        EntityPredicate.Builder.create().equipment(new EntityEquipmentPredicate(
                                ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,
                                this.baseProvider(TagItem.KNIFE),ItemPredicate.ANY)).build()
                )
                ))
        );

        Advancements.put(new ResourceLocation(ID,"basic/get_bark"),ChildAdvancement(
                new ItemStack(Items.KNIFE_IRON.get()),"advancement.sinocraft.basic.get_bark.title","advancement.sinocraft.basic.get_bark.description",
                new ResourceLocation(ID,"basic/knife"),FrameType.TASK,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_bark",this.hasItem(Items.BARK.get())).
                withCriterion("use_knife",new RightClickBlockWithItemTrigger.Instance(
                        RegistryTrigger.SHAVE_BARK_WITH_KNIFE.getId(),BlockPredicate.field_226231_a_,
                        StatePropertiesPredicate.EMPTY,ItemPredicate.ANY
                ))
        );
    }

    @Override
    public String getName() {
        return "Advancements";
    }
}
