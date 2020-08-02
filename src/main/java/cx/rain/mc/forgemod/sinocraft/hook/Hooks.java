package cx.rain.mc.forgemod.sinocraft.hook;

import cx.rain.mc.forgemod.sinocraft.hook.client.RenderHook;
import cx.rain.mc.forgemod.sinocraft.hook.common.BlockAdditions;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class Hooks {
    public static void hookCommon(FMLCommonSetupEvent event){
        new BlockAdditions();
    }

    public static void hookClient(FMLClientSetupEvent event){
        new RenderHook();
    }

    public static void hookServer(FMLDedicatedServerSetupEvent event){

    }
}
