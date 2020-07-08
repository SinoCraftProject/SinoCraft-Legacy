package cx.rain.mc.forgemod.culturecraft.season.interfaces;

import cx.rain.mc.forgemod.culturecraft.season.enumerate.Season;

public interface ISeasonState
{
    /**
     * 一天的tick数.
     * 正常为24000tick
     *
     * @return The duration in ticks
     */
    int getDayDuration();

    /**
     * 一个季节的tick.
     *
     * @return The duration in ticks
     */
    int getSubSeasonDuration();

    /**
     * 一个季节的tick.
     *
     * @return The duration in ticks
     */
    int getSeasonDuration();

    /**
     *一年的tick
     *
     * @return The duration in ticks
     */
    int getCycleDuration();

    /**
     *
     * 一年的周期会重新有春夏秋冬
     * 333kopnpi
     *
     * @return The time in ticks
     */
    int getSeasonCycleTicks();

    /**
     * Get the number of days elapsed.
     *
     * @return The current day
     */
    int getDay();

    /**
     * Get the current sub season.
     *
     * @return The current sub season
     */

  Season.SubSeason getSubSeason();


    /**
     * Get the current season. This method is
     * mainly for convenience.
     *
     * @return The current season
     */
    Season getSeason();

    /**
     * Get the current tropical season. This method is
     * mainly for convenience.
     *
     * @return The current tropical season
     */

    Season.TropicalSeason getTropicalSeason();


}