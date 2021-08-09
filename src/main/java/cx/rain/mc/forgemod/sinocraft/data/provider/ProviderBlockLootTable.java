package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockPlant;
import cx.rain.mc.forgemod.sinocraft.data.provider.base.ProviderBaseBlockLootTable;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.loot.ConstantRange;
import net.minecraft.loot.ItemLootEntry;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.RandomValueRange;
import net.minecraft.loot.conditions.BlockStateProperty;
import net.minecraft.loot.functions.SetCount;
import net.minecraft.util.IItemProvider;

public class ProviderBlockLootTable extends ProviderBaseBlockLootTable {
    public ProviderBlockLootTable(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTables() {
        getLootTables().put(ModBlocks.PEACH_LOG.get(), tableNormal("log_peach", ModBlocks.PEACH_LOG.get()));
        getLootTables().put(ModBlocks.PEACH_LOG_STRIPPED.get(), tableNormal("log_peach_stripped", ModBlocks.PEACH_LOG_STRIPPED.get()));
        getLootTables().put(ModBlocks.PEACH_LOG_BARK.get(), tableNormal("log_peach_skin", ModBlocks.PEACH_LOG_BARK.get()));
        getLootTables().put(ModBlocks.PEACH_LOG_STRIPPED_BARK.get(), tableNormal("log_peach_stripped_skin", ModBlocks.PEACH_LOG_STRIPPED_BARK.get()));
        getLootTables().put(ModBlocks.PEACH_PLANK.get(), tableNormal("plank_peach", ModBlocks.PEACH_PLANK.get()));
        getLootTables().put(ModBlocks.PEACH_LEAVES.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_peach_sapling", ModBlocks.PEACH_SAPLING.get()), droppingLeavesWithShearsOrSilkTouch("leaves_peach", ModBlocks.PEACH_LEAVES.get()), droppingFruitsAfterMatureWithChance("leaves_peach_fruits", ModBlocks.PEACH_LEAVES.get(), ModItems.PEACH.get())));
        getLootTables().put(ModBlocks.PEACH_SAPLING.get(), tableNormal("sapling_peach", ModBlocks.PEACH_SAPLING.get()));

        getLootTables().put(ModBlocks.WALNUT_LOG.get(), tableNormal("log_walnut", ModBlocks.WALNUT_LOG.get()));
        getLootTables().put(ModBlocks.WALNUT_LOG_STRIPPED.get(), tableNormal("log_walnut_stripped", ModBlocks.WALNUT_LOG_STRIPPED.get()));
        getLootTables().put(ModBlocks.WALNUT_LOG_BARK.get(), tableNormal("log_walnut_skin", ModBlocks.WALNUT_LOG_BARK.get()));
        getLootTables().put(ModBlocks.WALNUT_LOG_STRIPPED_BARK.get(), tableNormal("log_walnut_stripped_skin", ModBlocks.WALNUT_LOG_STRIPPED_BARK.get()));
        getLootTables().put(ModBlocks.WALNUT_PLANK.get(), tableNormal("plank_walnut", ModBlocks.WALNUT_PLANK.get()));
        getLootTables().put(ModBlocks.WALNUT_LEAVES.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_walnut_sapling", ModBlocks.WALNUT_SAPLING.get()), droppingLeavesWithShearsOrSilkTouch("leaves_walnut", ModBlocks.WALNUT_LEAVES.get())));
        getLootTables().put(ModBlocks.WALNUT_SAPLING.get(), tableNormal("sapling_walnut", ModBlocks.WALNUT_SAPLING.get()));

        getLootTables().put(ModBlocks.PLUM_LOG.get(), tableNormal("log_plum", ModBlocks.PLUM_LOG.get()));
        getLootTables().put(ModBlocks.PLUM_LOG_STRIPPED.get(), tableNormal("log_plum_stripped", ModBlocks.PLUM_LOG_STRIPPED.get()));
        getLootTables().put(ModBlocks.PLUM_LOG_BARK.get(), tableNormal("log_plum_skin", ModBlocks.PLUM_LOG_BARK.get()));
        getLootTables().put(ModBlocks.PLUM_LOG_STRIPPED_BARK.get(), tableNormal("log_plum_stripped_skin", ModBlocks.PLUM_LOG_STRIPPED_BARK.get()));
        getLootTables().put(ModBlocks.PLUM_PLANK.get(), tableNormal("plank_plum", ModBlocks.PLUM_PLANK.get()));
        getLootTables().put(ModBlocks.PLUM_LEAVES.get(), tableWithPools(droppingSaplingsAndSticksWithHigherChance("leaves_plum_sapling", ModBlocks.PLUM_SAPLING.get()), droppingLeavesWithShearsOrSilkTouch("leaves_plum", ModBlocks.PLUM_LEAVES.get())));
        getLootTables().put(ModBlocks.PLUM_SAPLING.get(), tableNormal("sapling_plum", ModBlocks.PLUM_SAPLING.get()));
        
        getLootTables().put(ModBlocks.MULBERRY_LOG.get(), tableNormal("log_mulberry", ModBlocks.MULBERRY_LOG.get()));
        getLootTables().put(ModBlocks.MULBERRY_LOG_STRIPPED.get(), tableNormal("log_mulberry_stripped", ModBlocks.MULBERRY_LOG_STRIPPED.get()));
        getLootTables().put(ModBlocks.MULBERRY_LOG_BARK.get(), tableNormal("log_mulberry_skin", ModBlocks.MULBERRY_LOG_BARK.get()));
        getLootTables().put(ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get(), tableNormal("log_mulberry_stripped_skin", ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get()));
        getLootTables().put(ModBlocks.MULBERRY_PLANK.get(), tableNormal("plank_mulberry", ModBlocks.MULBERRY_PLANK.get()));
        getLootTables().put(ModBlocks.MULBERRY_LEAVES.get(), tableWithPools(droppingSilkwormAndSaplingAndSticksWithChance("leaves_mulberry_sapling", net.minecraft.block.Blocks.AIR, ModBlocks.MULBERRY_SAPLING.get()), droppingLeavesWithShearsOrSilkTouch("leaves_mulberry", ModBlocks.MULBERRY_LEAVES.get())));
        getLootTables().put(ModBlocks.MULBERRY_SAPLING.get(), tableNormal("sapling_mulberry", ModBlocks.MULBERRY_SAPLING.get()));

        getLootTables().put(ModBlocks.WHITE_MARBLE.get(), tableNormal("white_marble", ModBlocks.WHITE_MARBLE.get()));
        getLootTables().put(ModBlocks.RED_MARBLE.get(), tableNormal("red_marble", ModBlocks.RED_MARBLE.get()));
        getLootTables().put(ModBlocks.BLACK_MARBLE.get(), tableNormal("black_marble", ModBlocks.BLACK_MARBLE.get()));

        getLootTables().put(ModBlocks.GREEN_RADISH_PLANT.get(), tableWithPools(droppingSeeds("green_radish_seed", ModBlocks.GREEN_RADISH_PLANT.get()), droppingCropsByStage("green_radish", ModBlocks.GREEN_RADISH_PLANT.get(), 7, ModBlocks.GREEN_RADISH_PLANT.get())));
        getLootTables().put(ModBlocks.SUMMER_RADISH_PLANT.get(), tableWithPools(droppingSeeds("summer_radish_seed", ModBlocks.SUMMER_RADISH_PLANT.get()), droppingCropsByStage("summer_radish", ModBlocks.SUMMER_RADISH_PLANT.get(), 7, ModBlocks.SUMMER_RADISH_PLANT.get())));
        getLootTables().put(ModBlocks.WHITE_RADISH_PLANT.get(), tableWithPools(droppingSeeds("white_radish_seed", ModBlocks.WHITE_RADISH_PLANT.get()), droppingCropsByStage("white_radish", ModBlocks.WHITE_RADISH_PLANT.get(), 7, ModBlocks.WHITE_RADISH_PLANT.get())));
        getLootTables().put(ModBlocks.CHILI_PEPPER_PLANT.get(), tableWithPools(droppingSeeds("chili_pepper_seeds", ModItems.CHILI_PEPPER_SEED.get()), droppingSeedsAndFruitsWithChanceByStage("chili_pepper", ModBlocks.CHILI_PEPPER_PLANT.get(), 7, ModItems.CHILI_PEPPER_SEED.get(), ModItems.CHILI_PEPPER.get())));
        getLootTables().put(ModBlocks.GREEN_PEPPER_PLANT.get(), tableWithPools(droppingSeeds("green_pepper_seeds", ModItems.GREEN_PEPPER_SEED.get()), droppingSeedsAndFruitsWithChanceByStage("green_pepper", ModBlocks.GREEN_PEPPER_PLANT.get(), 7, ModItems.GREEN_PEPPER_SEED.get(), ModItems.GREEN_PEPPER.get())));
        getLootTables().put(ModBlocks.EGGPLANT_PLANT.get(), tableWithPools(droppingSeeds("eggplant_seeds", ModItems.EGGPLANT_SEED.get()), droppingSeedsAndFruitsWithChanceByStage("eggplant", ModBlocks.EGGPLANT_PLANT.get(), 7, ModItems.EGGPLANT_SEED.get(), ModItems.EGGPLANT.get())));
        getLootTables().put(ModBlocks.CABBAGE_PLANT.get(), tableWithPools(droppingSeeds("cabbage_seeds", ModItems.CABBAGE_SEED.get()), droppingSeedsAndFruitsWithChanceByStage("cabbage", ModBlocks.CABBAGE_PLANT.get(), 7, ModItems.CABBAGE_SEED.get(), ModItems.CABBAGE.get())));
        getLootTables().put(ModBlocks.RICE_PLANT.get(), tableWithPools(droppingSeeds("rice_seeds", ModItems.RICE_SEED.get()), droppingCropsWithChanceByStage("rice", ModBlocks.RICE_PLANT.get(), 7, ModItems.RICE_SEED.get(), 1, 3)));
        getLootTables().put(ModBlocks.MILLET_PLANT.get(), tableWithPools(droppingSeeds("millet_seeds", ModItems.MILLET_SEED.get()), droppingCropsWithChanceByStage("millet", ModBlocks.MILLET_PLANT.get(), 7, ModItems.MILLET_SEED.get(), 1, 3)));
        getLootTables().put(ModBlocks.SORGHUM_PLANT.get(), tableWithPools(droppingSeeds("sorghum_seeds", ModItems.SORGHUM_SEED.get()), droppingCropsWithChanceByStage("sorghum", ModBlocks.SORGHUM_PLANT.get(), 7, ModItems.SORGHUM_SEED.get(), 1, 3)));
        getLootTables().put(ModBlocks.SOYBEAN_PLANT.get(), tableWithPools(droppingSeeds("soybean_seeds", ModItems.SOYBEAN.get()), droppingCropsWithChanceByStage("soybean", ModBlocks.SOYBEAN_PLANT.get(), 7, ModItems.SOYBEAN.get(), 1, 3)));

        getLootTables().put(ModBlocks.STONE_MILL.get(), tableNormal("black_marble", ModBlocks.STONE_MILL.get()));
        getLootTables().put(ModBlocks.PAPER_DRYING_RACK.get(), tableNormal("black_marble", ModBlocks.PAPER_DRYING_RACK.get()));
        getLootTables().put(ModBlocks.VAT.get(), tableNormal("vat", ModBlocks.VAT.get()));
        getLootTables().put(ModBlocks.STOVE.get(), tableNormal("black_marble", ModBlocks.STOVE.get()));
    }

    protected LootPool.Builder droppingCropsByStage(String name, BlockPlant block, int age, IItemProvider crops) {
        return droppingCropsWithChanceByStage(name, block, age, crops, 1, 4);
    }

    protected LootPool.Builder droppingCropsWithChanceByStage(String name, BlockPlant block, int age, IItemProvider crops, int min, int max) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(block.getAgeProperty(), age)))
                .addEntry(ItemLootEntry.builder(crops).acceptFunction(SetCount.builder(RandomValueRange.of(min, max))));
    }

    protected LootPool.Builder droppingSeedsAndFruitsWithChanceByStage(String name, BlockPlant block, int age, IItemProvider seeds, IItemProvider fruits) {
        return LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .acceptCondition(BlockStateProperty.builder(block).fromProperties(StatePropertiesPredicate.Builder.newBuilder().withIntProp(block.getAgeProperty(), age)))
                .addEntry(ItemLootEntry.builder(seeds).acceptFunction(SetCount.builder(RandomValueRange.of(0, 3))))
                .addEntry(ItemLootEntry.builder(fruits).acceptFunction(SetCount.builder(RandomValueRange.of(1, 3))));
    }
}
