package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityStoneMillRender;
import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.TileEntityVatRender;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntities;
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
        ClientRegistry.bindTileEntityRenderer(TileEntities.VAT.get(), TileEntityVatRender::new);
        ClientRegistry.bindTileEntityRenderer(TileEntities.STONE_MILL.get(), TileEntityStoneMillRender::new);
        SinoCraft.getInstance().getLog().info("Registering TER");
    }
}
