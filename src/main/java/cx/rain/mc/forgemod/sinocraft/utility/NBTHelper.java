package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;

import java.util.stream.Collector;

public class NBTHelper {

    public static int getOrDefault(ItemStack stack, String key, int defaultValue) {
        CompoundNBT tag = stack.getTag();
        return tag == null || tag.contains(key, Constants.NBT.TAG_INT) ? defaultValue : tag.getInt(key);
    }

    public static float getOrDefault(ItemStack stack, String key, float defaultValue) {
        CompoundNBT tag = stack.getTag();
        return tag == null || tag.contains(key, Constants.NBT.TAG_FLOAT) ? defaultValue : tag.getFloat(key);
    }

}
