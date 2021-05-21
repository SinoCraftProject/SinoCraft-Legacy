package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IMillRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IMillRecipeBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MillRecipe implements IMillRecipe {
    final ResourceLocation id;
    Ingredient input = Ingredient.EMPTY;
    ItemStack output = ItemStack.EMPTY;
    int time = 60;

    public static IMillRecipeBuilder builder(ResourceLocation id) {
        return new Builder(id);
    }

    MillRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public boolean matches(IExtendedRecipeInventory inv, World worldIn) {
        return inv.getItemCount() >= 0 && input.test(inv.getInputItem(0));
    }

    @Override
    public ItemStack getCraftingResult(IExtendedRecipeInventory inv) {
        return output;
    }

    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public IRecipeType<?> getType() {
        return ModRecipes.MILL;
    }

    @Override
    public void serialize(JsonObject json) {
        MillSerializer.SERIALIZER.write(json, this);
    }

    @Override
    public ResourceLocation getID() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return MillSerializer.SERIALIZER;
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

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public Ingredient getInput() {
        return input;
    }

    static class Builder implements IMillRecipeBuilder {
        final MillRecipe recipe;

        Builder(ResourceLocation id) {
            recipe = new MillRecipe(id);
        }

        @Override
        public IMillRecipeBuilder setInput(IItemProvider input) {
            recipe.input = Ingredient.fromItems(input);
            return this;
        }

        @Override
        public IMillRecipeBuilder setInput(Ingredient input) {
            recipe.input = input;
            return this;
        }

        @Override
        public IMillRecipeBuilder setOutput(ItemStack output) {
            recipe.output = output.copy();
            return this;
        }

        @Override
        public IMillRecipeBuilder setTime(int time) {
            recipe.time = time;
            return this;
        }

        @Override
        public IMillRecipe build() {
            return recipe;
        }
    }
}
