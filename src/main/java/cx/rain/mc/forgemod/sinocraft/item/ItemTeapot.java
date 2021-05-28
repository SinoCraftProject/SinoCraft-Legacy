package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.table.TableTeapot;
import cx.rain.mc.forgemod.sinocraft.item.base.TableItem;
import cx.rain.mc.forgemod.sinocraft.utility.NBTHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class ItemTeapot extends TableItem {

    public float getLeaves(ItemStack stack) {
        return NBTHelper.getOrDefault(stack, "teapot", "leaves", 0f);
    }

    public float addLeaves(ItemStack stack, float leaves) {
        float value = getLeaves(stack);
        float newValue = value + leaves;
        float capacity = getLeavesCapacity();
        if (newValue > capacity) {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teapot").putFloat("leaves", capacity);
            return capacity - value;
        } else {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teapot").putFloat("leaves", newValue);
            return leaves;
        }
    }

    public float takeLeaves(ItemStack stack, float leaves) {
        float value = getLeaves(stack);
        if (value > leaves) {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teapot").putFloat("leaves", value - leaves);
            return leaves;
        } else {
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teapot").putFloat("leaves", 0);
            return value;
        }
    }

    public int getWater(ItemStack stack) {
        return NBTHelper.getOrDefault(stack, "teapot", "water", 0);
    }

    public int addWater(ItemStack stack, int water) {
        return addFluid(stack, "water", water);
    }

    public int takeWater(ItemStack stack, int water) {
        return takeFluid(stack, "water", water);
    }

    public int getTea(ItemStack stack) {
        return NBTHelper.getOrDefault(stack, "teapot", "tea", 0);
    }

    public int addTea(ItemStack stack, int tea) {
        return addFluid(stack, "tea", tea);
    }

    public int takeTea(ItemStack stack, int tea) {
        return takeFluid(stack, "tea", tea);
    }

    public boolean isLeavesFull(ItemStack stack) {
        return getLeaves(stack) >= getLeavesCapacity();
    }

    public boolean isWaterFull(ItemStack stack) {
        return getWater(stack) + getTea(stack) >= getWaterCapacity();
    }

    public float getLeavesCapacity() {
        return 10f;
    }

    public int getWaterCapacity() {
        return 10000;
    }

    private int addFluid(ItemStack stack, String key, int value) {
        int waterCount = getWater(stack) + getTea(stack);
        int capacity = getWaterCapacity();
        if (waterCount >= capacity) {
            return 0;
        }
        int newCount = waterCount + value;
        if (newCount > capacity) {
            int add = capacity - waterCount;
            int newValue = NBTHelper.getOrDefault(stack, "teapot", key, 0) + add;
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teapot").putInt(key, newValue);
            return add;
        } else {
            int newValue = NBTHelper.getOrDefault(stack, "teapot", key, 0) + value;
            NBTHelper.getOrCreateTag(stack.getOrCreateTag(), "teapot").putInt(key, newValue);
            return value;
        }
    }

    private int takeFluid(ItemStack stack, String key, int value) {
        int thisValue = NBTHelper.getOrDefault(stack, "teapot", key, 0);
        if (thisValue > value) {
            int newValue = thisValue - value;
            NBTHelper.getOrCreateTag(stack.getTag(), "teapot").putInt(key, newValue);
            return value;
        } else {
            NBTHelper.getOrCreateTag(stack.getTag(), "teapot").putInt(key, 0);
            return thisValue;
        }
    }

    @Override
    public BaseTableElement createElement(double x, double y, double z) {
        return new TableTeapot(x, y, z);
    }

    public static ItemStack build(ItemStack stack, int water, int tea, float leaves) {
        CompoundNBT tag = stack.getOrCreateChildTag("teapot");
        tag.putFloat("leaves", leaves);
        tag.putInt("water", water);
        tag.putInt("tea", tea);
        return stack;
    }
}
