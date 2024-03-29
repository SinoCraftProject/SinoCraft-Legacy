package cx.rain.mc.forgemod.sinocraft;

import cx.rain.mc.forgemod.sinocraft.api.block.ISinoBlocks;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISinoRecipes;
import cx.rain.mc.forgemod.sinocraft.api.item.ISinoItems;
import cx.rain.mc.forgemod.sinocraft.api_impl.APIBlocks;
import cx.rain.mc.forgemod.sinocraft.api_impl.APIItems;
import cx.rain.mc.forgemod.sinocraft.api_impl.APIRecipes;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.ModTileEntities;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import cx.rain.mc.forgemod.sinocraft.data.modifier.ModModifiers;
import cx.rain.mc.forgemod.sinocraft.entity.ModEntities;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.gui.container.ModContainers;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.network.Networks;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(value = SinoCraft.MODID)
public class SinoCraft {
    public static final String MODID = "sinocraft";
    public static final String NAME = "SinoCraft";
    public static final String MC_VERSION = "1.16.5";
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
        new ModEntities(bus);
        new ModItems(bus);
        new ModTileEntities(bus);
        new ModFluids(bus);
        new ModContainers(bus);
        new ModRecipes(bus);
        new ModModifiers(bus);
        // remove：client only
        // ComponentType.init(bus);

        // API
        ISinoRecipes.INSTANCE.set(APIRecipes.INSTANCE);
        ISinoItems.INSTANCE.set(APIItems.INSTANCE);
        ISinoBlocks.INSTANCE.set(APIBlocks.INSTANCE);
    }

    public static SinoCraft getInstance() {
        return INSTANCE;
    }

    private void setup(final FMLCommonSetupEvent event) {
        logger.info("Hello SinoCraft!");
        Networks.setup();
    }

    public static Logger getLogger() {
        return INSTANCE.logger;
    }
}
