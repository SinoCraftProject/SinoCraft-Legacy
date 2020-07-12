package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class BlockItems {
    public static final DeferredRegister<Item> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ITEMS, CultureCraft.MODID);

    public static RegistryObject<Item> RADISH_WHITE =
            REGISTRY.register("radish_white", () -> new BlockItem(Blocks.RADISH_WHITE_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(3).saturation(4).build())
                            .setNoRepair()));
    public static RegistryObject<Item> RADISH_SUMMER =
            REGISTRY.register("radish_summer", () -> new BlockItem(Blocks.RADISH_SUMMER_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(2).saturation(3).build())
                            .setNoRepair()));
    public static RegistryObject<Item> RADISH_GREEN =
            REGISTRY.register("radish_green", () -> new BlockItem(Blocks.RADISH_GREEN_PLANT.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(2).saturation(5).build())
                            .setNoRepair()));

    public static RegistryObject<Item> LOG_PEACH =
            REGISTRY.register("log_peach", () -> new BlockItem(Blocks.LOG_PEACH.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_SKIN =
            REGISTRY.register("log_peach_skin", () -> new BlockItem(Blocks.LOG_PEACH_SKIN.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED =
            REGISTRY.register("log_peach_stripped", () -> new BlockItem(Blocks.LOG_PEACH_STRIPPED.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED_SKIN =
            REGISTRY.register("log_peach_stripped_skin", () ->
                    new BlockItem(Blocks.LOG_PEACH_STRIPPED_SKIN.get(),
                            new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PEACH =
            REGISTRY.register("leaves_peach", () -> new BlockItem(Blocks.LEAVES_PEACH.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_PEACH =
            REGISTRY.register("sapling_peach", () -> new BlockItem(Blocks.SAPLING_PEACH.get(),
                    new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> LOG_WALNUT =
            REGISTRY.register("log_walnut", () -> new BlockItem(Blocks.LOG_WALNUT.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_SKIN =
            REGISTRY.register("log_walnut_skin", () -> new BlockItem(Blocks.LOG_WALNUT_SKIN.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED =
            REGISTRY.register("log_walnut_stripped", () -> new BlockItem(Blocks.LOG_WALNUT_STRIPPED.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED_SKIN =
            REGISTRY.register("log_walnut_stripped_skin", () ->
                    new BlockItem(Blocks.LOG_WALNUT_STRIPPED_SKIN.get(),
                            new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_WALNUT =
            REGISTRY.register("leaves_walnut", () -> new BlockItem(Blocks.LEAVES_WALNUT.get(),
                    new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_WALNUT =
            REGISTRY.register("sapling_walnut", () -> new BlockItem(Blocks.SAPLING_WALNUT.get(),
                    new Item.Properties().group(Groups.DECORATE)));

    public BlockItems(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering block items.");
        REGISTRY.register(bus);
    }
}
