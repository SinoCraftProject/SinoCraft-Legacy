package cx.rain.mc.forgemod.sinocraft.structure.tree;

import cx.rain.mc.forgemod.sinocraft.world.gen.Features;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;

import javax.annotation.Nullable;
import java.util.Random;

public class TreeMulberry extends Tree {
    @Nullable
    @Override
    protected ConfiguredFeature<TreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        return Feature.FANCY_TREE.withConfiguration(Features.TREE_MULBERRY_CONFIG);
    }
}
