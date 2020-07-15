package cx.rain.mc.forgemod.culturecraft.client;

import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class Render {
    public static void ChangeRender() {
        RenderTypeLookup.setRenderLayer(Blocks.WHITE_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SUMMER_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.GREEN_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.CHILI_PEPPER_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.GREEN_PEPPER_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.EGGPLANT_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.CABBAGE_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_PEACH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_WALNUT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_MULBERRY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_PLUM.get(), RenderType.getCutout());
    }
}
