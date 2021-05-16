package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.gui.GuiChineseBrush;
import cx.rain.mc.forgemod.sinocraft.gui.container.ModContainers;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class BlindContainer {
    @SubscribeEvent
    public static void blind(FMLClientSetupEvent event) {
        ScreenManager.registerFactory(ModContainers.CHINESE_BRUSH.get(),
                GuiChineseBrush::create
        );
    }
}
