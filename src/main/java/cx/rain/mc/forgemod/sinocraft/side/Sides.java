package cx.rain.mc.forgemod.sinocraft.side;

import cx.rain.mc.forgemod.sinocraft.network.Networks;
import cx.rain.mc.forgemod.sinocraft.side.client.RenderHook;
import cx.rain.mc.forgemod.sinocraft.side.common.BlockAdditions;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent;

public class Sides {
    public static void Common(FMLCommonSetupEvent event){
        new BlockAdditions();
        Networks.setup();
        Networks.registerMessages();
    }

    public static void Client(FMLClientSetupEvent event){
        new RenderHook();
    }

    public static void Server(FMLDedicatedServerSetupEvent event){

    }
}
