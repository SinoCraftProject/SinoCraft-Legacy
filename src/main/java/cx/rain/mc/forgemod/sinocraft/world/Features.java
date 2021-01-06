package cx.rain.mc.forgemod.sinocraft.world;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.world.config.FeatureConfigs;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Features {
    public static final ConfiguredFeature<?, ?> WHITE_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), FeatureConfigs.WHITE_MARBLE);
    public static final ConfiguredFeature<?, ?> BLACK_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), FeatureConfigs.BLACK_MARBLE);
    public static final ConfiguredFeature<?, ?> RED_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), FeatureConfigs.RED_MARBLE);

    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        RegistryKey<Biome> biome = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(event.getName(), "Who registered null name biome, naming criticism!"));

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD)) {
            addFeatureWorld(gen);
        }

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER)) {
            addFeatureNether(gen);
        }
    }

    private static void addFeatureWorld(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, WHITE_MARBLE);
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, BLACK_MARBLE);
    }

    private static void addFeatureNether(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_ORES, RED_MARBLE);
    }
}
