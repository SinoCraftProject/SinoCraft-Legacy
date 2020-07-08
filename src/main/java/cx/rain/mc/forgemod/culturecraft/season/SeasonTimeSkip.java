package cx.rain.mc.forgemod.culturecraft.season;
/*
import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.season.worldsavedata.Savedata;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber
public class TimeSkipHandler
{
    private long lastDayTime = -1;

    @SubscribeEvent
    public void onWorldTick(TickEvent.WorldTickEvent event)
    {
        if (event.phase == TickEvent.Phase.START && event.side == LogicalSide.SERVER)
        {
            ServerWorld world = (ServerWorld)event.world;
            long dayTime = world.getLevelData().getDayTime();

            if (lastDayTime == -1)
            {
                lastDayTime = dayTime;
            }

            long difference = world.getLevelData().getDayTime() - lastDayTime;

            if (difference < 0)
            {
                difference += 24000L;
            }

            // Time has skipped, skip the season time too
            if (difference > 1)
            {
                Savedata seasonData = SeasonHandler.getSeasonSavedData(world);
                seasonData.seasonCycleTicks += difference;
                seasonData.setDirty();
                SeasonHandler.sendSeasonUpdate(world);
                CultureCraft.Log.info("Season time skipped by " + difference);
            }

            lastDayTime = dayTime;
        }
    }
}

 */