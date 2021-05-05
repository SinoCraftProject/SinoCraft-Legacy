package cx.rain.mc.forgemod.sinocraft.api.crafting.cooking;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.cooking.IStoveInventory;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public interface ICookingRecipe extends IRecipe<IStoveInventory> {
    /**
     * Judge item set is meeting the rules of this recipe
     * @param items Item set
     * @param thermal the thermal of stove
     * @return Is item set meeting the rules of this recipe
     */
    boolean matches(@Nonnull NonNullList<ItemStack> items, World worldIn, int thermal);

    @Override
    default IRecipeType<?> getType() {
        return ModRecipes.COOKING;
    }

    @Override
    default IRecipeSerializer<?> getSerializer() {
        return new CookingRecipe.Serializer();
    }
}
