package cx.rain.mc.forgemod.culturecraft.world.gen;

import com.google.common.collect.ImmutableList;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.HugeTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraft.world.gen.foliageplacer.SpruceFoliagePlacer;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraftforge.common.IPlantable;

import static cx.rain.mc.forgemod.culturecraft.block.Blocks.*;
import static net.minecraft.world.gen.surfacebuilders.SurfaceBuilder.PODZOL;

public class Features {
    public static final TreeFeatureConfig TREE_PEACH_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_PEACH.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_PEACH.get().getDefaultState()),
                    new BlobFoliagePlacer(2, 0))
                    .baseHeight(4)
                    .heightRandA(2)
                    .foliageHeight(2)
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

    public static final TreeFeatureConfig TREE_MULBERRY_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_MULBERRY.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_MULBERRY.get().getDefaultState()),
                    new BlobFoliagePlacer(0, 0))
                    .setSapling((IPlantable) Blocks.SAPLING_MULBERRY.get())
                    .build();

    public static final TreeFeatureConfig TREE_PLUM_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(Blocks.LOG_PLUM.get().getDefaultState()),
                    new SimpleBlockStateProvider(Blocks.LEAVES_PLUM.get().getDefaultState()),
                    new SpruceFoliagePlacer(2, 1))
                    .baseHeight(4)
                    .heightRandA(1)
                    .trunkHeight(1)
                    .trunkHeightRandom(1)
                    .trunkTopOffsetRandom(2)
                    .ignoreVines()
                    .setSapling((IPlantable) Blocks.SAPLING_PLUM.get().getDefaultState())
                    .build();

}
