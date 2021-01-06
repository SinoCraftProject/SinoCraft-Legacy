package cx.rain.mc.forgemod.sinocraft.world;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.world.config.Features;
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

        System.out.println("0");
        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.OVERWORLD)) {
            System.out.println("1");
            addFeatureWorld(gen);
        }

        if (BiomeDictionary.hasType(biome, BiomeDictionary.Type.NETHER)) {
            addFeatureNether(gen);
        }
    }

    private static void addFeatureWorld(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.WHITE_MARBLE);
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.BLACK_MARBLE);
    }

    private static void addFeatureNether(BiomeGenerationSettingsBuilder generation) {
        generation.withFeature(GenerationStage.Decoration.UNDERGROUND_DECORATION, Features.RED_MARBLE);
    }
}
