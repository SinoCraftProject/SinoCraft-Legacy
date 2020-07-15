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
    }
}
