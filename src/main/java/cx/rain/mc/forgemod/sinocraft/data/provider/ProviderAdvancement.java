package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.data.provider.base.ProviderBaseAdvancement;
import cx.rain.mc.forgemod.sinocraft.data.tag.TagItem;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.ItemStack;

public class ProviderAdvancement extends ProviderBaseAdvancement {
    public ProviderAdvancement(DataGenerator generatorIn) {
        super(generatorIn, SinoCraft.MODID);
    }

    /**
     * Put advancement after dependencies.
     */
    @Override
    protected void registerAdvancements() {
        // SinoCraft root advancement.
        add(modLoc("basic/root"), root(new ItemStack(ModBlockItems.WHITE_MARBLE.get()), "root",
                modLoc("textures/gui/advancements/backgrounds/white_marble.png"),
                FrameType.TASK, false)
                .withCriterion("only", new TickTrigger.Instance(EntityPredicate.AndPredicate.ANY_AND)));

        // Got knife.
        add(modLoc("basic/got_knife"), child(
                new ItemStack(ModItems.KNIFE_IRON.get()), "got_knife",
                modLoc("basic/root"), FrameType.TASK, true, true, false)
                .withCriterion("got_knife", hasItem(TagItem.KNIFE)));

        // Kill entity by knives.
        // FIXME: 2021/5/9 qyl: I break this.
//        add(modLoc("basic/kill_entity_by_knives"), child(
//                new ItemStack(ModItems.KNIFE_IRON.get()), "kill_entity_by_knives",
//                modLoc("basic/knife"), FrameType.GOAL, true, true, false)
//                .withCriterion("knife", new KilledTrigger.Instance(CriteriaTriggers.PLAYER_KILLED_ENTITY.getId(),
//                        EntityPredicate.AndPredicate.ANY_AND, EntityPredicate.AndPredicate.ANY_AND,
//                        new DamageSourcePredicate(false, false, false,
//                                false, false, false, false,
//                                false, EntityPredicate.ANY,
//                                EntityPredicate.Builder.create().equipment(new EntityEquipmentPredicate(
//                                        ItemPredicate.ANY, ItemPredicate.ANY, ItemPredicate.ANY, ItemPredicate.ANY,
//                                        baseProvider(TagItem.KNIFE), ItemPredicate.ANY)).build()))));

        // Got bark.
        add(modLoc("basic/got_bark"), child(new ItemStack(ModItems.KNIFE_IRON.get()), "got_bark",
                modLoc("basic/got_knife"), FrameType.TASK, true, true, false)
                .withCriterion("got_bark", hasItem(ModItems.BARK.get())));

        add(modLoc("basic/got_ink"), child(new ItemStack(ModItems.CHINESE_INK.get()), "got_ink",
                modLoc("basic/root"), FrameType.TASK, true, true, false)
                .withCriterion("got_ink", hasItem(ModItems.CHINESE_INK.get())));

        add(modLoc("basic/write_on_paper"), child(new ItemStack(ModItems.XUAN_PAPER.get()), "write_on_paper",
                modLoc("basic/got_ink"), FrameType.GOAL, true, true, false)
                .withCriterion("write_on_paper", hasItem(ModItems.XUAN_PAPER.get())));

        add(modLoc("basic/got_stone_mill"), child(
                new ItemStack(ModItems.FLOUR.get()), "got_stone_mill",
                modLoc("basic/root"), FrameType.TASK, true, true, false)
                .withCriterion("got_stone_mill", hasItem(ModBlocks.STONE_MILL.get())));

        add(modLoc("basic/eating_first"), child(
                new ItemStack(ModBlocks.STOVE.get()), "eating_first",
                modLoc("basic/root"), FrameType.TASK, true, true, false)
                .withCriterion("eating_first", hasItem(ModBlocks.STOVE.get())));

        // Todo: Change pot to porridge.
        add(modLoc("basic/porridge_ready"), child(
                new ItemStack(ModBlocks.POT.get()), "porridge_ready",
                modLoc("basic/eating_first"), FrameType.TASK, true, true, false)
                .withCriterion("eating_first", hasItem(ModBlocks.POT.get())));

        add(modLoc("basic/heroes_assembly"), child(
                new ItemStack(ModItems.HEROES_ASSEMBLE.get()), "heroes_assembly",
                modLoc("basic/eating_first"), FrameType.CHALLENGE, true, true, true)
                .withCriterion("heroes_assembly", hasItem(ModItems.HEROES_ASSEMBLE.get())));
    }

    @Override
    public String getName() {
        return "Advancements";
    }
}
