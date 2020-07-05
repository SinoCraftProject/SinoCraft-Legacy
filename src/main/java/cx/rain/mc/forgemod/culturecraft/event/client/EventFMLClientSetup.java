package cx.rain.mc.forgemod.culturecraft.event.client;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = CultureCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventFMLClientSetup {
    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        ModelLoader.addSpecialModel(new ModelResourceLocation(
                RegistryItem.ITEMS.get("seven_stars_precious_blade").getRegistryName() + "_handheld",
                "inventory"));
    }
}
