package cx.rain.mc.forgemod.culturecraft.data.gen.provider;

import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.data.gen.provider.base.ProviderBaseBlockLootTable;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.data.DataGenerator;

public class ProviderBlockLootTable extends ProviderBaseBlockLootTable {
    public ProviderBlockLootTable(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerTables() {
        getLootTables().put(Blocks.LOG_PEACH.get(), tableNormal("log_peach", Blocks.LOG_PEACH.get()));
        getLootTables().put(Blocks.LOG_PEACH_STRIPPED.get(), tableNormal("log_peach_stripped", Blocks.LOG_PEACH_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_PEACH_SKIN.get(), tableNormal("log_peach_skin", Blocks.LOG_PEACH_SKIN.get()));
        getLootTables().put(Blocks.LOG_PEACH_STRIPPED_SKIN.get(), tableNormal("log_peach_stripped_skin", Blocks.LOG_PEACH_STRIPPED_SKIN.get()));
        getLootTables().put(Blocks.PLANK_PEACH.get(), tableNormal("plank_peach", Blocks.PLANK_PEACH.get()));
        getLootTables().put(Blocks.LEAVES_PEACH.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_peach_sapling", Blocks.SAPLING_PEACH.get()), droppingLeavesWithShearsOrSilkTouch("leaves_peach", Blocks.LEAVES_PEACH.get()), droppingFruitsAfterMatureWithChance("leaves_peach_fruits", Blocks.LEAVES_PEACH.get(), Items.PEACH.get())));
        getLootTables().put(Blocks.SAPLING_PEACH.get(), tableNormal("sapling_peach", Blocks.SAPLING_PEACH.get()));

        getLootTables().put(Blocks.LOG_WALNUT.get(), tableNormal("log_walnut", Blocks.LOG_WALNUT.get()));
        getLootTables().put(Blocks.LOG_WALNUT_STRIPPED.get(), tableNormal("log_walnut_stripped", Blocks.LOG_WALNUT_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_WALNUT_SKIN.get(), tableNormal("log_walnut_skin", Blocks.LOG_WALNUT_SKIN.get()));
        getLootTables().put(Blocks.LOG_WALNUT_STRIPPED_SKIN.get(), tableNormal("log_walnut_stripped_skin", Blocks.LOG_WALNUT_STRIPPED_SKIN.get()));
        getLootTables().put(Blocks.PLANK_WALNUT.get(), tableNormal("plank_walnut", Blocks.PLANK_WALNUT.get()));
        getLootTables().put(Blocks.LEAVES_WALNUT.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_walnut_sapling", Blocks.SAPLING_WALNUT.get()), droppingLeavesWithShearsOrSilkTouch("leaves_walnut", Blocks.LEAVES_WALNUT.get())));
        getLootTables().put(Blocks.SAPLING_WALNUT.get(), tableNormal("sapling_walnut", Blocks.SAPLING_WALNUT.get()));

        getLootTables().put(Blocks.LOG_PLUM.get(), tableNormal("log_plum", Blocks.LOG_PLUM.get()));
        getLootTables().put(Blocks.LOG_PLUM_STRIPPED.get(), tableNormal("log_plum_stripped", Blocks.LOG_PLUM_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_PLUM_SKIN.get(), tableNormal("log_plum_skin", Blocks.LOG_PLUM_SKIN.get()));
        getLootTables().put(Blocks.LOG_PLUM_STRIPPED_SKIN.get(), tableNormal("log_plum_stripped_skin", Blocks.LOG_PLUM_STRIPPED_SKIN.get()));
        getLootTables().put(Blocks.PLANK_PLUM.get(), tableNormal("plank_plum", Blocks.PLANK_PLUM.get()));
        //getLootTables().put(Blocks.LEAVES_PLUM.get(), tableWithPools(droppingSaplingsAndSticksWithChance("leaves_plum_sapling", Blocks.SAPLING_PLUM.get()), droppingLeavesWithShearsOrSilkTouch("leaves_plum", Blocks.LEAVES_PLUM.get())));
        //getLootTables().put(Blocks.SAPLING_PLUM.get(), tableNormal("sapling_plum", Blocks.SAPLING_PLUM.get()));
        
        getLootTables().put(Blocks.LOG_MULBERRY.get(), tableNormal("log_mulberry", Blocks.LOG_MULBERRY.get()));
        getLootTables().put(Blocks.LOG_MULBERRY_STRIPPED.get(), tableNormal("log_mulberry_stripped", Blocks.LOG_MULBERRY_STRIPPED.get()));
        getLootTables().put(Blocks.LOG_MULBERRY_SKIN.get(), tableNormal("log_mulberry_skin", Blocks.LOG_MULBERRY_SKIN.get()));
        getLootTables().put(Blocks.LOG_MULBERRY_STRIPPED_SKIN.get(), tableNormal("log_mulberry_stripped_skin", Blocks.LOG_MULBERRY_STRIPPED_SKIN.get()));
        getLootTables().put(Blocks.PLANK_MULBERRY.get(), tableNormal("plank_mulberry", Blocks.PLANK_MULBERRY.get()));
        getLootTables().put(Blocks.LEAVES_MULBERRY.get(), tableWithPools(droppingSilkwormAndSaplingAndSticksWithChance("leaves_mulberry_sapling", Items.SILKWORM.get(), Blocks.SAPLING_MULBERRY.get()), droppingLeavesWithShearsOrSilkTouch("leaves_mulberry", Blocks.LEAVES_MULBERRY.get())));
        getLootTables().put(Blocks.SAPLING_MULBERRY.get(), tableNormal("sapling_mulberry", Blocks.SAPLING_MULBERRY.get()));
    }
}
