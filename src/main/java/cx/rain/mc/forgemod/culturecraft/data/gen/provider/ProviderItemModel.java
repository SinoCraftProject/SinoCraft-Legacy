package cx.rain.mc.forgemod.culturecraft.data.gen.provider;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.client.model.generators.ItemModelProvider;

public class ProviderItemModel extends ItemModelProvider {
    public ProviderItemModel(DataGenerator generator, ExistingFileHelper existingFileHelper) {
        super(generator, CultureCraft.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {

    }

    @Override
    public String getName() {
        return "Item Models";
    }
}
