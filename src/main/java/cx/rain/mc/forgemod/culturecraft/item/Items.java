package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.enumerate.PlantType;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ITEMS, CultureCraft.MODID);

    public static RegistryObject<Item> CHILI_PEPPER_SEED = REGISTRY.register("chili_pepper_seed", () -> new ItemSeed(Blocks.CHILI_PEPPER_PLANT.get(), PlantType.CHILI_PEPPER));
    public static RegistryObject<Item> GREEN_PEPPER_SEED = REGISTRY.register("green_pepper_seed", () -> new ItemSeed(Blocks.GREEN_PEPPER_PLANT.get(), PlantType.GREEN_PEPPER));
    public static RegistryObject<Item> EGGPLANT_SEED = REGISTRY.register("eggplant_seed", () -> new ItemSeed(Blocks.EGGPLANT_PLANT.get(), PlantType.EGGPLANT));
    public static RegistryObject<Item> CABBAGE_SEED = REGISTRY.register("cabbage_seed", () -> new ItemSeed(Blocks.CABBAGE_PLANT.get(), PlantType.CABBAGE));
    public static RegistryObject<Item> RICE_SEED = REGISTRY.register("rice_seed", () -> new ItemSeed(Blocks.RICE_PLANT.get(), PlantType.RICE));
    public static RegistryObject<Item> MILLET_SEED = REGISTRY.register("millet_seed", () -> new ItemSeed(Blocks.MILLET_PLANT.get(), PlantType.MILLET));
    public static RegistryObject<Item> SOYBEAN_SEED = REGISTRY.register("soybean_seed", () -> new ItemSeed(Blocks.SOYBEAN_PLANT.get(), PlantType.SOYBEAN));

    public static RegistryObject<Item> PEACH = REGISTRY.register("peach", () -> new ItemFood(new Food.Builder().hunger(2).saturation(3).fastToEat().build()));

    public Items(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
