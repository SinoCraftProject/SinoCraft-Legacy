package cx.rain.mc.forgemod.sinocraft.api.crafting;

import com.google.gson.JsonObject;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

/**
 * 格式：<pre>{@code
 * {
 *   "type": "sinocraft:steamer",
 *   // 制作时间
 *   "time": 20,
 *   // 原料
 *   // 内物品与一般配方 ingredient 一致
 *   // 可直接使用物品 id
 *   "ingredient": "minecraft:iron_ingot",
 *   // 产物
 *   // 物品产物，可选，与一般配方产物物品一致，如果个数为 1 且不含 nbt 可直接使用物品 id
 *   "result": "minecraft:acacia_planks",
 *   // 过热产物，可选，默认 sinocraft:adust_food
 *   "adust": "sinocraft:adust_food",
 *   // 温度
 *   "heat": [0, 100]
 * }}</pre>
 */
public interface ISteamerRecipe extends IRecipe<IExtendedRecipeInventory>, IFinishedRecipe {

    int getCookingTime();

    int getMinHeat();

    int getMaxHeat();

    ItemStack getAdustOutput();

    @Override
    default boolean canFit(int width, int height) {
        return true;
    }

    @Override
    default ResourceLocation getID() {
        return getId();
    }

    @Nullable
    @Override
    default JsonObject getAdvancementJson() {
        return null;
    }

    @Nullable
    @Override
    default ResourceLocation getAdvancementID() {
        return null;
    }
}
