package cx.rain.mc.forgemod.sinocraft.side.client;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;

public class ChangeRenderType {
    public ChangeRenderType(){
        RenderTypeLookup.setRenderLayer(ModBlocks.WHITE_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SUMMER_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_RADISH_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.CHILI_PEPPER_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.GREEN_PEPPER_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.CABBAGE_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.EGGPLANT_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MILLET_PLANT.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.SOYBEAN_PLANT.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(ModBlocks.PEACH_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.WALNUT_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.MULBERRY_SAPLING.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PLUM_SAPLING.get(), RenderType.getCutout());

        RenderTypeLookup.setRenderLayer(ModBlocks.STONE_MILL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PAPER_DRYING_RACK.get(), RenderType.getCutout());
    }
}
