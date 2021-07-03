package cx.rain.mc.forgemod.sinocraft.data.provider;

import cx.rain.mc.forgemod.sinocraft.data.provider.src_provider.APIBlocksProvider;
import cx.rain.mc.forgemod.sinocraft.data.provider.src_provider.APIItemsProvider;
import cx.rain.mc.forgemod.sinocraft.data.provider.src_provider.SRCNetworkProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DirectoryCache;
import net.minecraft.data.IDataProvider;

import java.io.IOException;

public class ProviderAPI implements IDataProvider {

    private final DataGenerator generator;

    public ProviderAPI(DataGenerator generator) {
        this.generator = generator;
    }

    @Override
    public void act(DirectoryCache cache) throws IOException {
        new APIItemsProvider().write(generator);
        new APIBlocksProvider().write(generator);
        new SRCNetworkProvider().write(generator);
    }

    @Override
    public String getName() {
        return "SinoCraft Source Generator";
    }
}
