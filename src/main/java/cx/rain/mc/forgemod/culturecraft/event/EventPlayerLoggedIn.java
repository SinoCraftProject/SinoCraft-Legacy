package cx.rain.mc.forgemod.culturecraft.event;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = CultureCraft.MODID)
public class EventPlayerLoggedIn {
    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        event.getPlayer().sendMessage(
                new TranslationTextComponent("message.culturecraft.player.join.welcome"));
    }
}
