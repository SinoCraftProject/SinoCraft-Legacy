package cx.rain.mc.forgemod.sinocraft.world.tree;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.foliageplacer.PineFoliagePlacer;
import net.minecraft.world.gen.trunkplacer.StraightTrunkPlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class TreePlum extends Tree {
    public static final BaseTreeFeatureConfig CONFIG = new BaseTreeFeatureConfig.Builder(
            new SimpleBlockStateProvider(ModBlocks.PLUM_LOG.get().getDefaultState().with(RotatedPillarBlock.AXIS,
                    Direction.Axis.Y)),
            new SimpleBlockStateProvider(ModBlocks.PLUM_LEAVES.get().getDefaultState()),
            new PineFoliagePlacer(FeatureSpread.create(1), FeatureSpread.create(1), FeatureSpread.create(3, 1)),
            new StraightTrunkPlacer(5, 2, 0),
            new TwoLayerFeature(1, 0, 1))
            .setIgnoreVines()
            .build();

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.TREE.withConfiguration(CONFIG);
    }
}
