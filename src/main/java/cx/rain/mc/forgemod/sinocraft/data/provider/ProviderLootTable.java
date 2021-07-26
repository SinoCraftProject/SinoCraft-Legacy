package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.data.provider.base.ProviderBaseLootTable;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.data.DataGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraft.loot.conditions.RandomChance;
import net.minecraft.loot.functions.ApplyBonus;
import net.minecraft.loot.functions.ExplosionDecay;
import net.minecraft.util.ResourceLocation;

public class ProviderLootTable extends ProviderBaseLootTable {
    public ProviderLootTable(DataGenerator generatorIn) {
        super(generatorIn);
    }

//    public static ResourceLocation modLoc(String name) {
//        return new ResourceLocation(SinoCraft.MODID, name);
//    }
//
//    public static ResourceLocation dropLoc(String name) {
//        return modLoc("drops/" + name);
//    }

//    public void registerDrops() {
//        this.getLootTables().put(dropLoc("grass"),
//                LootTable.builder().addLootPool(
//                        LootPool.builder()
//                                .acceptCondition(RandomChance.builder(0.625f))
//                                .acceptFunction(ApplyBonus.uniformBonusCount(Enchantments.FORTUNE, 2))
//                                .acceptFunction(ExplosionDecay.builder())
//                                .addEntry(ItemLootEntry.builder(ModItems.CHILI_PEPPER_SEED.get()).weight(1))
//                                .addEntry(ItemLootEntry.builder(ModItems.GREEN_PEPPER_SEED.get()).weight(1))
//                                .addEntry(ItemLootEntry.builder(ModItems.SORGHUM_SEED.get()).weight(1))
//                                .addEntry(ItemLootEntry.builder(ModItems.CABBAGE_SEED.get()).weight(1))
//                                .addEntry(ItemLootEntry.builder(ModItems.EGGPLANT_SEED.get()).weight(1))
//                                .addEntry(ItemLootEntry.builder(ModItems.MILLET_SEED.get()).weight(1))
//                                .addEntry(ItemLootEntry.builder(ModItems.RICE_SEED.get()).weight(1))
//                ));
//    }

    @Override
    protected void registerTables() {
//        registerDrops();
    }
}
