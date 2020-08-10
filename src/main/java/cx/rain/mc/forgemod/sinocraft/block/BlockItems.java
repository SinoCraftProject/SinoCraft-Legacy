package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.group.Groups;
import net.minecraft.block.Block;
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
            new DeferredRegister<>(ForgeRegistries.ITEMS, SinoCraft.MODID);

    public static RegistryObject<Item> WHITE_RADISH = REGISTRY.register("white_radish", () -> new BlockNamedItem(Blocks.WHITE_RADISH_PLANT.get(), new Item.Properties().group(Groups.FOODS).food(new Food.Builder().hunger(3).saturation(4).build()).setNoRepair()));
    public static RegistryObject<Item> SUMMER_RADISH = REGISTRY.register("summer_radish", () -> new BlockNamedItem(Blocks.SUMMER_RADISH_PLANT.get(), new Item.Properties().group(Groups.FOODS).food(new Food.Builder().hunger(2).saturation(3).build()).setNoRepair()));
    public static RegistryObject<Item> GREEN_RADISH = REGISTRY.register("green_radish", () -> new BlockNamedItem(Blocks.GREEN_RADISH_PLANT.get(), new Item.Properties().group(Groups.FOODS).food(new Food.Builder().hunger(2).saturation(5).build()).setNoRepair()));

    public static RegistryObject<Item> LOG_PEACH = REGISTRY.register("log_peach", () -> new BlockItem(Blocks.LOG_PEACH.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_BARK = REGISTRY.register("log_peach_bark", () -> new BlockItem(Blocks.LOG_PEACH_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED = REGISTRY.register("log_peach_stripped", () -> new BlockItem(Blocks.LOG_PEACH_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED_BARK = REGISTRY.register("log_peach_stripped_bark", () -> new BlockItem(Blocks.LOG_PEACH_STRIPPED_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_PEACH = REGISTRY.register("plank_peach", () -> new BlockItem(Blocks.PLANK_PEACH.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PEACH = REGISTRY.register("leaves_peach", () -> new BlockItem(Blocks.LEAVES_PEACH.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_PEACH = REGISTRY.register("sapling_peach", () -> new BlockItem(Blocks.SAPLING_PEACH.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> LOG_WALNUT = REGISTRY.register("log_walnut", () -> new BlockItem(Blocks.LOG_WALNUT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_BARK = REGISTRY.register("log_walnut_bark", () -> new BlockItem(Blocks.LOG_WALNUT_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED = REGISTRY.register("log_walnut_stripped", () -> new BlockItem(Blocks.LOG_WALNUT_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED_BARK = REGISTRY.register("log_walnut_stripped_bark", () -> new BlockItem(Blocks.LOG_WALNUT_STRIPPED_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_WALNUT = REGISTRY.register("plank_walnut", () -> new BlockItem(Blocks.PLANK_WALNUT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_WALNUT = REGISTRY.register("leaves_walnut", () -> new BlockItem(Blocks.LEAVES_WALNUT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_WALNUT = REGISTRY.register("sapling_walnut", () -> new BlockItem(Blocks.SAPLING_WALNUT.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> LOG_PLUM = REGISTRY.register("log_plum", () -> new BlockItem(Blocks.LOG_PLUM.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_BARK = REGISTRY.register("log_plum_bark", () -> new BlockItem(Blocks.LOG_PLUM_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_STRIPPED = REGISTRY.register("log_plum_stripped", () -> new BlockItem(Blocks.LOG_PLUM_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_STRIPPED_BARK = REGISTRY.register("log_plum_stripped_bark", () -> new BlockItem(Blocks.LOG_PLUM_STRIPPED_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_PLUM = REGISTRY.register("plank_plum", () -> new BlockItem(Blocks.PLANK_PLUM.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PLUM = REGISTRY.register("leaves_plum", () -> new BlockItem(Blocks.LEAVES_PLUM.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_PLUM = REGISTRY.register("sapling_plum", () -> new BlockItem(Blocks.SAPLING_PLUM.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> LOG_MULBERRY = REGISTRY.register("log_mulberry", () -> new BlockItem(Blocks.LOG_MULBERRY.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_BARK = REGISTRY.register("log_mulberry_bark", () -> new BlockItem(Blocks.LOG_MULBERRY_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_STRIPPED = REGISTRY.register("log_mulberry_stripped", () -> new BlockItem(Blocks.LOG_MULBERRY_STRIPPED.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_STRIPPED_BARK = REGISTRY.register("log_mulberry_stripped_bark", () -> new BlockItem(Blocks.LOG_MULBERRY_STRIPPED_BARK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PLANK_MULBERRY = REGISTRY.register("plank_mulberry", () -> new BlockItem(Blocks.PLANK_MULBERRY.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_MULBERRY = REGISTRY.register("leaves_mulberry", () -> new BlockItem(Blocks.LEAVES_MULBERRY.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_MULBERRY = REGISTRY.register("sapling_mulberry", () -> new BlockItem(Blocks.SAPLING_MULBERRY.get(), new Item.Properties().group(Groups.DECORATE)));

    public static RegistryObject<Item> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockItem(Blocks.WHITE_MARBLE.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockItem(Blocks.RED_MARBLE.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockItem(Blocks.BLACK_MARBLE.get(), new Item.Properties().group(Groups.BLOCKS)));

    public static RegistryObject<Item> VAT = REGISTRY.register("vat", () -> new BlockItem(Blocks.VAT.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> STONE_MILL = REGISTRY.register("stone_mill", () -> new BlockItem(Blocks.STONE_MILL.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", () -> new BlockItem(Blocks.PAPER_DRYING_RACK.get(), new Item.Properties().group(Groups.BLOCKS)));
    public static RegistryObject<Item> STOVE = REGISTRY.register("stove", () -> new BlockItem(Blocks.STOVE.get(), new Item.Properties().group(Groups.BLOCKS)));

    public BlockItems(IEventBus bus) {
        SinoCraft.getInstance().getLog().info("Registering block items.");
        REGISTRY.register(bus);
    }
}
