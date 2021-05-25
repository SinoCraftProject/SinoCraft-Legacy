package cx.rain.mc.forgemod.sinocraft.utility;

import jdk.internal.jline.internal.Nullable;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.Constants;

import java.util.Optional;

public class ItemTagHelper {

    @Nullable
    public static Optional<CompoundNBT> get(ItemStack stack) {
        return Optional.ofNullable(stack.getTag());
    }

    public static int getOrDefault(ItemStack stack, String key, int defaultValue) {
        CompoundNBT tag = stack.getTag();
        return tag == null || tag.contains(key, Constants.NBT.TAG_INT) ? defaultValue : tag.getInt(key);
    }

    public static byte getOrDefault(ItemStack stack, String key, byte defaultValue) {
        CompoundNBT tag = stack.getTag();
        return tag == null || tag.contains(key, Constants.NBT.TAG_BYTE) ? defaultValue : tag.getByte(key);
    }

    public static String getOrDefault(ItemStack stack, String key, String defaultValue) {
        CompoundNBT tag = stack.getTag();
        return tag == null || tag.contains(key, Constants.NBT.TAG_STRING) ? defaultValue : tag.getString(key);
    }

    public static boolean getOrDefault(ItemStack stack, String key, boolean defaultValue) {
        CompoundNBT tag = stack.getTag();
        return tag == null || tag.contains(key, Constants.NBT.TAG_BYTE) ? defaultValue : tag.getBoolean(key);
    }
}
