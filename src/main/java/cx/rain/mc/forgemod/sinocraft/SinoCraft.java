package cx.rain.mc.forgemod.sinocraft;

import cx.rain.mc.forgemod.sinocraft.advancement.RegistryTrigger;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.gui.container.Containers;
import cx.rain.mc.forgemod.sinocraft.side.Sides;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntities;
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

    private final Logger logger = LogManager.getLogger(SinoCraft.NAME);

    public SinoCraft() {
        INSTANCE = this;
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);
        bus.addListener(this::setupClient);
        bus.addListener(this::setupServer);

        new ModBlocks(bus);
        new ModBlockItems(bus);
        new ModEntities(bus);
        new ModItems(bus);
        new TileEntities(bus);
        new ModFluids(bus);
        new Containers(bus);
        new RegistryTrigger();
    }

    public static SinoCraft getInstance() {
        return INSTANCE;
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Hello Minecraft!");

        Sides.Common(event);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        Sides.Client(event);
    }

    private void setupServer(final FMLDedicatedServerSetupEvent event) {
        Sides.Server(event);
    }

    public static Logger getLog() {
        return INSTANCE.logger;
    }
}
