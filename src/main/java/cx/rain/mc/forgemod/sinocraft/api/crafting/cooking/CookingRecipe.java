package cx.rain.mc.forgemod.sinocraft.api.crafting.cooking;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IHeat;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.cooking.IStoveInventory;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.JsonToNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistryEntry;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Normal recipe
 * You only need to create such an instance for normal recipe
 * {@link IHeat}
 *
 * @author Infinity_rain
 */
public class CookingRecipe extends CookingRecipeBase {
    private int minThermal;
    private int maxThermal;
    private NonNullList<ItemStack> stacks;
    private ItemStack recipeResult;
    private boolean isDust;
    private ResourceLocation id;

    /**
     * @param id recipe ID
     * @param minThermal   the min thermal of recipe
     * @param maxThermal   the max thermal of recipe
     * @param recipeResult the result of recipe
     * @param stacks       item set
     */
    public CookingRecipe(ResourceLocation id, int minThermal, int maxThermal, ItemStack recipeResult, ItemStack... stacks) {
        this.id = id;
        this.minThermal = minThermal;
        this.maxThermal = maxThermal;
        this.recipeResult = recipeResult;
        this.stacks = NonNullList.create();
        this.stacks.addAll(Arrays.asList(stacks));
    }

    @Override
    public boolean matches(NonNullList<ItemStack> items, World world, int thermal) {
        isDust = false;
        if (thermal >= minThermal) {
            if (items.equals(stacks)) {
                if (thermal > maxThermal)
                    isDust = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack getCraftingResult(IStoveInventory inv) {
        return isDust ? new ItemStack(ModItems.ADUST_FOOD.get()) : recipeResult;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return isDust ? new ItemStack(ModItems.ADUST_FOOD.get()) : recipeResult;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<CookingRecipe> {
        @Override
        public CookingRecipe read(ResourceLocation recipeId, JsonObject json) {
            try {
                JsonArray _ingredients = json.getAsJsonArray("ingredients");

                List<ItemStack> ingredients = new ArrayList<>();

                for (JsonElement element : _ingredients) {
                    ItemStack item = ItemStack.read(new JsonToNBT(new StringReader(
                            element.getAsJsonPrimitive().getAsString())
                    ).readStruct());
                    ingredients.add(item);
                }

                ItemStack result = ItemStack.read(new JsonToNBT(new StringReader(
                        json.getAsJsonPrimitive("result").getAsString())
                ).readStruct());

                int minThermal = json.getAsJsonPrimitive("minThermal").getAsInt();
                int maxThermal = json.getAsJsonPrimitive("maxThermal").getAsInt();
                return new CookingRecipe(recipeId, minThermal, maxThermal, result, (ItemStack[]) ingredients.toArray());
            } catch (CommandSyntaxException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Nullable
        @Override
        public CookingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            NonNullList<ItemStack> stacks = NonNullList.create();

            int _stacks_size = buffer.readInt();
            for (int i = 0 ; i < _stacks_size ; i ++) {
                stacks.add(ItemStack.read(buffer.readCompoundTag()));
            }

            ItemStack result = ItemStack.read(buffer.readCompoundTag());
            return new CookingRecipe(recipeId, buffer.readInt(), buffer.readInt(), result, (ItemStack[]) stacks.toArray());
        }

        @Override
        public void write(PacketBuffer buffer, CookingRecipe recipe) {
            buffer.writeInt(recipe.stacks.size());

            for (ItemStack stack : recipe.stacks) {
                CompoundNBT nbt = new CompoundNBT();
                stack.write(nbt);
                buffer.writeCompoundTag(nbt);
            }


            CompoundNBT nbt = new CompoundNBT();
            recipe.recipeResult.write(nbt);
            buffer.writeCompoundTag(nbt);

            buffer.writeInt(recipe.minThermal).writeInt(recipe.maxThermal);
        }
    }

}
