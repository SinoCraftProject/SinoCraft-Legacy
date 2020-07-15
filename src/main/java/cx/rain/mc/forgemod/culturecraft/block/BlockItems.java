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

    public static RegistryObject<Item> WHITE_RADISH = REGISTRY.register("radish_white", () -> new BlockNamedItem(Blocks.WHITE_RADISH_PLANT.get(), new Item.Properties().group(Groups.FOODS).food(new Food.Builder().hunger(3).saturation(4).build()).setNoRepair()));
    public static RegistryObject<Item> SUMMER_RADISH = REGISTRY.register("radish_summer", () -> new BlockNamedItem(Blocks.SUMMER_RADISH_PLANT.get(), new Item.Properties().group(Groups.FOODS).food(new Food.Builder().hunger(2).saturation(3).build()).setNoRepair()));
    public static RegistryObject<Item> GREEN_RADISH = REGISTRY.register("radish_green", () -> new BlockNamedItem(Blocks.GREEN_RADISH_PLANT.get(), new Item.Properties().group(Groups.FOODS).food(new Food.Builder().hunger(2).saturation(5).build()).setNoRepair()));

    public static RegistryObject<Item> LOG_PEACH = REGISTRY.register("log_peach", () -> new BlockItem(Blocks.LOG_PEACH.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_SKIN = REGISTRY.register("log_peach_skin", () -> new BlockItem(Blocks.LOG_PEACH_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED = REGISTRY.register("log_peach_stripped", () -> new BlockItem(Blocks.LOG_PEACH_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED_SKIN = REGISTRY.register("log_peach_stripped_skin", () -> new BlockItem(Blocks.LOG_PEACH_STRIPPED_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_PEACH = REGISTRY.register("plank_peach", () -> new BlockItem(Blocks.PLANK_PEACH.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PEACH = REGISTRY.register("leaves_peach", () -> new BlockItem(Blocks.LEAVES_PEACH.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_PEACH = REGISTRY.register("sapling_peach", () -> new BlockItem(Blocks.SAPLING_PEACH.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> LOG_WALNUT = REGISTRY.register("log_walnut", () -> new BlockItem(Blocks.LOG_WALNUT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_SKIN = REGISTRY.register("log_walnut_skin", () -> new BlockItem(Blocks.LOG_WALNUT_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED = REGISTRY.register("log_walnut_stripped", () -> new BlockItem(Blocks.LOG_WALNUT_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED_SKIN = REGISTRY.register("log_walnut_stripped_skin", () -> new BlockItem(Blocks.LOG_WALNUT_STRIPPED_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_WALNUT = REGISTRY.register("plank_walnut", () -> new BlockItem(Blocks.PLANK_WALNUT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_WALNUT = REGISTRY.register("leaves_walnut", () -> new BlockItem(Blocks.LEAVES_WALNUT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_WALNUT = REGISTRY.register("sapling_walnut", () -> new BlockItem(Blocks.SAPLING_WALNUT.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> LOG_PLUM = REGISTRY.register("log_plum", () -> new BlockItem(Blocks.LOG_PLUM.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_SKIN = REGISTRY.register("log_plum_skin", () -> new BlockItem(Blocks.LOG_PLUM_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_STRIPPED = REGISTRY.register("log_plum_stripped", () -> new BlockItem(Blocks.LOG_PLUM_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_STRIPPED_SKIN = REGISTRY.register("log_plum_stripped_skin", () -> new BlockItem(Blocks.LOG_PLUM_STRIPPED_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_PLUM = REGISTRY.register("plank_plum", () -> new BlockItem(Blocks.PLANK_PLUM.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PLUM = REGISTRY.register("leaves_plum", () -> new BlockItem(Blocks.LEAVES_PLUM.get(), new Item.Properties().group(Groups.BLOCKS)));
<<<<<<< HEAD
    public static RegistryObject<Item> SAPLING_PLUM = REGISTRY.register("sapling_plum", () -> new BlockItem(Blocks.SAPLING_PLUM.get(), new Item.Properties().group(Groups.DECORATE)));
=======
    // Todo
    //public static RegistryObject<Item> SAPLING_PLUM = REGISTRY.register("sapling_plum", () -> new BlockItem(Blocks.SAPLING_PLUM.get(), new Item.Properties().group(Groups.DECORATE)));
>>>>>>> 2a54515cac3df260d16a024949eed452833a9eda

    public static RegistryObject<Item> LOG_MULBERRY = REGISTRY.register("log_mulberry", () -> new BlockItem(Blocks.LOG_MULBERRY.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_SKIN = REGISTRY.register("log_mulberry_skin", () -> new BlockItem(Blocks.LOG_MULBERRY_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_STRIPPED = REGISTRY.register("log_mulberry_stripped", () -> new BlockItem(Blocks.LOG_MULBERRY_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_STRIPPED_SKIN = REGISTRY.register("log_mulberry_stripped_skin", () -> new BlockItem(Blocks.LOG_MULBERRY_STRIPPED_SKIN.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_MULBERRY = REGISTRY.register("plank_mulberry", () -> new BlockItem(Blocks.PLANK_MULBERRY.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_MULBERRY = REGISTRY.register("leaves_mulberry", () -> new BlockItem(Blocks.LEAVES_MULBERRY.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_MULBERRY = REGISTRY.register("sapling_mulberry", () -> new BlockItem(Blocks.SAPLING_MULBERRY.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockItem(Blocks.WHITE_MARBLE.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockItem(Blocks.RED_MARBLE.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockItem(Blocks.BLACK_MARBLE.get(), new Item.Properties().group(Groups.BLOCKS)));

    public BlockItems(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering block items.");
        REGISTRY.register(bus);
    }
}
