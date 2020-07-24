package cx.rain.mc.forgemod.sinocraft.data.gen;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.*;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.base.ProviderAdvancement;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.language.ProviderLanguageENUS;
import cx.rain.mc.forgemod.sinocraft.data.gen.provider.language.ProviderLanguageZHCN;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.client.model.generators.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = SinoCraft.MODID, bus = Bus.MOD)
public class DataGen {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        ExistingFileHelper helper = event.getExistingFileHelper();

        if (event.includeClient()) {
            generator.addProvider(new ProviderBlockState(generator, helper));
            generator.addProvider(new ProviderItemModel(generator, helper));
        }

        if (event.includeServer()) {
            generator.addProvider(new ProviderBlockTag(generator));
            generator.addProvider(new ProviderItemTag(generator));
            generator.addProvider(new ProviderBlockLootTable(generator));

            generator.addProvider(new ProviderLanguageZHCN(generator));
            generator.addProvider(new ProviderLanguageENUS(generator));
            generator.addProvider(new ProviderAdvancement(generator));
        }
    }
}
