package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ICookingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISteamerRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    static final IRecipeType<ISoakingRecipe> SOAKING = IRecipeType.register("sinocraft:soaking");
    static final IRecipeType<ICookingRecipe> COOKING = IRecipeType.register("sinocraft:cooking");
    static final IRecipeType<ISteamerRecipe> STEAMER = IRecipeType.register("sinocraft:steamer");

    private static final DeferredRegister<IRecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    static final RegistryObject<SoakingSerializer> SOAKING_SERIALIZER = REGISTRY.register("soaking", () -> SoakingSerializer.SERIALIZER);
    static final RegistryObject<CookingSerializer> COOKING_SERIALIZER = REGISTRY.register("cooking", () -> CookingSerializer.SERIALIZER);
    static final RegistryObject<SteamerSerializer> STEAMER_SERIALIZER = REGISTRY.register("steamer", () -> SteamerSerializer.SERIALIZER);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        REGISTRY.register(bus);
    }
}
