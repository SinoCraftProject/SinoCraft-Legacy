package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
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
import net.minecraft.item.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;

public class ProviderAdvancement extends ProviderBaseAdvancement {
    public static final String ID = SinoCraft.MODID;

    public ProviderAdvancement(DataGenerator generatorIn) {
        super(generatorIn);
    }

    /**
     * Put advancement after dependencies.
     */
    @Override
    protected void registerAdvancements() {
        // SinoCraft root advancement.
        Advancements.put(new ResourceLocation(ID, "basic/root"), RootAdvancement(
                new ItemStack(ModBlockItems.WHITE_MARBLE.get()), "advancement.sinocraft.root.title", "advancement.sinocraft.root.description",
                new ResourceLocation(ID, "textures/gui/advancements/backgrounds/white_marble.png"),
                FrameType.TASK, false, false, false, new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("only", new TickTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND))
        );

        // Got knife.
        Advancements.put(new ResourceLocation(ID, "basic/knife"), ChildAdvancement(
                new ItemStack(ModItems.KNIFE_IRON.get()), "advancement.sinocraft.knife.title", "advancement.sinocraft.knife.description",
                new ResourceLocation(ID, "basic/root"), FrameType.TASK, true, true, false, new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife", this.hasItem(TagItem.KNIFE))
        );

        // Kill entity by knives.
        Advancements.put(new ResourceLocation(ID, "basic/kill_entity_by_knives"), ChildAdvancement(
                new ItemStack(ModItems.KNIFE_IRON.get()), "advancement.sinocraft.kill_entity_by_knives.title", "advancement.sinocraft.knife_killed.description",
                new ResourceLocation(ID, "basic/knife"), FrameType.GOAL, true, true, false, new AdvancementRewards.Builder().addExperience(0)).
                withCriterion("knife", new KilledTrigger.Instance(
                        CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(),
                        EntityPredicate.AndPredicate.ANY_AND,
                        EntityPredicate.AndPredicate.ANY_AND,
                        new DamageSourcePredicate(
                                false, false, false, false, false,
                                false, false, false, EntityPredicate.ANY,
                                EntityPredicate.Builder.create().equipment(new EntityEquipmentPredicate(
                                        ItemPredicate.ANY, ItemPredicate.ANY, ItemPredicate.ANY, ItemPredicate.ANY,
                                        this.baseProvider(TagItem.KNIFE), ItemPredicate.ANY)).build()
                        )
                ))
        );

        // Got bark.
        Advancements.put(new ResourceLocation(ID, "basic/get_bark"), ChildAdvancement(
                new ItemStack(ModItems.KNIFE_IRON.get()), "advancement.sinocraft.get_bark.title", "advancement.sinocraft.get_bark.description",
                new ResourceLocation(ID, "basic/knife"), FrameType.TASK, true, true, false, new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_bark", this.hasItem(ModItems.BARK.get())).
                withCriterion("use_knife", new RightClickBlockWithItemTrigger.Instance(
                        EntityPredicate.AndPredicate.ANY_AND,
                        LocationPredicate.Builder.builder().block(
                                BlockPredicate.Builder.createBuilder().setTag(BlockTags.LOGS).build()
                        ).build(),
                        ItemPredicate.Builder.create().tag(TagItem.KNIFE).build()
                ))
        );

        Advancements.put(new ResourceLocation(ID, "basic/kill_all_mobs_with_knife"), makeMobKnifeAdvancement(ChildAdvancement(
                new ItemStack(ModItems.KNIFE_GOLD.get()), "advancement.sinocraft.kill_all_mobs_with_knife.title", "advancement.sinocraft.kill_all_mobs_with_knife.description",
                new ResourceLocation(ID, "basic/kill_entity_by_knives"), FrameType.CHALLENGE, true, true, false, new AdvancementRewards.Builder().addExperience(500))
                )
        );

        Advancements.put(new ResourceLocation(ID, "basic/kill_all_entities_with_knife"), makeEntityKnifeAdvancement(ChildAdvancement(
                new ItemStack(ModItems.KNIFE_DIAMOND.get()), "advancement.sinocraft.kill_all_entities_with_knife.title", "advancement.sinocraft.kill_all_entities_with_knife.description",
                new ResourceLocation(ID, "basic/kill_all_mobs_with_knife"), FrameType.CHALLENGE, true, true, false, new AdvancementRewards.Builder().addExperience(1000))
                )
        );

        Advancements.put(new ResourceLocation(ID, "basic/get_china_ink"), ChildAdvancement(
                new ItemStack(ModItems.CHINA_INK.get()), "advancement.sinocraft.get_china_ink.title", "advancement.sinocraft.get_china_ink.description",
                new ResourceLocation(ID, "basic/root"), FrameType.TASK, true, true, false, new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_china_ink", this.hasItem(ModItems.CHINA_INK.get())
                )
        );

        Advancements.put(new ResourceLocation(ID, "basic/get_stone_mill"), ChildAdvancement(
                new ItemStack(ModItems.FLOUR.get()), "advancement.sinocraft.get_stone_mill.title", "advancement.sinocraft.get_stone_mill.description",
                new ResourceLocation(ID, "basic/root"), FrameType.TASK, true, true, false, new AdvancementRewards.Builder().addExperience(0)).
                withRequirementsStrategy(IRequirementsStrategy.AND).
                withCriterion("get_stone_mill", this.hasItem(ModBlocks.STONE_MILL.get())
                )
        );
    }

    @Override
    public String getName() {
        return "Advancements";
    }

    public static ItemStack EnchantItem(ItemStack stack, Enchantment enchant, int level) {
        stack.addEnchantment(enchant, level);
        return stack;
    }
}
