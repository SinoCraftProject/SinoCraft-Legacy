package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public interface IFluidIngredient {

    IFluidIngredient EMPTY = IModRecipes.getInstance().newFluidIngredient(Fluids.EMPTY, 0);

    int getType();

    Fluid getFluid();

    ITag<Fluid> getTag();

    ResourceLocation getResourceLocation();

    int getAmount();

    boolean match(FluidStack stack);

    void write(PacketBuffer buffer);
}
