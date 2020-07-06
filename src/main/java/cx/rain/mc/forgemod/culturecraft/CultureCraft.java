package cx.rain.mc.forgemod.culturecraft;

import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.client.Render;
import cx.rain.mc.forgemod.culturecraft.entity.Entities;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
@SuppressWarnings("deprecation")
@Mod(value = CultureCraft.MODID)
public class CultureCraft {
    public static final String MODID = "culturecraft";
    public static final String NAME = "Culture Craft";
    public static final String MC_VERSION = "1.15.2";
    public static final String MOD_VERSION = "1.0.0";
    public static final String VERSION = MC_VERSION + "-" + MOD_VERSION;

    private static CultureCraft INSTANCE = null;

    private final Logger Log = LogManager.getLogger(CultureCraft.NAME);

    public CultureCraft() {
        INSTANCE = this;

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        bus.addListener(this::setupServer);

        new Blocks(bus);
        new Items(bus);
    }

    public static CultureCraft getInstance() {
        return INSTANCE;
    }

    public Logger getLog() {
        return Log;
    }

    private void setup(final FMLCommonSetupEvent event) {
        Log.info("Hello Minecraft!");
    }

    private void setupClient(final FMLClientSetupEvent event) {
        Render.ChangeRender();
    }

    private void setupServer(final FMLDedicatedServerSetupEvent event) {

    }
}
