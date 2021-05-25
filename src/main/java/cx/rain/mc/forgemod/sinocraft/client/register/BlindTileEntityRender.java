package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityStoneMillRender;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityTeaTableRender;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityVatRender;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.ModTileEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.ClientRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(modid = SinoCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlindTileEntityRender {

    @SubscribeEvent
    public static void blind(FMLClientSetupEvent event) {
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.VAT.get(), TileEntityVatRender::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.STONE_MILL.get(), TileEntityStoneMillRender::new);
        ClientRegistry.bindTileEntityRenderer(ModTileEntities.TEA_TABLE.get(), TileEntityTeaTableRender::new);
        SinoCraft.getLogger().info("Registering TileEntity renderer.");
    }
}
