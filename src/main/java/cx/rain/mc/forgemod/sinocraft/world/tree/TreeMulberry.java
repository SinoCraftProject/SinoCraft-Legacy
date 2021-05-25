package cx.rain.mc.forgemod.sinocraft.world.tree;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.FancyFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.FancyTrunkPlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class TreeMulberry extends Tree {
    public static final BaseTreeFeatureConfig CONFIG = new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.MULBERRY_LOG.get().getDefaultState().with(RotatedPillarBlock.AXIS,
                    Direction.Axis.Y)),
            new SimpleBlockStateProvider(ModBlocks.MULBERRY_LEAVES.get().getDefaultState()),
            new FancyFoliagePlacer(FeatureSpread.create(2), FeatureSpread.create(3), 4),
            new FancyTrunkPlacer(7, 3, 0),
            new TwoLayerFeature(1, 0, 1))
            .setIgnoreVines()
            .setHeightmap(Heightmap.Type.MOTION_BLOCKING)
            .build();

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.TREE.withConfiguration(CONFIG);
    }
}
