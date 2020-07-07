package cx.rain.mc.forgemod.culturecraft.world.gen;

import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliageplacer.BlobFoliagePlacer;
import net.minecraftforge.common.IPlantable;

public class Features {
    public static final TreeFeatureConfig TREE_PEACH_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(RegistryBlock.BLOCKS.get("log_peach").getDefaultState()),
                    new SimpleBlockStateProvider(RegistryBlock.BLOCKS.get("leaves_peach").getDefaultState()),
                    new BlobFoliagePlacer(2, 0))
                    .baseHeight(4)
                    .heightRandA(2)
                    .foliageHeight(3)
                    .ignoreVines()
                    .setSapling((IPlantable) RegistryBlock.BLOCKS.get("sapling_peach"))
                    .build();
    public static final TreeFeatureConfig TREE_WALNUT_CONFIG =
            new TreeFeatureConfig.Builder(
                    new SimpleBlockStateProvider(RegistryBlock.BLOCKS.get("log_walnut").getDefaultState()),
                    new SimpleBlockStateProvider(RegistryBlock.BLOCKS.get("leaves_walnut").getDefaultState()),
                    new BlobFoliagePlacer(2, 0))
                    .baseHeight(4)
                    .heightRandA(2)
                    .foliageHeight(3)
                    .ignoreVines()
                    .setSapling((IPlantable) RegistryBlock.BLOCKS.get("sapling_walnut"))
                    .build();


}
