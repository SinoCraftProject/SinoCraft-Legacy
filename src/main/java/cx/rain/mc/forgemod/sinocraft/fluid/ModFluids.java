package cx.rain.mc.forgemod.sinocraft.fluid;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fluids.FluidAttributes;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModFluids {
    public static final DeferredRegister<Fluid> REGISTRY = DeferredRegister.create(ForgeRegistries.FLUIDS, SinoCraft.MODID);
    public static RegistryObject<FlowingFluid> WOOD_PULP = REGISTRY.register("wood_pulp", () -> new ForgeFlowingFluid.Source(ModFluids.WOOD_PULP_PROPERTIES));
    public static RegistryObject<FlowingFluid> WOOD_PULP_FLOWING = REGISTRY.register("wood_pulp_flowing", () -> new ForgeFlowingFluid.Flowing(ModFluids.WOOD_PULP_PROPERTIES));
    public static ForgeFlowingFluid.Properties WOOD_PULP_PROPERTIES = new ForgeFlowingFluid.Properties(WOOD_PULP, WOOD_PULP_FLOWING, FluidAttributes.builder(new ResourceLocation(SinoCraft.MODID,"block/wood_pulp_still"), new ResourceLocation(SinoCraft.MODID,"block/wood_pulp_flow")).color(0xFFBC8129).density(10)).bucket(ModItems.BUCKET_WOOD_PULP).block(()-> (FlowingFluidBlock) ModBlocks.WOOD_PULP_BLOCK.get()).slopeFindDistance(3).explosionResistance(100F);

    public ModFluids(IEventBus bus) {
        SinoCraft.getLogger().info("Registering fluids.");
        REGISTRY.register(bus);
    }
}