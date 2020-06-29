package cx.rain.mc.forgemod.culturecraft.datagen;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@EventBusSubscriber(modid = CultureCraft.MODID, bus = Bus.MOD)
public class EventGatherData {
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();

        // Todo: Data Generator.
        if (event.includeClient()) {

        }

        if (event.includeServer()) {

        }
    }
}
