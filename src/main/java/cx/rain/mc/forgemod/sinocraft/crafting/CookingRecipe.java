package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.*;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;

/**
 * Normal recipe
 * You only need to create such an instance for normal recipe
 * {@link IHeat}
 *
 * @author Infinity_rain
 */
public class CookingRecipe implements ICookingRecipe {
    final ResourceLocation id;
    int minThermal;
    int maxThermal;
    ItemStack recipeResult = new ItemStack(ModItems.ADUST_FOOD.get());
    ItemStack adustResult = new ItemStack(ModItems.ADUST_FOOD.get());
    int time = 60;
    final NonNullList<CountIngredient> stacks = NonNullList.create();

    public static CookingRecipe.Builder builder(ResourceLocation id) {
        return new Builder(id);
    }

    public static CookingRecipe.Builder builder(String id) {
        return new Builder(new ResourceLocation(id));
    }

    CookingRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public int getCookingTime() {
        return time;
    }

    @Override
    public CountIngredient getInput(int index) {
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
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeType<?> getType() {
        return IModRecipes.getInstance().getCookingRecipe();
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
        public Builder setHeat(int min, int max) {
            recipe.minThermal = min;
            recipe.maxThermal = max;
            return this;
        }

        @Override
        public Builder setOutput(ItemStack output) {
            recipe.recipeResult = output;
            return this;
        }

        @Override
        public Builder setAdustOutput(ItemStack output) {
            recipe.adustResult = output;
            return this;
        }

        @Override
        public Builder setTime(int time) {
            recipe.time = time;
            return this;
        }

        @Override
        public Builder addInput(Ingredient item) {
            recipe.stacks.add(new CountIngredient(item, 1));
            return this;
        }

        @Override
        public Builder addInput(Ingredient item, int count) {
            recipe.stacks.add(new CountIngredient(item, count));
            return this;
        }

        public Builder addInput(CountIngredient ingredient) {
            recipe.stacks.add(ingredient);
            return this;
        }

        @Override
        public CookingRecipe build() {
            return recipe;
        }
    }
}
