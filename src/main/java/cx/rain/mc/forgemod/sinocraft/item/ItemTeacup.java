package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.table.TableTeacup;
import cx.rain.mc.forgemod.sinocraft.item.base.TableItem;
import cx.rain.mc.forgemod.sinocraft.utility.NBTHelper;
import net.minecraft.item.ItemStack;

public class ItemTeacup extends TableItem {

    public int getTea(ItemStack stack) {
        return NBTHelper.getOrDefault(stack, "teacup", "tea", 0);
    }

    public int addTea(ItemStack stack, int tea) {
        if (tea <= 0) {
            return 0;
        }
        int value = getTea(stack);
        int capacity = getCapacity();
        if (value >= capacity) {
            return 0;
        }
        int newCount = value + tea;
        if (newCount >= capacity) {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teacup").putInt("tea", capacity);
            return capacity - value;
        } else {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teacup").putInt("tea", newCount);
            return tea;
        }
    }

    public int takeTea(ItemStack stack, int teaCount) {
        if (teaCount <= 0) {
            return 0;
        }
        int value = getTea(stack);
        if (value <= 0) {
            return 0;
        }
        if (value <= teaCount) {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teacup").putInt("tea", 0);
            return value;
        } else {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teacup").putInt("tea", value - teaCount);
            return teaCount;
        }
    }

    public int getCapacity() {
        return 1000;
    }

    public boolean isFull(ItemStack stack) {
        return getTea(stack) >= getCapacity();
    }

    @Override
    public BaseTableElement createElement(double x, double y, double z) {
        return new TableTeacup(x, y, z);
    }

    public static ItemStack build(ItemStack stack, int tea) {
        NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teacup").putInt("tea", tea);
        return stack;
    }
}
