package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;

public class FuelHelper {
    public static boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

    public static int getItemBurnTime(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack);
    }
}
