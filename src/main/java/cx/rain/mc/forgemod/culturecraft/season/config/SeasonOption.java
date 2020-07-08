package cx.rain.mc.forgemod.culturecraft.season.config;

public enum SeasonOption implements ISeasonOption
{
    DAY_DURATION("Day Duration"),
    SUB_SEASON_DURATION("Sub Season Duration"),
    STARTING_SUB_SEASON("Starting Sub Season"),
    PROGRESS_SEASON_WHILE_OFFLINE("Progress Season While Offline");

    private final String optionName;

    SeasonOption(String name)
    {
        this.optionName = name;
    }

    @Override
    public String getOptionName()
    {
        return this.optionName;
    }
}