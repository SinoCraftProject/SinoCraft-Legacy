package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

/**
 * 用于配方校验，单独写了几个方法，原本属于 IInventory 的方法用于直接访问所有物品，新增的则只访问输入物品
 */
public interface IExtendedRecipeInventory extends IInventory {

    /**
     * 根据索引获取输入物品
     * @param index 索引
     * @return 输入物品
     */
    ItemStack getInputItem(int index);

    /**
     * 获取所有输入物品个数
     * @return 物品个数
     */
    int getItemCount();

    /**
     * 设置物品输入索引与配方输入索引之间的对应关系，多用于无序合成
     * @param input 输入索引
     * @param recipe 配方索引
     */
    default void setItemSlotMap(int input, int recipe) {

    }

    /**
     * 根据索引获取输入流体
     * @param index 索引
     * @return 输入流体
     */
    default FluidStack getInputFluid(int index) {
        return FluidStack.EMPTY;
    }

    /**
     * 获取所有输入流体个数
     * @return 流体个数
     */
    default int getFluidCount() {
        return 0;
    }

    /**
     * 设置流体输入索引与配方输入索引之间的对应关系，多用于无序合成
     * @param input 输入索引
     * @param recipe 配方索引
     */
    default void setFluidSlotMap(int input, int recipe) {

    }

    /**
     * 获取运行时当前温度
     * @return 当前温度
     */
    default int getCurrentHeat() {
        return 0;
    }

    /**
     * 清空所有索引映射
     */
    default void removeAllSlotMap() {

    }

    /**
     * 获取运行时最大温度
     * @return 最大温度
     */
    default int getMaxHeat() {
        return 0;
    }
}
