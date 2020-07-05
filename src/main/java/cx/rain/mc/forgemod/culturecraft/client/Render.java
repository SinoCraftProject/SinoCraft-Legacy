package cx.rain.mc.forgemod.culturecraft.client;

import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class Render {
    public static void ChangeRender() {
        RenderTypeLookup.setRenderLayer(Blocks.RADISH_WHITE_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.RADISH_SUMMER_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.RADISH_GREEN_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.PEPPER_CHILI_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.PEPPER_GREEN_PLANT.get(),RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.CELERY_CABBAGE_PLANT.get(),RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.EGGPLANT_PLANT.get(),RenderType.getCutout());
    }
}
