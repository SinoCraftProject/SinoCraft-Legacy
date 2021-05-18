package cx.rain.mc.forgemod.sinocraft.plugin.jei.category;

import cx.rain.mc.forgemod.sinocraft.api.crafting.ICountIngredient;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IFluidIngredient;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryHelper {

    public static List<ItemStack> getInputItems(ICountIngredient input) {
        if (input == ICountIngredient.EMPTY || input.getCount() == 0) {
            return Collections.emptyList();
        }
        return Arrays.stream(input.getIngredient().getMatchingStacks())
                .map(ItemStack::copy)
                .peek(stack -> stack.setCount(input.getCount()))
                .collect(Collectors.toList());
    }

    public static List<FluidStack> getInputFluids(IFluidIngredient input) {
        if (input == IFluidIngredient.EMPTY || input.getAmount() == 0) {
            return Collections.emptyList();
        }
        if (input.getType() == 0) {
            return Collections.singletonList(new FluidStack(input.getFluid(), input.getAmount()));
        } else {
            return input.getTag().getAllElements().stream()
                    .map(fluid -> new FluidStack(fluid, input.getAmount()))
                    .collect(Collectors.toList());
        }
    }
}
