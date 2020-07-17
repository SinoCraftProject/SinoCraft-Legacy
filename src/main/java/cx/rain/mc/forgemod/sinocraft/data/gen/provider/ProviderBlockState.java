package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockLeavesGrowable;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockPlant;
import net.minecraft.block.LogBlock;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.client.model.generators.ExistingFileHelper;

public class ProviderBlockState extends BlockStateProvider {
    public ProviderBlockState(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SinoCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        axisBlock((LogBlock) Blocks.LOG_PEACH.get(), modLoc("block/log_peach_side"), modLoc("block/log_peach_top"));
        axisBlock((LogBlock) Blocks.LOG_PEACH_STRIPPED.get(), modLoc("block/log_peach_stripped"), modLoc("block/log_peach_top"));
        simpleBlock(Blocks.LOG_PEACH_SKIN.get(), models().cubeAll("log_peach_skin", modLoc("block/log_peach_side")));
        simpleBlock(Blocks.LOG_PEACH_STRIPPED_SKIN.get(), models().cubeAll("log_peach_stripped_skin", modLoc("block/log_peach_stripped")));
        simpleBlock(Blocks.PLANK_PEACH.get());
        getVariantBuilder(Blocks.LEAVES_PEACH.get())
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), false).modelForState().modelFile(models().cubeAll("leaves_peach", modLoc("block/leaves_peach"))).addModel()
                .partialState().with(BlockLeavesGrowable.getMatureProperty(), true).modelForState().modelFile(models().cubeAll("leaves_peach_mature", modLoc("block/leaves_peach_mature"))).addModel();
        simpleBlock(Blocks.SAPLING_PEACH.get(), models().cross("sapling_peach", modLoc("block/sapling_peach")));

        axisBlock((LogBlock) Blocks.LOG_WALNUT.get(), modLoc("block/log_walnut_side"), modLoc("block/log_walnut_top"));
        //axisBlock((LogBlock) Blocks.LOG_WALNUT_STRIPPED.get(), modLoc("block/log_walnut_stripped"), modLoc("block/log_walnut_top"));
        simpleBlock(Blocks.LOG_WALNUT_SKIN.get(), models().cubeAll("log_walnut_skin", modLoc("block/log_walnut_side")));
        //simpleBlock(Blocks.LOG_WALNUT_STRIPPED_SKIN.get(), models().cubeAll("log_walnut_stripped_skin", modLoc("block/log_walnut_stripped")));
        simpleBlock(Blocks.PLANK_WALNUT.get());
        simpleBlock(Blocks.LEAVES_WALNUT.get());
        simpleBlock(Blocks.SAPLING_WALNUT.get(), models().cross("sapling_walnut", modLoc("block/sapling_walnut")));

        axisBlock((LogBlock) Blocks.LOG_MULBERRY.get(), modLoc("block/log_mulberry_side"), modLoc("block/log_mulberry_top"));
        axisBlock((LogBlock) Blocks.LOG_MULBERRY_STRIPPED.get(), modLoc("block/log_mulberry_stripped"), modLoc("block/log_mulberry_top"));
        simpleBlock(Blocks.LOG_MULBERRY_SKIN.get(), models().cubeAll("log_mulberry_skin", modLoc("block/log_mulberry_side")));
        simpleBlock(Blocks.LOG_MULBERRY_STRIPPED_SKIN.get(), models().cubeAll("log_mulberry_stripped_skin", modLoc("block/log_mulberry_stripped")));
        //simpleBlock(Blocks.PLANK_MULBERRY.get());
        simpleBlock(Blocks.LEAVES_MULBERRY.get());
        simpleBlock(Blocks.SAPLING_MULBERRY.get(), models().cross("sapling_mulberry", modLoc("block/sapling_mulberry")));


        //axisBlock((LogBlock) Blocks.LOG_PLUM.get(), modLoc("block/log_plum_side"), modLoc("block/log_plum_top"));
        //axisBlock((LogBlock) Blocks.LOG_PLUM_STRIPPED.get(), modLoc("block/log_plum_stripped"), modLoc("block/log_plum_top"));
        //simpleBlock(Blocks.LOG_PLUM_SKIN.get(), models().cubeAll("log_plum_skin", modLoc("block/log_plum_side")));
        //simpleBlock(Blocks.LOG_PLUM_STRIPPED_SKIN.get(), models().cubeAll("log_plum_stripped_skin", modLoc("block/log_plum_stripped")));
        //simpleBlock(Blocks.PLANK_PLUM.get());
        //simpleBlock(Blocks.LEAVES_PLUM.get());
        //simpleBlock(Blocks.SAPLING_PLUM.get(), models().cross("sapling_plum", modLoc("block/sapling_plum")));

        simpleBlock(Blocks.WHITE_MARBLE.get());
        //simpleBlock(Blocks.RED_MARBLE.get());
        //simpleBlock(Blocks.BLACK_MARBLE.get());

        getVariantBuilder(Blocks.WHITE_RADISH_PLANT.get())
                .partialState().with(BlockPlant.AGE, 0).modelForState().modelFile(models().crop("white_radish_plant_stage_0", modLoc("block/white_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 1).modelForState().modelFile(models().crop("white_radish_plant_stage_1", modLoc("block/white_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 2).modelForState().modelFile(models().crop("white_radish_plant_stage_2", modLoc("block/white_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 3).modelForState().modelFile(models().crop("white_radish_plant_stage_3", modLoc("block/white_radish_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 4).modelForState().modelFile(models().crop("white_radish_plant_stage_4", modLoc("block/white_radish_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 5).modelForState().modelFile(models().crop("white_radish_plant_stage_5", modLoc("block/white_radish_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 6).modelForState().modelFile(models().crop("white_radish_plant_stage_6", modLoc("block/white_radish_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 7).modelForState().modelFile(models().crop("white_radish_plant_stage_7", modLoc("block/white_radish_plant_stage_3"))).addModel();
        getVariantBuilder(Blocks.SUMMER_RADISH_PLANT.get())
                .partialState().with(BlockPlant.AGE, 0).modelForState().modelFile(models().crop("summer_radish_plant_stage_0", modLoc("block/summer_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 1).modelForState().modelFile(models().crop("summer_radish_plant_stage_1", modLoc("block/summer_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 2).modelForState().modelFile(models().crop("summer_radish_plant_stage_2", modLoc("block/summer_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 3).modelForState().modelFile(models().crop("summer_radish_plant_stage_3", modLoc("block/summer_radish_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 4).modelForState().modelFile(models().crop("summer_radish_plant_stage_4", modLoc("block/summer_radish_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 5).modelForState().modelFile(models().crop("summer_radish_plant_stage_5", modLoc("block/summer_radish_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 6).modelForState().modelFile(models().crop("summer_radish_plant_stage_6", modLoc("block/summer_radish_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 7).modelForState().modelFile(models().crop("summer_radish_plant_stage_7", modLoc("block/summer_radish_plant_stage_3"))).addModel();
        getVariantBuilder(Blocks.GREEN_RADISH_PLANT.get())
                .partialState().with(BlockPlant.AGE, 0).modelForState().modelFile(models().crop("green_radish_plant_stage_0", modLoc("block/green_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 1).modelForState().modelFile(models().crop("green_radish_plant_stage_1", modLoc("block/green_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 2).modelForState().modelFile(models().crop("green_radish_plant_stage_2", modLoc("block/green_radish_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 3).modelForState().modelFile(models().crop("green_radish_plant_stage_3", modLoc("block/green_radish_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 4).modelForState().modelFile(models().crop("green_radish_plant_stage_4", modLoc("block/green_radish_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 5).modelForState().modelFile(models().crop("green_radish_plant_stage_5", modLoc("block/green_radish_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 6).modelForState().modelFile(models().crop("green_radish_plant_stage_6", modLoc("block/green_radish_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 7).modelForState().modelFile(models().crop("green_radish_plant_stage_7", modLoc("block/green_radish_plant_stage_3"))).addModel();
        getVariantBuilder(Blocks.GREEN_PEPPER_PLANT.get())
                .partialState().with(BlockPlant.AGE, 0).modelForState().modelFile(models().crop("green_pepper_plant_stage_0", modLoc("block/green_pepper_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 1).modelForState().modelFile(models().crop("green_pepper_plant_stage_1", modLoc("block/green_pepper_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 2).modelForState().modelFile(models().crop("green_pepper_plant_stage_2", modLoc("block/green_pepper_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 3).modelForState().modelFile(models().crop("green_pepper_plant_stage_3", modLoc("block/green_pepper_plant_stage_3"))).addModel()
                .partialState().with(BlockPlant.AGE, 4).modelForState().modelFile(models().crop("green_pepper_plant_stage_4", modLoc("block/green_pepper_plant_stage_4"))).addModel()
                .partialState().with(BlockPlant.AGE, 5).modelForState().modelFile(models().crop("green_pepper_plant_stage_5", modLoc("block/green_pepper_plant_stage_5"))).addModel()
                .partialState().with(BlockPlant.AGE, 6).modelForState().modelFile(models().crop("green_pepper_plant_stage_6", modLoc("block/green_pepper_plant_stage_6"))).addModel()
                .partialState().with(BlockPlant.AGE, 7).modelForState().modelFile(models().crop("green_pepper_plant_stage_7", modLoc("block/green_pepper_plant_stage_7"))).addModel();
        getVariantBuilder(Blocks.CHILI_PEPPER_PLANT.get())
                .partialState().with(BlockPlant.AGE, 0).modelForState().modelFile(models().crop("chili_pepper_plant_stage_0", modLoc("block/chili_pepper_plant_stage_0"))).addModel()
                .partialState().with(BlockPlant.AGE, 1).modelForState().modelFile(models().crop("chili_pepper_plant_stage_1", modLoc("block/chili_pepper_plant_stage_1"))).addModel()
                .partialState().with(BlockPlant.AGE, 2).modelForState().modelFile(models().crop("chili_pepper_plant_stage_2", modLoc("block/chili_pepper_plant_stage_2"))).addModel()
                .partialState().with(BlockPlant.AGE, 3).modelForState().modelFile(models().crop("chili_pepper_plant_stage_3", modLoc("block/chili_pepper_plant_stage_3"))).addModel()
                .partialState().with(BlockPlant.AGE, 4).modelForState().modelFile(models().crop("chili_pepper_plant_stage_4", modLoc("block/chili_pepper_plant_stage_4"))).addModel()
                .partialState().with(BlockPlant.AGE, 5).modelForState().modelFile(models().crop("chili_pepper_plant_stage_5", modLoc("block/chili_pepper_plant_stage_5"))).addModel()
                .partialState().with(BlockPlant.AGE, 6).modelForState().modelFile(models().crop("chili_pepper_plant_stage_6", modLoc("block/chili_pepper_plant_stage_6"))).addModel()
                .partialState().with(BlockPlant.AGE, 7).modelForState().modelFile(models().crop("chili_pepper_plant_stage_7", modLoc("block/chili_pepper_plant_stage_7"))).addModel();

    }
}
