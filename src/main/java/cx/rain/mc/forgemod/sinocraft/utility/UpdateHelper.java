package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;

public class UpdateHelper {

    public static IntNBT fixHeatData(INBT nbt) {
        if (nbt instanceof IntNBT) {
            return (IntNBT) nbt;
        } else if (nbt instanceof CompoundNBT) {
            CompoundNBT data = (CompoundNBT) nbt;
            return IntNBT.valueOf(data.getInt("heat"));
        } else {
            return IntNBT.valueOf(0);
        }
    }
}
