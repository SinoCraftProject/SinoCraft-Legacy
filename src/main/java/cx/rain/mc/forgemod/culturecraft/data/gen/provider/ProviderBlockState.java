package cx.rain.mc.forgemod.culturecraft.data.gen.provider;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.BlockLeavesGrowable;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ConfiguredModel;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class ProviderBlockState extends BlockStateProvider {
    public ProviderBlockState(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, CultureCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        axisBlock((LogBlock) Blocks.LOG_PEACH.get(), modLoc("block/log_peach_side"), modLoc("block/log_peach_top"));
        axisBlock((LogBlock) Blocks.LOG_WALNUT.get(), modLoc("block/log_walnut_side"), modLoc("block/log_walnut_top"));
        axisBlock((LogBlock) Blocks.LOG_PEACH_STRIPPED.get(), modLoc("block/log_peach_stripped"), modLoc("block/log_peach_top"));
        //axisBlock((LogBlock) Blocks.LOG_WALNUT_STRIPPED.get(), modLoc("block/log_walnut_stripped"), modLoc("block/log_walnut_top"));

        simpleBlock(Blocks.LOG_PEACH_SKIN.get(), models().cubeAll("log_peach_skin", modLoc("block/log_peach_side")));
        //simpleBlock(Blocks.LOG_PEACH_STRIPPED_SKIN.get(), models().cubeAll("log_peach_stripped_skin", modLoc("block/log_peach_stripped")));
        simpleBlock(Blocks.LOG_WALNUT_SKIN.get(), models().cubeAll("log_walnut_skin", modLoc("block/log_walnut_side")));
        //simpleBlock(Blocks.LOG_WALNUT_STRIPPED_SKIN.get(), models().cubeAll("log_walnut_stripped_skin", modLoc("block/log_walnut_stripped")));

        simpleBlock(Blocks.PLANK_PEACH.get());
        simpleBlock(Blocks.PLANK_WALNUT.get());
        getVariantBuilder(Blocks.LEAVES_PEACH.get())
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), false)
                        .modelForState().modelFile(models().cubeAll("leaves_peach", modLoc("block/leaves_peach"))).addModel()
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), true)
                        .modelForState().modelFile(models().cubeAll("leaves_peach_mature", modLoc("block/leaves_peach_mature"))).addModel();
        simpleBlock(Blocks.LEAVES_WALNUT.get());

        simpleBlock(RegistryBlock.BLOCKS.get("marble_white"));
    }
}
