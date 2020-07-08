package cx.rain.mc.forgemod.culturecraft.season;

import com.google.common.base.Preconditions;
import cx.rain.mc.forgemod.culturecraft.season.config.SeasonOption;
import cx.rain.mc.forgemod.culturecraft.season.config.SyncedConfig;
import cx.rain.mc.forgemod.culturecraft.season.enumerate.Season;
import cx.rain.mc.forgemod.culturecraft.season.interfaces.ISeasonState;

public final class SeasonTime implements ISeasonState {
    public static final SeasonTime ZERO = new SeasonTime(0);
    public final int time;

    public SeasonTime(int time) {
        Preconditions.checkArgument(time >= 0, "Time cannot be negative!");
        this.time = time;
    }

    @Override
    public int getDayDuration()
    {
        return SyncedConfig.getIntValue(SeasonOption.DAY_DURATION);
    }

            @Override
            public int getSubSeasonDuration()
            {
                return getDayDuration() * SyncedConfig.getIntValue(SeasonOption.SUB_SEASON_DURATION);
            }

    @Override
    public int getSeasonDuration() {
        return getSubSeasonDuration() * 3;
    }

    @Override
    public int getCycleDuration() {
        return getSubSeasonDuration() * Season.SubSeason.VALUES.length;
    }

    @Override
    public int getSeasonCycleTicks() {
        return this.time;
    }

    @Override
    public int getDay() {
        return this.time / getDayDuration();
    }

    @Override
    public Season.SubSeason getSubSeason() {
        int index = (this.time / getSubSeasonDuration()) % Season.SubSeason.VALUES.length;
        return Season.SubSeason.VALUES[index];
    }

    @Override
    public Season getSeason() {
        return null;
    }

    @Override
    public Season.TropicalSeason getTropicalSeason() {
        return null;
    }
}