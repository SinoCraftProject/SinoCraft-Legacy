package cx.rain.mc.forgemod.sinocraft.data;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.data.provider.*;
import cx.rain.mc.forgemod.sinocraft.data.provider.language.ProviderLanguageENUS;
import cx.rain.mc.forgemod.sinocraft.data.provider.language.ProviderLanguageZHCN;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
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
            BlockTagsProvider blockTagsProvider = new ProviderBlockTag(generator, helper);
            generator.addProvider(blockTagsProvider);
            generator.addProvider(new ProviderItemTag(generator, helper, blockTagsProvider));
            generator.addProvider(new ProviderBlockLootTable(generator));
            generator.addProvider(new ProviderLootTable(generator));
            generator.addProvider(new ProviderGlobalLootModifier(generator));

            generator.addProvider(new ProviderLanguageZHCN(generator));
            generator.addProvider(new ProviderLanguageENUS(generator));
            generator.addProvider(new ProviderAdvancement(generator));
            generator.addProvider(new ProviderRecipe(generator));
            generator.addProvider(new ProviderAPI(generator));
        }
    }
}
