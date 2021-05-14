package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraftforge.common.util.Constants;

public class DataFixHelper {

    public static CompoundNBT fixHeatData(INBT nbt) {
        if (nbt instanceof IntNBT) {
            CompoundNBT newData = new CompoundNBT();
            newData.putInt("heat", ((IntNBT) nbt).getInt());
            newData.putInt("maxHeat", 0);
            return newData;
        } else if (nbt instanceof CompoundNBT) {
            CompoundNBT data = (CompoundNBT) nbt;
            if (!data.contains("heat", Constants.NBT.TAG_INT)) {
                data.putInt("heat", 0);
            }
            if (!data.contains("maxHeat", Constants.NBT.TAG_INT)) {
                data.putInt("maxHeat", 0);
            }
            return data;
        } else {
            CompoundNBT newData = new CompoundNBT();
            newData.putInt("heat", 0);
            newData.putInt("maxHeat", 0);
            return newData;
        }
    }
}
