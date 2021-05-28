package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;

import javax.annotation.Nullable;

public class NBTHelper {

    @Nullable
    public static CompoundNBT getTag(@Nullable CompoundNBT parent, String path) {
        if (parent == null) {
            return null;
        }
        if (path.contains("/")) {
            CompoundNBT result = parent;
            for (String s : path.split("/")) {
                result = getTag(result, s);
                if (result == null) {
                    return null;
                }
            }
            return result;
        } else if (parent.contains(path, Constants.NBT.TAG_COMPOUND)) {
            return parent.getCompound(path);
        } else {
            return null;
        }
    }

    public static CompoundNBT getOrCreateTag(CompoundNBT parent, String path) {
        if (path.contains("/")) {
            CompoundNBT result = parent;
            for (String s : path.split("/")) {
                result = getOrCreateTag(result, s);
            }
            return result;
        } else if (parent.contains(path, Constants.NBT.TAG_COMPOUND)) {
            return parent.getCompound(path);
        } else {
            CompoundNBT nbt = new CompoundNBT();
            parent.put(path, nbt);
            return nbt;
        }
    }

    public static int getOrDefault(ItemStack stack, String path, String key, int defaultValue) {
        CompoundNBT tag = getTag(stack.getTag(), key);
        return tag == null || tag.contains(key, Constants.NBT.TAG_INT) ? defaultValue : tag.getInt(key);
    }

    public static float getOrDefault(ItemStack stack, String path, String key, float defaultValue) {
        CompoundNBT tag = getTag(stack.getTag(), key);
        return tag == null || tag.contains(key, Constants.NBT.TAG_FLOAT) ? defaultValue : tag.getFloat(key);
    }

}
