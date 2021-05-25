package cx.rain.mc.forgemod.sinocraft.client;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockPlant;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.fluid.Fluid;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fml.RegistryObject;

@OnlyIn(Dist.CLIENT)
public class ChangeRenderType {
    public ChangeRenderType(){
        for (RegistryObject<Block> entry : ModBlocks.REGISTRY.getEntries()) {
            Block block = entry.get();
            // all crops and sapling blocks
            if (block instanceof CropsBlock || block instanceof SaplingBlock) {
                RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
            }
        }
        for (RegistryObject<Fluid> entry : ModFluids.REGISTRY.getEntries()) {
            RenderTypeLookup.setRenderLayer(entry.get(), RenderType.getTranslucent());
        }

        RenderTypeLookup.setRenderLayer(ModBlocks.STONE_MILL.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.PAPER_DRYING_RACK.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(ModBlocks.STOVE.get(), RenderType.getCutout());
    }
}
