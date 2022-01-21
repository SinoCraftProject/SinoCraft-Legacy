package cx.rain.mc.forgemod.sinocraft;

import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tree.ModTrees;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(SinoCraft.MODID)
public class SinoCraft {
    public static final String MODID = "sinocraft";
    public static final String NAME = "SinoCraft";
    public static final String MC_VERSION = "1.18.1";
    public static final String MOD_VERSION = "1.1.0";
    public static final String VERSION = MC_VERSION + "-" + MOD_VERSION;

    private static SinoCraft INSTANCE = null;

    private final Logger logger = LogManager.getLogger(SinoCraft.NAME);

    public SinoCraft() {
        INSTANCE = this;

        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        bus.addListener(this::setup);

        new ModBlocks(bus);
        new ModBlockItems(bus);
//        new ModEntities(bus);
        new ModItems(bus);

        new ModTrees(bus);
//        new ModTileEntities(bus);
//        new ModFluids(bus);
//        new ModContainers(bus);
//        new ModRecipes(bus);
//        new ModModifiers(bus);
    }

    public static SinoCraft getInstance() {
        return INSTANCE;
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Hello SinoCraft!");
//        ModNetworking.setup();
    }

    public Logger getLogger() {
        return logger;
    }
}
