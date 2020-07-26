package cx.rain.mc.forgemod.sinocraft.data.gen.provider.base;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockLeavesGrowable;
import net.minecraft.advancements.criterion.EnchantmentPredicate;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.MinMaxBounds;
import net.minecraft.advancements.criterion.StatePropertiesPredicate;
import net.minecraft.block.Block;
import net.minecraft.block.CropsBlock;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Items;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.storage.loot.*;
import net.minecraft.world.storage.loot.conditions.BlockStateProperty;
import net.minecraft.world.storage.loot.conditions.ILootCondition;
import net.minecraft.world.storage.loot.conditions.MatchTool;
import net.minecraft.world.storage.loot.functions.SetCount;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public abstract class ProviderBaseLootTable implements IDataProvider {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create();
    private final Map<ResourceLocation, LootTable.Builder> lootTables = new HashMap<>();
    private DataGenerator generator = null;

    public ProviderBaseLootTable(DataGenerator generatorIn) {
        generator = generatorIn;
    }

    protected abstract void registerTables();

    public Map<ResourceLocation, LootTable.Builder> getLootTables() {
        return lootTables;
    }

    @Override
    public void act(DirectoryCache cache) {
        registerTables();

        Map<ResourceLocation, LootTable> tables = new HashMap<>();
        for (Map.Entry<ResourceLocation, LootTable.Builder> entry : lootTables.entrySet()) {
            tables.put(entry.getKey(), entry.getValue().setParameterSet(LootParameterSets.BLOCK).build());
        }
        writeTables(cache, tables);
    }

    private void writeTables(DirectoryCache cache, Map<ResourceLocation, LootTable> tables) {
        Path outputFolder = generator.getOutputFolder();
        tables.forEach((key, lootTable) -> {
            Path path = getPath(outputFolder, key);
            try {
                IDataProvider.save(GSON, cache, LootTableManager.toJson(lootTable), path);
            } catch (IOException e) {
                LOGGER.error("Couldn't write loot table {}", path, e);
            }
        });
    }

    private static Path getPath(Path pathIn, ResourceLocation id) {
        return pathIn.resolve("data/" + id.getNamespace() + "/loot_tables/" + id.getPath() + ".json");
    }

    @Override
    public String getName() {
        return "Loot Tables";
    }
}
