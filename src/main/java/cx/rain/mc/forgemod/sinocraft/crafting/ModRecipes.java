package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.cooking.CookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.vat.ISoakRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.cooking.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.vat.SoakRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final IRecipeType<ISoakRecipe> SOAK = IRecipeType.register("sinocraft:soak");
    public static final IRecipeType<ICookingRecipe> COOKING = IRecipeType.register("sinocraft:cooking");

    public static final DeferredRegister<IRecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    public static final RegistryObject<SoakRecipe.Serializer> SOAK_SERIALIZER = REGISTRY.register("soak", SoakRecipe.Serializer::new);
    public static final RegistryObject<CookingRecipe.Serializer> COOKING_SERIALIZER = REGISTRY.register("cooking", CookingRecipe.Serializer::new);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        REGISTRY.register(bus);
    }
}
