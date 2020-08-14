package cx.rain.mc.forgemod.sinocraft.world.gen;

import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class Features {
    public static final TreeFeatureConfig TREE_PEACH_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_PEACH.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_PEACH.get().getDefaultState()),
                    new BlobFoliagePlacer(2, 0))
                    .baseHeight(4)
                    .heightRandA(2)
                    .foliageHeight(3)
                    .ignoreVines()
                    .setSapling((IPlantable) Blocks.SAPLING_PEACH.get())
                    .build();

    public static final TreeFeatureConfig TREE_WALNUT_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_WALNUT.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_WALNUT.get().getDefaultState()),
                    new BlobFoliagePlacer(2, 0))
                    .baseHeight(4)
                    .heightRandA(2)
                    .foliageHeight(3)
                    .ignoreVines()
                    .setSapling((IPlantable) Blocks.SAPLING_WALNUT.get())
                    .build();

    // Todo: Need optimize.
    // Todo: Change Log or Leaves to place.
    public static final TreeFeatureConfig TREE_PLUM_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_PLUM.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_PLUM.get().getDefaultState()),
                    new SpruceFoliagePlacer(2, 1)).
                    baseHeight(6).
                    heightRandA(3).
                    trunkHeight(1).
                    trunkHeightRandom(1).
                    trunkTopOffsetRandom(2).
                    ignoreVines().
                    setSapling((IPlantable) Blocks.SAPLING_PLUM.get())
                    .build();

    public static final TreeFeatureConfig TREE_MULBERRY_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_MULBERRY.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_MULBERRY.get().getDefaultState()),
                    new BlobFoliagePlacer(0, 0))
                    .setSapling((IPlantable) Blocks.SAPLING_MULBERRY.get())
                    .build();

    public static final OreFeatureConfig WHITE_MARBLE_CONFIG = new OreFeatureConfig(
            OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.WHITE_MARBLE.get().getDefaultState(), 17);

    public static final OreFeatureConfig BLACK_MARBLE_CONFIG = new OreFeatureConfig(
            OreFeatureConfig.FillerBlockType.NATURAL_STONE, Blocks.BLACK_MARBLE.get().getDefaultState(), 17);

    public static final OreFeatureConfig RED_MARBLE_CONFIG = new OreFeatureConfig(
            OreFeatureConfig.FillerBlockType.NETHERRACK, Blocks.RED_MARBLE.get().getDefaultState(), 17);
}
