package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.IMillRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.cooking.CookingSerializer;
import cx.rain.mc.forgemod.sinocraft.crafting.grinding.GrindingSerializer;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.SoakingSerializer;
import cx.rain.mc.forgemod.sinocraft.crafting.steaming.SteamingSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final IRecipeType<ISoakingRecipe> SOAKING = IRecipeType.register("sinocraft:soaking");
    public static final IRecipeType<ICookingRecipe> COOKING = IRecipeType.register("sinocraft:cooking");
    public static final IRecipeType<ISteamerRecipe> STEAMER = IRecipeType.register("sinocraft:steamer");
    public static final IRecipeType<IMillRecipe> MILL = IRecipeType.register("sinocraft:mill");

    private static final DeferredRegister<IRecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    public static final RegistryObject<SoakingSerializer> SOAKING_SERIALIZER = REGISTRY.register("soaking", () -> SoakingSerializer.SERIALIZER);
    public static final RegistryObject<CookingSerializer> COOKING_SERIALIZER = REGISTRY.register("cooking", () -> CookingSerializer.SERIALIZER);
    public static final RegistryObject<SteamingSerializer> STEAMER_SERIALIZER = REGISTRY.register("steamer", () -> SteamingSerializer.SERIALIZER);
    public static final RegistryObject<GrindingSerializer> MILL_SERIALIZER = REGISTRY.register("mill", () -> GrindingSerializer.SERIALIZER);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        REGISTRY.register(bus);
    }
}
