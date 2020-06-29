package cx.rain.mc.forgemod.culturecraft.registry;

import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class RegistryFluid {
    public static final ResourceLocation STILL_OIL_TEXTURE = new ResourceLocation("block/water_still");
    public static final ResourceLocation FLOWING_OIL_TEXTURE = new ResourceLocation("block/water_flow");

    public static final DeferredRegister<Fluid> FLUIDS = new DeferredRegister<>(ForgeRegistries.FLUIDS, "CultureCraft");
    public static RegistryObject<FlowingFluid> obsidianFluid = FLUIDS.register("null", () -> {
        return new ForgeFlowingFluid.Source(RegistryFluid.PROPERTIES);
    });
    public static RegistryObject<FlowingFluid> obsidianFluidFlowing = FLUIDS.register("null", () -> {
        return new ForgeFlowingFluid.Flowing(RegistryFluid.PROPERTIES);
    });
    public static ForgeFlowingFluid.Properties PROPERTIES = new ForgeFlowingFluid.Properties(obsidianFluid, obsidianFluidFlowing, FluidAttributes.builder(STILL_OIL_TEXTURE, FLOWING_OIL_TEXTURE).color(0x311cbb).density(10)).bucket((Supplier<? extends Item>) RegistryItem.ITEMS.get("ceramicbowl")).block((Supplier<? extends FlowingFluidBlock>) RegistryBlock.BLOCKS.get("null")).slopeFindDistance(3).explosionResistance(100F);
}
//拉过去      、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、继续拉、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、这里先桶、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、、流体方块