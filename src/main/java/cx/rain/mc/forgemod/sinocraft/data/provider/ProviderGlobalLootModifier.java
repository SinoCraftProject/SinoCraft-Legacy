package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.data.modifier.SeedDropModifier;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.block.Blocks;
import net.minecraft.data.DataGenerator;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.*;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import cx.rain.mc.forgemod.sinocraft.data.modifier.ModModifiers;

public class ProviderGlobalLootModifier extends GlobalLootModifierProvider {
    public ProviderGlobalLootModifier(DataGenerator gen) {
        super(gen, SinoCraft.MODID);
    }

    @Override
    protected void start() {
        add("chili_pepper_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.CHILI_PEPPER_SEED.get())));
        add("chili_pepper_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.CHILI_PEPPER_SEED.get())));

        add("green_pepper_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.GREEN_PEPPER_SEED.get())));
        add("green_pepper_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.GREEN_PEPPER_SEED.get())));

        add("sorghum_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.SORGHUM_SEED.get())));
        add("sorghum_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.SORGHUM_SEED.get())));

        add("cabbage_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.CABBAGE_SEED.get())));
        add("cabbage_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.CABBAGE_SEED.get())));

        add("eggplant_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.EGGPLANT_SEED.get())));
        add("eggplant_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.EGGPLANT_SEED.get())));

        add("millet_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.MILLET_SEED.get())));
        add("millet_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.MILLET_SEED.get())));

        add("rice_seed_from_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.GRASS).build()
        }, new ItemStack(ModItems.RICE_SEED.get())));
        add("rice_seed_from_tall_grass", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.01f).build(),
                Inverted.builder(MatchTool.builder(ItemPredicate.Builder.create().item(Items.SHEARS))).build(),
                BlockStateProperty.builder(Blocks.TALL_GRASS).build()
        }, new ItemStack(ModItems.RICE_SEED.get())));


        add("white_radish_from_zombie", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.05f).build(),
                EntityHasProperty.builder(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.create().type(EntityType.ZOMBIE)).build()
        }, new ItemStack(ModBlockItems.WHITE_RADISH.get())));
        add("summer_radish_from_zombie", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.05f).build(),
                EntityHasProperty.builder(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.create().type(EntityType.ZOMBIE)).build()
        }, new ItemStack(ModBlockItems.SUMMER_RADISH.get())));
        add("green_radish_from_zombie", ModModifiers.SEED_DROP.get(), new SeedDropModifier(new ILootCondition[]{
                RandomChance.builder(0.05f).build(),
                EntityHasProperty.builder(LootContext.EntityTarget.THIS,
                        EntityPredicate.Builder.create().type(EntityType.ZOMBIE)).build()
        }, new ItemStack(ModBlockItems.GREEN_RADISH.get())));
    }
}
