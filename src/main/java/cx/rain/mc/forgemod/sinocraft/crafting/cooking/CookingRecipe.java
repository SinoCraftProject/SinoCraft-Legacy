package cx.rain.mc.forgemod.sinocraft.crafting.cooking;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.capability.IHeat;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipeBuilder;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICountIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.crafting.CountIngredient;
import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.Arrays;

/**
 * Normal recipe
 * You only need to create such an instance for normal recipe
 * {@link IHeat}
 *
 * @author Infinity_rain
 */
public class CookingRecipe implements ICookingRecipe {
    protected final ResourceLocation id;
    protected int minThermal;
    protected int maxThermal;
    protected ItemStack recipeResult = new ItemStack(ModItems.ADUST_FOOD.get());
    protected ItemStack adustResult = new ItemStack(ModItems.ADUST_FOOD.get());
    protected ItemStack container = ItemStack.EMPTY;
    protected int time = 60;
    protected final NonNullList<ICountIngredient> stacks = NonNullList.create();

    public static ICookingRecipeBuilder builder(ResourceLocation id) {
        return new Builder(id);
    }

    CookingRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public int getCookingTime() {
        return time;
    }

    @Override
    public ICountIngredient getInput(int index) {
        return stacks.get(index);
    }

    @Override
    public int getInputSlotCount() {
        return stacks.size();
    }

    @Override
    public int getMinHeat() {
        return minThermal;
    }

    @Override
    public int getMaxHeat() {
        return maxThermal;
    }

    @Override
    public boolean matches(IExtendedRecipeInventory inv, World worldIn) {
        int size = stacks.size();
        if (inv.getItemCount() >= size) {
            int[] map = new int[size];
            Arrays.fill(map, -1);
            for (int i = 0; i < size; i++) {
                boolean match = false;
                for (int j = 0; j < size; j++) {
                    if (map[j] >= 0) continue;
                    if (stacks.get(j).match(inv.getInputItem(i))) {
                        map[j] = i;
                        match = true;
                        break;
                    }
                }
                if (!match) {
                    return false;
                }
            }
            for (int i = 0; i < map.length; i++) {
                inv.setItemSlotMap(i, map[i]);
            }
            return true;
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IExtendedRecipeInventory inv) {
        return inv.getMaxHeat() > maxThermal ? adustResult : recipeResult;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return recipeResult;
    }

    @Override
    public ItemStack getAdustOutput() {
        return adustResult;
    }

    @Override
    public ItemStack getContainer() {
        return container;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.COOKING;
    }

    @Override
    public void serialize(JsonObject json) {
        CookingSerializer.SERIALIZER.write(json, this);
    }

    @Override
    public ResourceLocation getID() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return CookingSerializer.SERIALIZER;
    }

    @Nullable
    @Override
    public JsonObject getAdvancementJson() {
        return null;
    }

    @Nullable
    @Override
    public ResourceLocation getAdvancementID() {
        return null;
    }

    public static class Builder implements ICookingRecipeBuilder {
        final CookingRecipe recipe;

        Builder(ResourceLocation id) {
            recipe = new CookingRecipe(id);
        }

        @Override
        public ICookingRecipeBuilder setHeat(int min, int max) {
            recipe.minThermal = min;
            recipe.maxThermal = max;
            return this;
        }

        @Override
        public ICookingRecipeBuilder setOutput(ItemStack output) {
            recipe.recipeResult = output;
            return this;
        }

        @Override
        public ICookingRecipeBuilder setAdustOutput(ItemStack output) {
            recipe.adustResult = output;
            return this;
        }

        @Override
        public ICookingRecipeBuilder setTime(int time) {
            recipe.time = time;
            return this;
        }

        @Override
        public ICookingRecipeBuilder addInput(Ingredient item) {
            recipe.stacks.add(new CountIngredient(item, 1));
            return this;
        }

        @Override
        public ICookingRecipeBuilder addInput(Ingredient item, int count) {
            recipe.stacks.add(new CountIngredient(item, count));
            return this;
        }

        @Override
        public ICookingRecipeBuilder setContainer(ItemStack container) {
            recipe.container = container;
            return this;
        }

        @Override
        public ICookingRecipeBuilder addInput(ICountIngredient ingredient) {
            recipe.stacks.add(ingredient);
            return this;
        }

        @Override
        public ICookingRecipe build() {
            return recipe;
        }
    }
}
