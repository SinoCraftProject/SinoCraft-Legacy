package cx.rain.mc.forgemod.sinocraft.world.structure.tree;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.world.gen.Features;
import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.blockstateprovider.AxisRotatingBlockStateProvider;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;

import javax.annotation.Nullable;
import java.util.Random;

public class TreePeach extends Tree {

//    public static final BaseTreeFeatureConfig PEACH_TREE_CONFIG = new BaseTreeFeatureConfig.Builder(
//            new AxisRotatingBlockStateProvider(ModBlocks.LOG_PEACH.get()),
//            new SimpleBlockStateProvider(ModBlocks.LEAVES_PEACH.get().getDefaultState()),
//            new BlobFoliagePlacer(2, 0, 0)
//    )

    @Nullable
    @Override
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean p_225546_2_) {
        // Fixme: Broken!
        //return Feature.TREE.withConfiguration(Features.TREE_PEACH_CONFIG);
        return null;
    }
}