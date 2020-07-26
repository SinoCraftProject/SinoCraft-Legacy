package cx.rain.mc.forgemod.sinocraft.event;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.advanement.RegistryTrigger;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID)
public class EventPlayerRightClickBlockWithItem {

    @SubscribeEvent
    public static void onClick(PlayerInteractEvent.RightClickBlock event) {
        if(!event.getWorld().isRemote){
            RegistryTrigger.RIGHT_CLICK_BLOCK_WITH_ITEM.test((ServerPlayerEntity) event.getPlayer(),
                    event.getPos(),event.getPlayer().getHeldItem(event.getHand()));
        }
    }
}
