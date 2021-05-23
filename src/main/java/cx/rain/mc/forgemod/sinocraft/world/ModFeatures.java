package cx.rain.mc.forgemod.sinocraft.world;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.world.config.FeatureConfigs;
import net.minecraft.util.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.world.BiomeGenerationSettingsBuilder;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.Objects;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModFeatures {
    @SubscribeEvent
    public static void onBiomeLoading(BiomeLoadingEvent event) {
        BiomeGenerationSettingsBuilder gen = event.getGeneration();
        RegistryKey<Biome> biome = RegistryKey.getOrCreateKey(ForgeRegistries.Keys.BIOMES,
                Objects.requireNonNull(event.getName(), "Who registered null name biome, naming criticism!"));

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD)) {
            addFeatureWorld(gen);

            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS)) {
                addFeaturePlains(gen);
            }

            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST)) {
                addFeatureForest(gen);
            }

            if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN)) {
                addFeatureMountain(gen);
            }
        }

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER)) {
            addFeatureNether(gen);
        }
    }

    private static void addFeatureWorld(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, FeatureConfigs.WHITE_MARBLE);
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, FeatureConfigs.BLACK_MARBLE);
    }

    private static void addFeaturePlains(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureConfigs.MULBERRY_PLAINS);
    }

    private static void addFeatureForest(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureConfigs.PEACH);
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureConfigs.WALNUT);
    }

    private static void addFeatureMountain(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.VEGETAL_DECORATION, FeatureConfigs.PLUM);
    }

    private static void addFeatureNether(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, FeatureConfigs.RED_MARBLE);
    }
}
