package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;

/**
 * 格式：<pre>{@code
 * {
 *   "type": "sinocraft:mill",
 *   // 内物品与一般无序配方 ingredients 一致
 *   "input": {
 *       "item": "minecraft:iron_ingot",
 *   },
 *   // 右键次数
 *   "time": 100,
 *   // 产物，与一般配方产物物品一致，如果个数为 1 且不含 nbt 可直接使用物品 id
 *   "result": "minecraft:acacia_planks",
 * }}</pre>
 */
public interface IMillRecipe extends IRecipe<IExtendedRecipeInventory>, IFinishedRecipe {

    /**
     * Get the times need to right click
     */
    int getTime();

    Ingredient getInput();
}
