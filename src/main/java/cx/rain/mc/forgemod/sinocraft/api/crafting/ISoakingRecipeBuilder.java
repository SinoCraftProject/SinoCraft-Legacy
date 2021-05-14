package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public interface ISoakingRecipeBuilder {

    ISoakingRecipeBuilder setInput(Ingredient ingredient);

    ISoakingRecipeBuilder setInput(Ingredient ingredient, int count);

    ISoakingRecipeBuilder setInput(Fluid fluid);

    ISoakingRecipeBuilder setInput(Fluid fluid, int amount);

    ISoakingRecipeBuilder setInput(ResourceLocation fluidTag);

    ISoakingRecipeBuilder setInput(ResourceLocation fluidTag, int amount);

    ISoakingRecipeBuilder setOutput(ItemStack stack);

    ISoakingRecipeBuilder setOutput(FluidStack stack);

    ISoakingRecipeBuilder setTime(int time);

    ISoakingRecipe build();
}
