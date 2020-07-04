package cx.rain.mc.forgemod.culturecraft.client;

import cx.rain.mc.forgemod.culturecraft.client.renderer.entity.RendererBuffalo;
import cx.rain.mc.forgemod.culturecraft.entity.Entities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class RendererRegister {
    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(Entities.ENTITY_BUFFALO, RendererBuffalo::new);
    }
}