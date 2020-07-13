package cx.rain.mc.forgemod.culturecraft.world.gen;

import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
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
                    .foliageHeightRandom(2)
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
                    .foliageHeightRandom(2)
                    .ignoreVines()
                    .setSapling((IPlantable) Blocks.SAPLING_WALNUT.get())
                    .build();


}
