package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ITEMS, CultureCraft.MODID);

    public static RegistryObject<Item> RADISH_WHITE =
            REGISTRY.register("radish_white", () -> new BlockNamedItem(Blocks.RADISH_WHITE_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(3).saturation(4).build())
                            .setNoRepair()));

    public static RegistryObject<Item> RADISH_SUMMER =
            REGISTRY.register("radish_summer", () -> new BlockNamedItem(Blocks.RADISH_SUMMER_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(2).saturation(3).build())
                            .setNoRepair()));

    public static RegistryObject<Item> RADISH_GREEN =
            REGISTRY.register("radish_green", () -> new BlockNamedItem(Blocks.RADISH_GREEN_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(2).saturation(5).build())
                            .setNoRepair()));


    public static RegistryObject<Item> SEED_CHILIPEPPER =
            REGISTRY.register("pepper_chili_seed", () -> new BlockNamedItem(Blocks.PEPPER_CHILI_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.ITEMS)
                            .setNoRepair()));

    public static RegistryObject<Item> SEED_GREENPEPPER =
            REGISTRY.register("pepper_green_seed", () -> new BlockNamedItem(Blocks.PEPPER_GREEN_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.ITEMS)
                            .setNoRepair()));

    public static RegistryObject<Item> RICE =
            REGISTRY.register("rice", () -> new BlockNamedItem(Blocks.RICE.get(),
                    new Item.Properties()
                            .food(new Food.Builder().hunger(1).saturation(2).build())
                            .group(Groups.FOODS)
                            .setNoRepair()));

    public static RegistryObject<Item> EGGPLANT =
            REGISTRY.register("eggplant", () -> new BlockNamedItem(Blocks.EGGPLANT_PLANT.get(),
                    new Item.Properties()
                            .food(new Food.Builder().hunger(2).saturation(2).build())
                            .group(Groups.FOODS)
                            .setNoRepair()));

    public Items(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
