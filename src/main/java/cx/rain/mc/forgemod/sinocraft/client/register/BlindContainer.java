package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.client.gui.GuiDrawWithChineseBrush;
import cx.rain.mc.forgemod.sinocraft.gui.Containers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlindContainer {
    @SubscribeEvent
    public static void blind(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(Containers.CHINESE_BRUSH.get(),
                GuiDrawWithChineseBrush::create
        );
    }
}
