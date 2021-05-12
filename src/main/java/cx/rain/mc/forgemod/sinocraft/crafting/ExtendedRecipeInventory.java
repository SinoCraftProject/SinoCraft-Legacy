package cx.rain.mc.forgemod.sinocraft.crafting;

import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.wrapper.RecipeWrapper;

public class ExtendedRecipeInventory extends RecipeWrapper {

    private final IItemHandler items;
    private final IFluidHandler fluids;

    public ExtendedRecipeInventory(IItemHandlerModifiable items, IFluidHandler fluids) {
        super(items);
        this.items = items;
        this.fluids = fluids;
    }

    public IItemHandler getItems() {
        return items;
    }

    public IFluidHandler getFluids() {
        return fluids;
    }

    public int getFluidCount() {
        return fluids.getTanks();
    }

    public FluidStack getFluid(int index) {
        return fluids.getFluidInTank(index);
    }
}
