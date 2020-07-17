package cx.rain.mc.forgemod.sinocraft;

import cx.rain.mc.forgemod.sinocraft.block.BlockAdditions;
import cx.rain.mc.forgemod.sinocraft.block.BlockItems;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import cx.rain.mc.forgemod.sinocraft.client.Render;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@SuppressWarnings("deprecation")
@Mod(value = SinoCraft.MODID)
public class SinoCraft {
    public static final String MODID = "sinocraft";
    public static final String NAME = "SinoCraft";
    public static final String MC_VERSION = "1.15.2";
    public static final String MOD_VERSION = "1.0.0";
    public static final String VERSION = MC_VERSION + "-" + MOD_VERSION;

    private static SinoCraft INSTANCE = null;

    private final Logger Log = LogManager.getLogger(SinoCraft.NAME);

    public SinoCraft() {
        INSTANCE = this;

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        bus.addListener(this::setupServer);

        new Blocks(bus);
        new BlockItems(bus);
        new Items(bus);
    }

    public static SinoCraft getInstance() {
        return INSTANCE;
    }

    public Logger getLog() {
        return Log;
    }

    private void setup(final FMLCommonSetupEvent event) {
        Log.info("Hello Minecraft!");

        new BlockAdditions();
    }

    private void setupClient(final FMLClientSetupEvent event) {
        Render.ChangeRender();
    }

    private void setupServer(final FMLDedicatedServerSetupEvent event) {

    }
}
