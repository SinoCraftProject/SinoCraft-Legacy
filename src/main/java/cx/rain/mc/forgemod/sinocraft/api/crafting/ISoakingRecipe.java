package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;

/**
 * 格式：<pre>{@code
 * {
 *   "type": "sinocraft:soaking",
 *   // 制作时间
 *   "time": 20
 *   // 原料
 *   "ingredients": {
 *     // 内物品与一般配方 ingredients 一致
 *     //  count 可选，默认 1
 *     // 若为物品列表则使用 items 属性
 *     "item": {
 *       "item": "minecraft:iron_ingot",
 *       // 物品列表，相当于一般配方中原料传入的数组
 *       // "items": [
 *       //   { "item": "...“ }， { "tag": "..." }, ...
 *       // ]
 *       "count": 3
 *     },
 *     // 流体配方，支持 fluid, tag, amount
 *     //  amount 可选，默认 1000
 *     "fluid": {
 *       "fluid": "minecraft:lava",
 *       "amount": 500
 *     }
 *   },
 *   // 产物
 *   "results": {
 *     // 物品产物，可选，与一般配方产物物品一致，如果个数为 1 且不含 nbt 可直接使用物品 id
 *     // "item": "minecraft:acacia_planks"
 *     "item": {
 *       "item": "minecraft:acacia_planks",
 *       "count": 2
 *     },
 *     // 流体产物，可选，包含 fluid 和 amount，若 amount=1000 可直接使用流体 id
 *     // "fluid": "minecraft:water"
 *     "fluid": {
 *       "fluid": "minecraft:water",
 *       "amount": 500
 *     }
 *   }
 * }}</pre>
 */
public interface ISoakingRecipe extends IRecipe<IExtendedRecipeInventory>, IFinishedRecipe {

    int getSoakingTime();

    ICountIngredient getInputItem();

    IFluidIngredient getInputFluid();

    FluidStack getFluidOutput();

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }
}
