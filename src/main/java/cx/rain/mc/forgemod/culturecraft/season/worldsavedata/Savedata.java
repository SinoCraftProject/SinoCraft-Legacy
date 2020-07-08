package cx.rain.mc.forgemod.culturecraft.season.worldsavedata;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.storage.WorldSavedData;


public abstract class Savedata extends WorldSavedData
{
    public static final String DATA_IDENTIFIER = "season";
    public static final int VERSION = 0;
    public int seasonCycleTicks;

    public Savedata()
    {
        this(DATA_IDENTIFIER);
    }

    //This specific constructor is required for saving to occur
    public Savedata(String identifier)
    {
        super(identifier);
    }




    public void load(CompoundNBT nbt)
    {
        this.seasonCycleTicks = nbt.getInt("SeasonCycleTicks");
    }


    public CompoundNBT save(CompoundNBT nbt)
    {
        nbt.putInt("SeasonCycleTicks", this.seasonCycleTicks);
        return nbt;
    }
}
