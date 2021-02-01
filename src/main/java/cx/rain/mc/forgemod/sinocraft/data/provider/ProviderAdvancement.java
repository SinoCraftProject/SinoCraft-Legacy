package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.advancement.RegistryTrigger;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.data.provider.base.ProviderBaseAdvancement;
import cx.rain.mc.forgemod.sinocraft.data.tag.TagItem;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.IRequirementsStrategy;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

public class ProviderAdvancement extends ProviderBaseAdvancement {
    public static final String ID = SinoCraft.MODID;

    public ProviderAdvancement(DataGenerator generatorIn) {
        super(generatorIn);
    }

    /**
     * 如果有依赖关系请把子进度放在副进度后Put
     */
    @Override
    protected void registerAdvancements() {
        Advancements.put(new ResourceLocation(ID,"basic/root"),RootAdvancement(
                new ItemStack(ModBlocks.WHITE_MARBLE.get()),"advancement.sinocraft.basic.root.title","advancement.sinocraft.basic.root.description",
                new ResourceLocation(ID,"textures/gui/advancements/backgrounds/white_marble.png"),
                FrameType.TASK,false,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("only",new TickTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND))
        );

        Advancements.put(new ResourceLocation(ID,"basic/knife"),ChildAdvancement(
                new ItemStack(ModItems.KNIFE_IRON.get()),"advancement.sinocraft.basic.knife.title","advancement.sinocraft.basic.knife.description",
                new ResourceLocation(ID,"basic/root"),FrameType.TASK,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife",this.hasItem(TagItem.KNIFE))
        );

        Advancements.put(new ResourceLocation(ID,"basic/knife_killed"),ChildAdvancement(
                new ItemStack(ModItems.KNIFE_IRON.get()),"advancement.sinocraft.basic.knife_killed.title","advancement.sinocraft.basic.knife_killed.description",
                new ResourceLocation(ID,"basic/knife"),FrameType.GOAL,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife", new KilledTrigger.Instance(
                        CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(), EntityPredicate.AndPredicate.ANY_AND,
                        EntityPredicate.AndPredicate.ANY_AND,new DamageSourcePredicate(
                                false,false,false,
                        false, false, false,
                        false,false,EntityPredicate.ANY,
                                EntityPredicate.Builder.create().equipment(
                                        new EntityEquipmentPredicate(
                                                ItemPredicate.ANY,ItemPredicate.ANY,ItemPredicate.ANY,
                                                ItemPredicate.ANY, this.baseProvider(TagItem.KNIFE),
                                                ItemPredicate.ANY)
                                        ).build()
                            )
                        )
                )
        );

        Advancements.put(new ResourceLocation(ID,"basic/get_bark"),ChildAdvancement(
                new ItemStack(ModItems.KNIFE_IRON.get()), "advancement.sinocraft.basic.get_bark.title","advancement.sinocraft.basic.get_bark.description",
                new ResourceLocation(ID,"basic/knife"),FrameType.TASK,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_bark",this.hasItem(ModItems.BARK.get())).
                withCriterion("use_knife",new RightClickBlockWithItemTrigger.Instance(
                        EntityPredicate.AndPredicate.ANY_AND, LocationPredicate.ANY,ItemPredicate.ANY
                        )
                )
        );

        Advancements.put(new ResourceLocation("minecraft","adventure/kill_all_entities"),makeEntityAdvancement(ChildAdvancement(
                EnchantItem(new ItemStack(net.minecraft.item.Items.DIAMOND_SWORD), Enchantments.SHARPNESS,3),"advancement.minecraft.adventure.kill_all_entities.title","advancement.minecraft.adventure.kill_all_entities.description",
                new ResourceLocation("minecraft","adventure/kill_all_mobs"),FrameType.CHALLENGE,true,true,false,new AdvancementRewards.Builder().addExperience(500)).
                withParent(Advancements.get(new ResourceLocation(ID,"basic/root")).build(new ResourceLocation("minecraft","adventure/kill_all_mobs"))
        )));

        Advancements.put(new ResourceLocation(ID,"basic/kill_all_mobs_with_knife"),makeMobKnifeAdvancement(ChildAdvancement(
                new ItemStack(ModItems.KNIFE_GOLD.get()),"advancement.sinocraft.basic.kill_all_mobs_with_knife.title","advancement.sinocraft.basic.kill_all_mobs_with_knife.description",
                new ResourceLocation(ID,"basic/knife_killed"),FrameType.CHALLENGE,true,true,false,new AdvancementRewards.Builder().addExperience(500))
                )
        );

        Advancements.put(new ResourceLocation(ID,"basic/kill_all_entities_with_knife"),makeEntityKnifeAdvancement(ChildAdvancement(
                new ItemStack(ModItems.KNIFE_DIAMOND.get()),"advancement.sinocraft.basic.kill_all_entities_with_knife.title","advancement.sinocraft.basic.kill_all_entities_with_knife.description",
                new ResourceLocation(ID,"basic/kill_all_mobs_with_knife"),FrameType.CHALLENGE,true,true,false,new AdvancementRewards.Builder().addExperience(1000))
                )
        );
		
		Advancements.put(new ResourceLocation(ID,"basic/get_china_ink"),ChildAdvancement(
                new ItemStack(ModItems.CHINA_INK.get()),"advancement.sinocraft.basic.get_china_ink.title","advancement.sinocraft.basic.get_china_ink.description",
                new ResourceLocation(ID,"basic/root"),FrameType.TASK,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_china_ink",this.hasItem(ModItems.CHINA_INK.get())
                )
        );

        Advancements.put(new ResourceLocation(ID,"basic/get_stone_mill"),ChildAdvancement(
                new ItemStack(ModItems.FLOUR.get()),"advancement.sinocraft.basic.get_stone_mill.title","advancement.sinocraft.basic.get_stone_mill.description",
                new ResourceLocation(ID,"basic/root"), FrameType.TASK,true,true,false,new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_stone_mill",this.hasItem(ModBlocks.STONE_MILL.get())
                )
        );
    }

    @Override
    public String getName() {
        return "Advancements";
    }

    public static ItemStack EnchantItem(ItemStack stack, Enchantment enchant, int level) {
        stack.addEnchantment(enchant,level);
        return stack;
    }
}
