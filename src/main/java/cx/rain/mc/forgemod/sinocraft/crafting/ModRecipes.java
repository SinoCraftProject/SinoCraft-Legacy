package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.crafting.potcooking.PotCookingSerializer;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.ISoakingRecipe;
import cx.rain.mc.forgemod.sinocraft.crafting.soaking.SoakingRecipeSerializer;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {
    public static final IRecipeType<ISoakingRecipe> SOAKING = IRecipeType.register(modRecipeType("soaking"));

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    public static final RegistryObject<SoakingRecipeSerializer> SOAKING_SERIALIZER = RECIPES.register("soaking", SoakingRecipeSerializer::new);
    public static final RegistryObject<PotCookingSerializer> POT_COOKING_SERIALIZER = RECIPES.register("pot_cooking", PotCookingSerializer::new);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        RECIPES.register(bus);
    }

    public static String modRecipeType(String type) {
        return SinoCraft.MODID + ":" + type;
    }
}
