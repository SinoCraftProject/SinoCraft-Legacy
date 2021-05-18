package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.crafting.*;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes implements IModRecipes {
    private static final IRecipeType<ISoakingRecipe> SOAKING = IRecipeType.register("sinocraft:soaking");
    private static final IRecipeType<ICookingRecipe> COOKING = IRecipeType.register("sinocraft:cooking");
    private static final IRecipeType<ISteamerRecipe> STEAMER = IRecipeType.register("sinocraft:steamer");

    private static final DeferredRegister<IRecipeSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, SinoCraft.MODID);

    private static final RegistryObject<SoakingSerializer> SOAKING_SERIALIZER = REGISTRY.register("soaking", () -> SoakingSerializer.SERIALIZER);
    private static final RegistryObject<CookingSerializer> COOKING_SERIALIZER = REGISTRY.register("cooking", () -> CookingSerializer.SERIALIZER);
    private static final RegistryObject<SteamerSerializer> STEAMER_SERIALIZER = REGISTRY.register("steamer", () -> SteamerSerializer.SERIALIZER);

    public ModRecipes(IEventBus bus) {
        SinoCraft.getLogger().info("Registering recipes.");
        REGISTRY.register(bus);

        IModRecipes.INSTANCE.set(this);
    }

    @Override
    public IRecipeType<ISoakingRecipe> getSoakingRecipe() {
        return SOAKING;
    }

    @Override
    public IRecipeType<ICookingRecipe> getCookingRecipe() {
        return COOKING;
    }

    @Override
    public IRecipeType<ISteamerRecipe> getSteamerRecipe() {
        return STEAMER;
    }

    @Override
    public IModRecipeSerializer<? extends ISoakingRecipe> getSoakingSerializer() {
        return SOAKING_SERIALIZER.get();
    }

    @Override
    public IModRecipeSerializer<? extends ICookingRecipe> getCookingSerializer() {
        return COOKING_SERIALIZER.get();
    }

    @Override
    public IModRecipeSerializer<? extends ISteamerRecipe> getSteamerSerializer() {
        return STEAMER_SERIALIZER.get();
    }

    @Override
    public ICookingRecipeBuilder newCookingRecipe(ResourceLocation id) {
        return CookingRecipe.builder(id);
    }

    @Override
    public ISoakingRecipeBuilder newSoakingRecipe(ResourceLocation id) {
        return SoakingRecipe.builder(id);
    }

    @Override
    public ISteamerRecipeBuilder newSteamerRecipe(ResourceLocation id) {
        return SteamerRecipe.builder(id);
    }

    @Override
    public ICountIngredient newCountIngredient(Ingredient ingredient, int count) {
        return new CountIngredient(ingredient, count);
    }

    @Override
    public IFluidIngredient newFluidIngredient(ResourceLocation fluidTag, int amount) {
        return new FluidIngredient(fluidTag, amount);
    }

    @Override
    public IFluidIngredient newFluidIngredient(Fluid fluid, int amount) {
        return new FluidIngredient(fluid, amount);
    }
}
