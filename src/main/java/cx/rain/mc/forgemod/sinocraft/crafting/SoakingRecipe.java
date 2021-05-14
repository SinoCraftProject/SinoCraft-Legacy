package cx.rain.mc.forgemod.sinocraft.crafting;

import com.google.gson.JsonObject;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IExtendedRecipeInventory;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IModRecipes;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISoakingRecipeBuilder;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidStack;
import org.jetbrains.annotations.Nullable;

public final class SoakingRecipe implements ISoakingRecipe {

    CountIngredient inputItem = null;
    FluidIngredient inputFluid = null;
    ItemStack outputItem = ItemStack.EMPTY;
    FluidStack outputFluid = FluidStack.EMPTY;
    int time;
    final ResourceLocation id;

    public static ISoakingRecipeBuilder builder(ResourceLocation id) {
        return new SoakingRecipe.Builder(id);
    }

    public static SoakingRecipe.Builder builder(String id) {
        return new SoakingRecipe.Builder(new ResourceLocation(id));
    }

    SoakingRecipe(ResourceLocation id) {
        this.id = id;
    }

    @Override
    public int getSoakingTime() {
        return time;
    }

    @Override
    public int getInputCount() {
        return inputItem == null ? 0 : inputItem.count;
    }

    @Override
    public int getFluidAmount() {
        return inputFluid == null ? 0 : inputFluid.amount;
    }

    @Override
    public FluidStack getFluidOutput() {
        return outputFluid;
    }

    @Override
    public boolean matches(IExtendedRecipeInventory inv, World worldIn) {
        return inv.getItemCount() > 0  && inputItem.match(inv.getInputItem(0))
            && inv.getFluidCount() > 0 && inputFluid.match(inv.getInputFluid(0));
    }

    @Override
    public ItemStack getCraftingResult(IExtendedRecipeInventory inv) {
        return outputItem;
    }

    @Override
    public ItemStack getRecipeOutput() {
        return outputItem;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public void serialize(JsonObject json) {
        SoakingSerializer.SERIALIZER.write(json, this);
    }

    @Override
    public ResourceLocation getID() {
        return id;
    }

    @Override
    public IRecipeSerializer<?> getSerializer() {
        return SoakingSerializer.SERIALIZER;
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
    public IRecipeType<?> getType() {
        return IModRecipes.getInstance().getSoakingRecipe();
    }

    public static class Builder implements ISoakingRecipeBuilder {
        SoakingRecipe recipe;

        Builder(ResourceLocation id) {
            recipe = new SoakingRecipe(id);
        }

        @Override
        public Builder setInput(Ingredient ingredient) {
            recipe.inputItem = new CountIngredient(ingredient, 1);
            return this;
        }

        @Override
        public Builder setInput(Ingredient ingredient, int count) {
            recipe.inputItem = new CountIngredient(ingredient, count);
            return this;
        }

        public Builder setInput(CountIngredient ingredient) {
            recipe.inputItem = ingredient;
            return this;
        }

        @Override
        public Builder setInput(Fluid fluid) {
            recipe.inputFluid = new FluidIngredient(fluid, 1000);
            return this;
        }

        @Override
        public Builder setInput(Fluid fluid, int amount) {
            recipe.inputFluid = new FluidIngredient(fluid, amount);
            return this;
        }

        public Builder setInput(FluidIngredient ingredient) {
            recipe.inputFluid = ingredient;
            return this;
        }

        @Override
        public Builder setInput(ResourceLocation fluidTag) {
            recipe.inputFluid = new FluidIngredient(fluidTag, 1000);
            return this;
        }

        @Override
        public Builder setInput(ResourceLocation fluidTag, int amount) {
            recipe.inputFluid = new FluidIngredient(fluidTag, amount);
            return this;
        }

        @Override
        public Builder setOutput(ItemStack stack) {
            recipe.outputItem = stack;
            return this;
        }

        @Override
        public Builder setOutput(FluidStack stack) {
            recipe.outputFluid = stack;
            return this;
        }

        @Override
        public Builder setTime(int time) {
            recipe.time = time;
            return this;
        }

        @Override
        public SoakingRecipe build() {
            return recipe;
        }
    }
}
