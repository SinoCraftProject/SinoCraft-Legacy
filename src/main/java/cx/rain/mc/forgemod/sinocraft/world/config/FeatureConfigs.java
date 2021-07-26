package cx.rain.mc.forgemod.sinocraft.world.config;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreeMulberry;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreePeach;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreePlum;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreeWalnut;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.DecoratedPlacementConfig;
import net.minecraft.world.gen.placement.NoiseDependant;
import net.minecraft.world.gen.placement.Placement;

public class FeatureConfigs {
    public static final OreFeatureConfig WHITE_MARBLE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.WHITE_MARBLE.get().getDefaultState(), 32);
    public static final OreFeatureConfig BLACK_MARBLE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.BLACK_MARBLE.get().getDefaultState(), 32);
    public static final OreFeatureConfig RED_MARBLE_CONFIG = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.RED_MARBLE.get().getDefaultState(), 32);

    public static final ConfiguredFeature<?, ?> WHITE_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), WHITE_MARBLE_CONFIG).range(80).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))).square().count(5);
    public static final ConfiguredFeature<?, ?> BLACK_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), BLACK_MARBLE_CONFIG).range(80).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))).square().count(5);
    public static final ConfiguredFeature<?, ?> RED_MARBLE = new ConfiguredFeature<>(new OreFeature(OreFeatureConfig.CODEC), RED_MARBLE_CONFIG).range(80).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))).square().count(5);

    public static final ConfiguredFeature<?, ?> PEACH = Feature.TREE.withConfiguration(TreePeach.CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))).square().count(5);
    public static final ConfiguredFeature<?, ?> WALNUT = Feature.TREE.withConfiguration(TreeWalnut.CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(50))).square().count(5);
    public static final ConfiguredFeature<?, ?> PLUM = Feature.TREE.withConfiguration(TreePlum.CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))).square().count(2);
    public static final ConfiguredFeature<?, ?> MULBERRY_PLAINS = Feature.TREE.withConfiguration(TreeMulberry.CONFIG).withPlacement(Placement.CHANCE.configure(new ChanceConfig(30))).square().count(1);
}
