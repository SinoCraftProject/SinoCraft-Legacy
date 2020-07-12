package cx.rain.mc.forgemod.culturecraft.data.gen;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.data.gen.provider.ProviderBlockState;
import cx.rain.mc.forgemod.culturecraft.data.gen.provider.ProviderBlockTags;
import cx.rain.mc.forgemod.culturecraft.data.gen.provider.ProviderItemModel;
import cx.rain.mc.forgemod.culturecraft.data.gen.provider.ProviderItemTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = CultureCraft.MODID, bus = Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        // Todo: Data Generator.
        if (event.includeClient()) {
            generator.addProvider(new ProviderBlockState(generator, helper));
            generator.addProvider(new ProviderItemModel(generator, helper));
        }

        if (event.includeServer()) {
            generator.addProvider(new ProviderBlockTags(generator));
            generator.addProvider(new ProviderItemTags(generator));
        }
    }
}
