package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.gui.GuiChineseBrush;
import cx.rain.mc.forgemod.sinocraft.gui.container.ModContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class BlindContainer {
    @SubscribeEvent
    public static void blind(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainers.CHINESE_BRUSH.get(),
                GuiChineseBrush::create
        );
    }
}
