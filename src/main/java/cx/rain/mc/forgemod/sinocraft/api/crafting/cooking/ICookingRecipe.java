package cx.rain.mc.forgemod.sinocraft.api.crafting.cooking;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ICountIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;

/**
 * 格式：<pre>{@code
 * {
 *   "type": "sinocraft:cooking",
 *   // 内物品与一般无序配方 ingredients 一致
 *   //  增加一个 count 属性，默认 1
 *   "ingredients": [
 *     {
 *       "item": "minecraft:iron_ingot",
 *       "count": 2
 *     },
 *     {
 *       "tag": "minecraft:planks"
 *     },
 *     // 物品列表默认 count 为 1，否则使用 items
 *     [ { "item": "..." }, { "tag": "..." }, ... ],
 *     {
 *       "items": [ { "item": "..." }, { "tag": "..." }, ... ],
 *       "count": 3
 *     }
 *   ],
 *   // 制作时间
 *   "time": 100,
 *   // 一般产物，与一般配方产物物品一致，如果个数为 1 且不含 nbt 可直接使用物品 id
 *   "result": "minecraft:acacia_planks",
 *   // 过热产物，可选，格式与一般产物一致，默认 sinocraft:adust_food
 *   "adust": {
 *     "item": "minecraft:acacia_boat",
 *     "count": 2
 *   },
 *   // 温度范围：温度下限决定是否可反应，温度上限决定是否产生过热产物
 *   // 也可以使用 minThermal 和 maxThermal，值为 int，但二者必须同时使用
 *   "heat": [50, 100],
 * }}</pre>
 */
public interface ICookingRecipe extends IRecipe<IExtendedRecipeInventory>, IFinishedRecipe {

    int getCookingTime();

    /**
     * Get input ingredient by index.
     * Index must less than {@link #getInputSlotCount()}
     */
    ICountIngredient getInput(int index);

    /**
     * Get the input count.
     */
    int getInputSlotCount();

    /**
     * Get the min heat. If heat is less than it, the recipe will not begin.
     */
    int getMinHeat();

    /**
     * Get the max heat. If any heat is more than it during crafting, it will return {@link #getAdustOutput()}.
     */
    int getMaxHeat();

    /**
     * Return result if any heat is more than {@link #getMaxHeat()} during crafting.
     */
    ItemStack getAdustOutput();

    /**
     * Return the container for dish
     */
    ItemStack getContainer();

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }
}
