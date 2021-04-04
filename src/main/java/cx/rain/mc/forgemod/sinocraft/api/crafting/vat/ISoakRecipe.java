package cx.rain.mc.forgemod.sinocraft.api.crafting.vat;

import cx.rain.mc.forgemod.sinocraft.crafting.ModRecipes;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.ICraftingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fluids.FluidStack;

public interface ISoakRecipe extends IRecipe<IInventory> {
    default IRecipeType<?> getType() {
        return ModRecipes.SOAK;
    }

    ItemStack getItem();

    FluidStack getFluidResult();
}
