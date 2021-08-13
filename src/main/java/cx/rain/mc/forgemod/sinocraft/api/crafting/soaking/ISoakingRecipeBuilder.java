package cx.rain.mc.forgemod.sinocraft.api.crafting.soaking;

import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag;
import net.minecraftforge.fluids.FluidStack;

public interface ISoakingRecipeBuilder {

    ISoakingRecipeBuilder setInput(Ingredient ingredient);

    ISoakingRecipeBuilder setInput(Ingredient ingredient, int count);

    ISoakingRecipeBuilder setInput(Fluid fluid);

    ISoakingRecipeBuilder setInput(Fluid fluid, int amount);

    ISoakingRecipeBuilder setInput(ITag<Fluid> fluid);

    ISoakingRecipeBuilder setInput(ITag<Fluid> fluid, int amount);

    ISoakingRecipeBuilder setOutput(ItemStack stack);

    ISoakingRecipeBuilder setOutput(FluidStack stack);

    ISoakingRecipeBuilder setTime(int time);

    ISoakingRecipe build();
}
