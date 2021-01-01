package cx.rain.mc.forgemod.sinocraft.data.gen.provider;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ProviderItemModel extends ItemModelProvider {
    public static final ModelFile.UncheckedModelFile GENERATED =
            new ModelFile.UncheckedModelFile("item/generated");

    public static final ModelFile.UncheckedModelFile TEMPLATE_SPAWN_EGG = new ModelFile.UncheckedModelFile("item/template_spawn_egg");

    public ProviderItemModel(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, SinoCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("log_peach").parent(getModel("block/log_peach"));
        getBuilder("log_peach_bark").parent(getModel("block/log_peach_bark"));
        getBuilder("log_peach_stripped").parent(getModel("block/log_peach_stripped"));
        getBuilder("log_peach_stripped_bark").parent(getModel("block/log_peach_stripped_bark"));
        getBuilder("leaves_peach").parent(getModel("block/leaves_peach"));
        getBuilder("plank_peach").parent(getModel("block/plank_peach"));
        getBuilder("sapling_peach").parent(GENERATED).texture("layer0", modLoc("block/sapling_peach"));
        
        getBuilder("log_walnut").parent(getModel("block/log_walnut"));
        getBuilder("log_walnut_bark").parent(getModel("block/log_walnut_bark"));
        getBuilder("log_walnut_stripped").parent(getModel("block/log_walnut_stripped"));
        getBuilder("log_walnut_stripped_bark").parent(getModel("block/log_walnut_stripped_bark"));
        getBuilder("leaves_walnut").parent(getModel("block/leaves_walnut"));
        getBuilder("plank_walnut").parent(getModel("block/plank_walnut"));
        getBuilder("sapling_walnut").parent(GENERATED).texture("layer0", modLoc("block/sapling_walnut"));

        getBuilder("log_plum").parent(getModel("block/log_plum"));
        getBuilder("log_plum_bark").parent(getModel("block/log_plum_bark"));
        getBuilder("log_plum_stripped").parent(getModel("block/log_plum_stripped"));
        getBuilder("log_plum_stripped_bark").parent(getModel("block/log_plum_stripped_bark"));
        getBuilder("leaves_plum").parent(getModel("block/leaves_plum"));
        getBuilder("plank_plum").parent(getModel("block/plank_plum"));
        getBuilder("sapling_plum").parent(GENERATED).texture("layer0", modLoc("block/sapling_plum"));

        getBuilder("log_mulberry").parent(getModel("block/log_mulberry"));
        getBuilder("log_mulberry_bark").parent(getModel("block/log_mulberry_bark"));
        getBuilder("log_mulberry_stripped").parent(getModel("block/log_mulberry_stripped"));
        getBuilder("log_mulberry_stripped_bark").parent(getModel("block/log_mulberry_stripped_bark"));
        getBuilder("leaves_mulberry").parent(getModel("block/leaves_mulberry"));
        getBuilder("plank_mulberry").parent(getModel("block/plank_mulberry"));
        getBuilder("sapling_mulberry").parent(GENERATED).texture("layer0", modLoc("block/sapling_mulberry"));

        getBuilder("white_marble").parent(getModel("block/white_marble"));
        getBuilder("red_marble").parent(getModel("block/red_marble"));
        getBuilder("black_marble").parent(getModel("block/black_marble"));

        getBuilder("peach").parent(GENERATED).texture("layer0", modLoc("item/peach"));
        getBuilder("chili_pepper_seed").parent(GENERATED).texture("layer0", modLoc("item/chili_pepper_seed"));
        getBuilder("green_pepper_seed").parent(GENERATED).texture("layer0", modLoc("item/green_pepper_seed"));
        getBuilder("eggplant_seed").parent(GENERATED).texture("layer0", modLoc("item/eggplant_seed"));
        getBuilder("cabbage_seed").parent(GENERATED).texture("layer0", modLoc("item/cabbage_seed"));
        getBuilder("rice_seed").parent(GENERATED).texture("layer0", modLoc("item/rice_seed"));
        getBuilder("millet_seed").parent(GENERATED).texture("layer0", modLoc("item/millet_seed"));
        getBuilder("sorghum_seed").parent(GENERATED).texture("layer0", modLoc("item/sorghum_seed"));
        getBuilder("soybean").parent(GENERATED).texture("layer0", modLoc("item/soybean"));
        getBuilder("rice").parent(GENERATED).texture("layer0", modLoc("item/rice"));
        getBuilder("millet").parent(GENERATED).texture("layer0", modLoc("item/millet"));
        getBuilder("sorghum").parent(GENERATED).texture("layer0", modLoc("item/sorghum"));

        getBuilder("chili_pepper").parent(GENERATED).texture("layer0", modLoc("item/chili_pepper"));
        getBuilder("green_pepper").parent(GENERATED).texture("layer0", modLoc("item/green_pepper"));
        getBuilder("eggplant").parent(GENERATED).texture("layer0", modLoc("item/eggplant"));
        getBuilder("cabbage").parent(GENERATED).texture("layer0", modLoc("item/cabbage"));
        getBuilder("white_radish").parent(GENERATED).texture("layer0", modLoc("item/white_radish"));
        getBuilder("summer_radish").parent(GENERATED).texture("layer0", modLoc("item/summer_radish"));
        getBuilder("green_radish").parent(GENERATED).texture("layer0", modLoc("item/green_radish"));

        getBuilder("flour").parent(GENERATED).texture("layer0", modLoc("item/flour"));
        getBuilder("dough").parent(GENERATED).texture("layer0", modLoc("item/dough"));
        getBuilder("dumpling_wrapper").parent(GENERATED).texture("layer0", modLoc("item/dumpling_wrapper"));
        getBuilder("dumpling").parent(GENERATED).texture("layer0", modLoc("item/dumpling"));
        getBuilder("cooked_dumpling").parent(GENERATED).texture("layer0", modLoc("item/cooked_dumpling"));
        getBuilder("stuffing").parent(GENERATED).texture("layer0", modLoc("item/stuffing"));

        getBuilder("bark").parent(GENERATED).texture("layer0",modLoc("item/bark"));
        getBuilder("china_ink").parent(GENERATED).texture("layer0",modLoc("item/china_ink"));
        getBuilder("bucket_wood_pulp").parent(GENERATED).texture("layer0",modLoc("item/bucket_wood_pulp"));
        getBuilder("xuan_paper").parent(GENERATED).texture("layer0",modLoc("item/xuan_paper"));
        getBuilder("charcoal_black").parent(GENERATED).texture("layer0", modLoc("item/charcoal_black"));
        getBuilder("ink_stone").parent(GENERATED).texture("layer0", modLoc("item/ink_stone"));

        getBuilder("knife_iron").parent(GENERATED).texture("layer0",modLoc("item/knife_iron"));
        getBuilder("knife_gold").parent(GENERATED).texture("layer0",modLoc("item/knife_gold"));
        getBuilder("knife_diamond").parent(GENERATED).texture("layer0",modLoc("item/knife_diamond"));
        getBuilder("chinese_brush").parent(GENERATED).texture("layer0",modLoc("item/chinese_brush"));

        getBuilder("spawn_egg_buffalo").parent(TEMPLATE_SPAWN_EGG);
    }

    @Override
    public String getName() {
        return "Item Models";
    }

    private ModelFile getModel(String loc) {
        return new ModelFile.UncheckedModelFile(new ResourceLocation(modid, loc));
    }
}
