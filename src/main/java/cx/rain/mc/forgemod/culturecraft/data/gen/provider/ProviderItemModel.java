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
        //getBuilder("peach").parent(GENERATED).texture("layer0", modLoc("block/peach"));
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
    }

    @Override
    public String getName() {
        return "Item Models";
    }

    private ModelFile getModel(String loc) {
        return new ModelFile.UncheckedModelFile(new ResourceLocation(modid, loc));
    }
}
