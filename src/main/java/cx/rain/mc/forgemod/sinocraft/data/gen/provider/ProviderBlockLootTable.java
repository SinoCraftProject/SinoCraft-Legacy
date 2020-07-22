package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderBaseBlockLootTable;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraft.data.DataGenerator;

public class ProviderBlockLootTable extends ProviderBaseBlockLootTable {
    public ProviderBlockLootTable(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTables() {
        getLootTables().put(Blocks.LOG_PEACH.get(), tableNormal("log_peach", Blocks.LOG_PEACH.get()));
        getLootTables().put(Blocks.LOG_PEACH_STRIPPED.get(), tableNormal("log_peach_stripped", Blocks.LOG_PEACH_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_PEACH_BARK.get(), tableNormal("log_peach_skin", Blocks.LOG_PEACH_BARK.get()));
        getLootTables().put(Blocks.LOG_PEACH_STRIPPED_BARK.get(), tableNormal("log_peach_stripped_skin", Blocks.LOG_PEACH_STRIPPED_BARK.get()));
        getLootTables().put(Blocks.PLANK_PEACH.get(), tableNormal("plank_peach", Blocks.PLANK_PEACH.get()));
        getLootTables().put(Blocks.LEAVES_PEACH.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_peach_sapling", Blocks.SAPLING_PEACH.get()), droppingLeavesWithShearsOrSilkTouch("leaves_peach", Blocks.LEAVES_PEACH.get()), droppingFruitsAfterMatureWithChance("leaves_peach_fruits", Blocks.LEAVES_PEACH.get(), Items.PEACH.get())));
        getLootTables().put(Blocks.SAPLING_PEACH.get(), tableNormal("sapling_peach", Blocks.SAPLING_PEACH.get()));

        getLootTables().put(Blocks.LOG_WALNUT.get(), tableNormal("log_walnut", Blocks.LOG_WALNUT.get()));
        getLootTables().put(Blocks.LOG_WALNUT_STRIPPED.get(), tableNormal("log_walnut_stripped", Blocks.LOG_WALNUT_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_WALNUT_BARK.get(), tableNormal("log_walnut_skin", Blocks.LOG_WALNUT_BARK.get()));
        getLootTables().put(Blocks.LOG_WALNUT_STRIPPED_BARK.get(), tableNormal("log_walnut_stripped_skin", Blocks.LOG_WALNUT_STRIPPED_BARK.get()));
        getLootTables().put(Blocks.PLANK_WALNUT.get(), tableNormal("plank_walnut", Blocks.PLANK_WALNUT.get()));
        getLootTables().put(Blocks.LEAVES_WALNUT.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_walnut_sapling", Blocks.SAPLING_WALNUT.get()), droppingLeavesWithShearsOrSilkTouch("leaves_walnut", Blocks.LEAVES_WALNUT.get())));
        getLootTables().put(Blocks.SAPLING_WALNUT.get(), tableNormal("sapling_walnut", Blocks.SAPLING_WALNUT.get()));

        getLootTables().put(Blocks.LOG_PLUM.get(), tableNormal("log_plum", Blocks.LOG_PLUM.get()));
        getLootTables().put(Blocks.LOG_PLUM_STRIPPED.get(), tableNormal("log_plum_stripped", Blocks.LOG_PLUM_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_PLUM_BARK.get(), tableNormal("log_plum_skin", Blocks.LOG_PLUM_BARK.get()));
        getLootTables().put(Blocks.LOG_PLUM_STRIPPED_BARK.get(), tableNormal("log_plum_stripped_skin", Blocks.LOG_PLUM_STRIPPED_BARK.get()));
        getLootTables().put(Blocks.PLANK_PLUM.get(), tableNormal("plank_plum", Blocks.PLANK_PLUM.get()));
        getLootTables().put(Blocks.LEAVES_PLUM.get(), tableWithPools(droppingSaplingsAndSticksWithHigherChance("leaves_plum_sapling", Blocks.SAPLING_PLUM.get()), droppingLeavesWithShearsOrSilkTouch("leaves_plum", Blocks.LEAVES_PLUM.get())));
        getLootTables().put(Blocks.SAPLING_PLUM.get(), tableNormal("sapling_plum", Blocks.SAPLING_PLUM.get()));
        
        getLootTables().put(Blocks.LOG_MULBERRY.get(), tableNormal("log_mulberry", Blocks.LOG_MULBERRY.get()));
        getLootTables().put(Blocks.LOG_MULBERRY_STRIPPED.get(), tableNormal("log_mulberry_stripped", Blocks.LOG_MULBERRY_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_MULBERRY_BARK.get(), tableNormal("log_mulberry_skin", Blocks.LOG_MULBERRY_BARK.get()));
        getLootTables().put(Blocks.LOG_MULBERRY_STRIPPED_BARK.get(), tableNormal("log_mulberry_stripped_skin", Blocks.LOG_MULBERRY_STRIPPED_BARK.get()));
        getLootTables().put(Blocks.PLANK_MULBERRY.get(), tableNormal("plank_mulberry", Blocks.PLANK_MULBERRY.get()));
        getLootTables().put(Blocks.LEAVES_MULBERRY.get(), tableWithPools(droppingSilkwormAndSaplingAndSticksWithChance("leaves_mulberry_sapling", Items.SILKWORM.get(), Blocks.SAPLING_MULBERRY.get()), droppingLeavesWithShearsOrSilkTouch("leaves_mulberry", Blocks.LEAVES_MULBERRY.get())));
        getLootTables().put(Blocks.SAPLING_MULBERRY.get(), tableNormal("sapling_mulberry", Blocks.SAPLING_MULBERRY.get()));

        getLootTables().put(Blocks.WHITE_MARBLE.get(), tableNormal("white_marble", Blocks.WHITE_MARBLE.get()));
        getLootTables().put(Blocks.RED_MARBLE.get(), tableNormal("red_marble", Blocks.RED_MARBLE.get()));
        getLootTables().put(Blocks.BLACK_MARBLE.get(), tableNormal("black_marble", Blocks.BLACK_MARBLE.get()));

        getLootTables().put(Blocks.GREEN_RADISH_PLANT.get(), tableWithPools(droppingSeeds("green_radish_seed", Blocks.GREEN_RADISH_PLANT.get()), droppingCropsByAge("green_radish", Blocks.GREEN_RADISH_PLANT.get(), 7, Blocks.GREEN_RADISH_PLANT.get())));
        getLootTables().put(Blocks.SUMMER_RADISH_PLANT.get(), tableWithPools(droppingSeeds("summer_radish_seed", Blocks.SUMMER_RADISH_PLANT.get()), droppingCropsByAge("summer_radish", Blocks.SUMMER_RADISH_PLANT.get(), 7, Blocks.SUMMER_RADISH_PLANT.get())));
        getLootTables().put(Blocks.WHITE_RADISH_PLANT.get(), tableWithPools(droppingSeeds("white_radish_seed", Blocks.WHITE_RADISH_PLANT.get()), droppingCropsByAge("white_radish", Blocks.WHITE_RADISH_PLANT.get(), 7, Blocks.WHITE_RADISH_PLANT.get())));
        getLootTables().put(Blocks.CHILI_PEPPER_PLANT.get(), tableWithPools(droppingSeeds("chili_pepper_seeds", Items.CHILI_PEPPER_SEED.get()), droppingSeedsAndFruitsWithChanceByAge("chili_pepper", Blocks.CHILI_PEPPER_PLANT.get(), 7, Items.CHILI_PEPPER_SEED.get(), Items.CHILI_PEPPER.get())));
        getLootTables().put(Blocks.GREEN_PEPPER_PLANT.get(), tableWithPools(droppingSeeds("green_pepper_seeds", Items.GREEN_PEPPER_SEED.get()), droppingSeedsAndFruitsWithChanceByAge("green_pepper", Blocks.GREEN_PEPPER_PLANT.get(), 7, Items.GREEN_PEPPER_SEED.get(), Items.GREEN_PEPPER.get())));
        getLootTables().put(Blocks.EGGPLANT_PLANT.get(), tableWithPools(droppingSeeds("eggplant_seeds", Items.EGGPLANT_SEED.get()), droppingSeedsAndFruitsWithChanceByAge("eggplant", Blocks.EGGPLANT_PLANT.get(), 7, Items.EGGPLANT_SEED.get(), Items.EGGPLANT.get())));
        getLootTables().put(Blocks.CABBAGE_PLANT.get(), tableWithPools(droppingSeeds("cabbage_seeds", Items.CABBAGE_SEED.get()), droppingSeedsAndFruitsWithChanceByAge("cabbage", Blocks.CABBAGE_PLANT.get(), 7, Items.CABBAGE_SEED.get(), Items.CABBAGE.get())));
        getLootTables().put(Blocks.RICE_PLANT.get(), tableWithPools(droppingSeeds("rice_seeds", Items.RICE_SEED.get()), droppingCropsWithChanceByAge("rice", Blocks.RICE_PLANT.get(), 7, Items.RICE_SEED.get(), 2, 5)));
        getLootTables().put(Blocks.MILLET_PLANT.get(), tableWithPools(droppingSeeds("millet_seeds", Items.MILLET_SEED.get()), droppingCropsWithChanceByAge("millet", Blocks.MILLET_PLANT.get(), 7, Items.MILLET_SEED.get(), 2, 5)));
        getLootTables().put(Blocks.SORGHUM_PLANT.get(), tableWithPools(droppingSeeds("sorghum_seeds", Items.SORGHUM_SEED.get()), droppingCropsWithChanceByAge("sorghum", Blocks.SORGHUM_PLANT.get(), 7, Items.SORGHUM_SEED.get(), 2, 5)));
        getLootTables().put(Blocks.SOYBEAN_PLANT.get(), tableWithPools(droppingSeeds("soybean_seeds", Items.SOYBEAN.get()), droppingCropsWithChanceByAge("soybean", Blocks.SOYBEAN_PLANT.get(), 7, Items.SOYBEAN.get(), 2, 5)));

    }
}
