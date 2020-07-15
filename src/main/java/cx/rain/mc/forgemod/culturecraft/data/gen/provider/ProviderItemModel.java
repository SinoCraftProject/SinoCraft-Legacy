package cx.rain.mc.forgemod.culturecraft.data.gen.provider;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.client.model.generators.ModelFile;

public class ProviderItemModel extends ItemModelProvider {
    public static final ModelFile.UncheckedModelFile GENERATED =
            new ModelFile.UncheckedModelFile("item/generated");

    public ProviderItemModel(DataGenerator gen, ExistingFileHelper exFileHelper) {
        super(gen, CultureCraft.MODID, exFileHelper);
    }

    @Override
    protected void registerModels() {
        getBuilder("log_peach").parent(getModel("block/log_peach"));
        getBuilder("log_peach_skin").parent(getModel("block/log_peach_skin"));
        getBuilder("log_peach_stripped").parent(getModel("block/log_peach_stripped"));
        getBuilder("log_peach_stripped_skin").parent(getModel("block/log_peach_stripped_skin"));
        getBuilder("leaves_peach").parent(getModel("block/leaves_peach"));
        getBuilder("plank_peach").parent(getModel("block/plank_peach"));
        getBuilder("sapling_peach").parent(GENERATED).texture("layer0", modLoc("block/sapling_peach"));
        
        getBuilder("log_walnut").parent(getModel("block/log_walnut"));
        getBuilder("log_walnut_skin").parent(getModel("block/log_walnut_skin"));
        //getBuilder("log_walnut_stripped").parent(getModel("block/log_walnut_stripped"));
        //getBuilder("log_walnut_stripped_skin").parent(getModel("block/log_walnut_stripped_skin"));
        getBuilder("leaves_walnut").parent(getModel("block/leaves_walnut"));
        getBuilder("plank_walnut").parent(getModel("block/plank_walnut"));
        getBuilder("sapling_walnut").parent(GENERATED).texture("layer0", modLoc("block/sapling_walnut"));

        getBuilder("log_plum").parent(getModel("block/log_plum"));
        getBuilder("log_plum_skin").parent(getModel("block/log_plum_skin"));
        getBuilder("log_plum_stripped").parent(getModel("block/log_plum_stripped"));
        getBuilder("log_plum_stripped_skin").parent(getModel("block/log_plum_stripped_skin"));
        getBuilder("leaves_plum").parent(getModel("block/leaves_plum"));
        getBuilder("plank_plum").parent(getModel("block/plank_plum"));
        //getBuilder("sapling_plum").parent(GENERATED).texture("layer0", modLoc("block/sapling_plum"));

        getBuilder("log_mulberry").parent(getModel("block/log_mulberry"));
        getBuilder("log_mulberry_skin").parent(getModel("block/log_mulberry_skin"));
        getBuilder("log_mulberry_stripped").parent(getModel("block/log_mulberry_stripped"));
        getBuilder("log_mulberry_stripped_skin").parent(getModel("block/log_mulberry_stripped_skin"));
        getBuilder("leaves_mulberry").parent(getModel("block/leaves_mulberry"));
        getBuilder("plank_mulberry").parent(getModel("block/plank_mulberry"));
        getBuilder("sapling_mulberry").parent(GENERATED).texture("layer0", modLoc("block/sapling_mulberry"));

        getBuilder("white_marble").parent(getModel("block/white_marble"));
        getBuilder("red_marble").parent(getModel("block/red_marble"));
        getBuilder("black_marble").parent(getModel("block/black_marble"));

        //getBuilder("peach").parent(GENERATED).texture("layer0", modLoc("item/peach"));
        getBuilder("chili_pepper_seed").parent(GENERATED).texture("layer0", modLoc("item/chili_pepper_seed"));
        getBuilder("green_pepper_seed").parent(GENERATED).texture("layer0", modLoc("item/green_pepper_seed"));
        getBuilder("eggplant_seed").parent(GENERATED).texture("layer0", modLoc("item/eggplant_seed"));
        //getBuilder("cabbage_seed").parent(GENERATED).texture("layer0", modLoc("item/cabbage_seed"));
        getBuilder("rice_seed").parent(GENERATED).texture("layer0", modLoc("item/rice_seed"));
        getBuilder("millet_seed").parent(GENERATED).texture("layer0", modLoc("item/millet_seed"));
        getBuilder("sorghum_seed").parent(GENERATED).texture("layer0", modLoc("item/sorghum_seed"));
        getBuilder("soybean").parent(GENERATED).texture("layer0", modLoc("item/soybean"));
        getBuilder("rice").parent(GENERATED).texture("layer0", modLoc("item/rice"));
        getBuilder("millet").parent(GENERATED).texture("layer0", modLoc("item/millet"));
        //getBuilder("sorghum").parent(GENERATED).texture("layer0", modLoc("item/sorghum"));

        getBuilder("chili_pepper").parent(GENERATED).texture("layer0", modLoc("item/chili_pepper"));
        getBuilder("green_pepper").parent(GENERATED).texture("layer0", modLoc("item/green_pepper"));
        getBuilder("eggplant").parent(GENERATED).texture("layer0", modLoc("item/eggplant"));
        getBuilder("cabbage").parent(GENERATED).texture("layer0", modLoc("item/cabbage"));

    }

    @Override
    public String getName() {
        return "Item Models";
    }

    private ModelFile getModel(String loc) {
        return new ModelFile.UncheckedModelFile(new ResourceLocation(modid, loc));
    }
}
