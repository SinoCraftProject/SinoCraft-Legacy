package cx.rain.mc.forgemod.sinocraft.world.config;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class FeatureConfigs {
    public static final OreFeatureConfig WHITE_MARBLE = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.WHITE_MARBLE.get().getDefaultState(), 32);
    public static final OreFeatureConfig BLACK_MARBLE = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.BASE_STONE_OVERWORLD, ModBlocks.BLACK_MARBLE.get().getDefaultState(), 32);
    public static final OreFeatureConfig RED_MARBLE = new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NETHERRACK, ModBlocks.RED_MARBLE.get().getDefaultState(), 32);


}
