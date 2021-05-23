package cx.rain.mc.forgemod.sinocraft.api.crafting;

import cx.rain.mc.forgemod.sinocraft.api.SinoCraftAPI;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

/**
 * An ingredient to match fluid stack by fluid or tag and amount.
 */
public interface IFluidIngredient {

    IFluidIngredient EMPTY = SinoCraftAPI.getRecipes().newFluidIngredient(Fluids.EMPTY, 0);

    int getType();

    Fluid getFluid();

    ITag<Fluid> getTag();

    ResourceLocation getResourceLocation();

    int getAmount();

    /**
     * test input fluid base on ingredient and amount.
     */
    boolean match(FluidStack stack);

    /**
     * write the ingredient to network packet.
     */
    void write(PacketBuffer buffer);
}
