package cx.rain.mc.forgemod.sinocraft.side;

import cx.rain.mc.forgemod.sinocraft.side.client.RenderHook;
import cx.rain.mc.forgemod.sinocraft.side.common.BlockAdditions;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class Sides {
    public static void hookCommon(FMLCommonSetupEvent event){
        new BlockAdditions();
    }

    public static void hookClient(FMLClientSetupEvent event){
        new RenderHook();
    }

    public static void hookServer(FMLDedicatedServerSetupEvent event){

    }
}
