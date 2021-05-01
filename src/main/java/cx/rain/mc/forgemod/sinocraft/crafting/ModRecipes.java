package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.IronPotSerializer;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.SoakingSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final IRecipeType<ISoakingRecipe> SOAK = IRecipeType.register(SinoCraft.MODID + "soaking");

    public static final DeferredRegister<IRecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    public static final RegistryObject<SoakingSerializer> SOAKING_SERIALIZER = REGISTRY.register("soaking", SoakingSerializer::new);
    public static final RegistryObject<IronPotSerializer> IRON_POT_SERIALIZER = REGISTRY.register("iron_pot", IronPotSerializer::new);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        REGISTRY.register(bus);
    }
}
