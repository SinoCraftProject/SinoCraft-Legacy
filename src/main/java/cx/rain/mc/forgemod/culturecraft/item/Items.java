package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.item.BlockItem;
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


    public static RegistryObject<Item> PEPPER_CHILI_SEED =
            REGISTRY.register("pepper_chili_seed", () -> new BlockNamedItem(Blocks.PEPPER_CHILI_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public static RegistryObject<Item> PEPPER_GREEN_SEED =
            REGISTRY.register("pepper_green_seed", () -> new BlockNamedItem(Blocks.PEPPER_GREEN_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public static RegistryObject<Item> EGGPLANT_SEED =
            REGISTRY.register("eggplant_seed", () ->
                    new BlockNamedItem(RegistryBlock.BLOCKS.get("eggplant_plant"),
                            new Item.Properties()
                                    .group(Groups.MISC)
                                    .setNoRepair()));

    public static RegistryObject<Item> SEED_RICE =
            REGISTRY.register("seed_rice", () -> new BlockNamedItem(Blocks.RICE_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.MISC)
                            .setNoRepair()));

    public Items(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering items.");
        REGISTRY.register(bus);
    }
}
