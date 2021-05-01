package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.IronPotRecipes;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ironpot.IronPotSerializer;
import cx.rain.mc.forgemod.sinocraft.api.crafting.vat.ISoakRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.vat.SoakRecipeSerializer;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.registry.Registry;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final IRecipeType<ISoakRecipe> SOAK = IRecipeType.register("sinocraft:soak");

    public static final DeferredRegister<IRecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    public static final RegistryObject<SoakRecipeSerializer> SOAK_SERIALIZER = REGISTRY.register("soak", SoakRecipeSerializer::new);
    public static final RegistryObject<IronPotSerializer> IRON_POT_SERIALIZER = REGISTRY.register("iron_pot", IronPotSerializer::new);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        REGISTRY.register(bus);
    }
}
