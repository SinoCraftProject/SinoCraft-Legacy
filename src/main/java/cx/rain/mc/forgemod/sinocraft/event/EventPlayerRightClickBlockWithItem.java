package cx.rain.mc.forgemod.sinocraft.event;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID)
public class EventPlayerRightClickBlockWithItem {

    @SubscribeEvent
    public static void onClick(PlayerInteractEvent.RightClickBlock event) {
    }
}
