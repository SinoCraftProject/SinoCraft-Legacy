package cx.rain.mc.forgemod.sinocraft.hook.client;

import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class ChangeRenderType {
    public ChangeRenderType(){
        RenderTypeLookup.setRenderLayer(Blocks.WHITE_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SUMMER_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.GREEN_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.CHILI_PEPPER_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.GREEN_PEPPER_PLANT.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(Blocks.EGGPLANT_PLANT.get(), RenderType.getCutout());
        //RenderTypeLookup.setRenderLayer(Blocks.CABBAGE_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_PEACH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_WALNUT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_MULBERRY.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.SAPLING_PLUM.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.STONE_MILL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(Blocks.PAPER_DRYING_RACK.get(), RenderType.getCutout());
    }
}
