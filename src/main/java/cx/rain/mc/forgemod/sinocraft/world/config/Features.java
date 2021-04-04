package cx.rain.mc.forgemod.sinocraft.world.config;

import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Features {
    public static final ConfiguredFeature<?, ?> WHITE_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), FeatureConfigs.WHITE_MARBLE).range(80).square().count(10);
    public static final ConfiguredFeature<?, ?> BLACK_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), FeatureConfigs.BLACK_MARBLE).range(80).square().count(10);
    public static final ConfiguredFeature<?, ?> RED_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), FeatureConfigs.RED_MARBLE).range(80).square().count(10);
}
