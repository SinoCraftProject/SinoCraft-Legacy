package cx.rain.mc.forgemod.culturecraft.data.gen.provider.base;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.functions.CopyName;
import net.minecraft.world.storage.loot.functions.CopyNbt;
import net.minecraft.world.storage.loot.functions.SetContents;

public abstract class ProviderBaseLootTable extends LootTableProvider {
    public ProviderBaseLootTable(DataGenerator dataGeneratorIn) {
        super(dataGeneratorIn);
    }

    protected abstract void registerTables();

    protected LootTable.Builder createStandardTable(String name, Block block) {
        LootPool.Builder builder = LootPool.builder()
                .name(name)
                .rolls(ConstantRange.of(1))
                .addEntry(ItemLootEntry.builder(block));
        return LootTable.builder().addLootPool(builder);
    }
}
