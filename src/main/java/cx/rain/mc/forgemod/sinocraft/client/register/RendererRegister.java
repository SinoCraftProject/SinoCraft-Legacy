package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.client.renderer.entity.RendererBuffalo;
import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import cx.rain.mc.forgemod.sinocraft.item.ItemTeapot;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.ItemModelsProperties;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = SinoCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RendererRegister {

    public static final ResourceLocation TEAPOT_TEA = new ResourceLocation("sinocraft:tea");

    @SubscribeEvent
    public static void onClientSetUpEvent(FMLClientSetupEvent event) {
        RenderingRegistry.registerEntityRenderingHandler(ModEntities.ENTITY_BUFFALO.get(), RendererBuffalo::new);
        ItemTeapot teapot = ModItems.TEAPOT.get();
        ItemModelsProperties.registerProperty(teapot, TEAPOT_TEA, (stack, world, entity) -> teapot.getTea(stack) > 0 ? 1.0f : 0.0f);
    }
}