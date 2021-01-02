package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlockItems {
    public static final DeferredRegister<Item> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ITEMS, SinoCraft.MODID);

    public static RegistryObject<Item> WHITE_RADISH = REGISTRY.register("white_radish", () -> new BlockNamedItem(ModBlocks.WHITE_RADISH_PLANT.get(), new Item.Properties().group(ModGroups.FOODS).food(new Food.Builder().hunger(3).saturation(4).build()).setNoRepair()));
    public static RegistryObject<Item> SUMMER_RADISH = REGISTRY.register("summer_radish", () -> new BlockNamedItem(ModBlocks.SUMMER_RADISH_PLANT.get(), new Item.Properties().group(ModGroups.FOODS).food(new Food.Builder().hunger(2).saturation(3).build()).setNoRepair()));
    public static RegistryObject<Item> GREEN_RADISH = REGISTRY.register("green_radish", () -> new BlockNamedItem(ModBlocks.GREEN_RADISH_PLANT.get(), new Item.Properties().group(ModGroups.FOODS).food(new Food.Builder().hunger(2).saturation(5).build()).setNoRepair()));

    public static RegistryObject<Item> LOG_PEACH = REGISTRY.register("log_peach", () -> new BlockItem(ModBlocks.LOG_PEACH.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_BARK = REGISTRY.register("log_peach_bark", () -> new BlockItem(ModBlocks.LOG_PEACH_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED = REGISTRY.register("log_peach_stripped", () -> new BlockItem(ModBlocks.LOG_PEACH_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_PEACH_STRIPPED_BARK = REGISTRY.register("log_peach_stripped_bark", () -> new BlockItem(ModBlocks.LOG_PEACH_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> PLANK_PEACH = REGISTRY.register("plank_peach", () -> new BlockItem(ModBlocks.PLANK_PEACH.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PEACH = REGISTRY.register("leaves_peach", () -> new BlockItem(ModBlocks.LEAVES_PEACH.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_PEACH = REGISTRY.register("sapling_peach", () -> new BlockItem(ModBlocks.SAPLING_PEACH.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<Item> LOG_WALNUT = REGISTRY.register("log_walnut", () -> new BlockItem(ModBlocks.LOG_WALNUT.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_BARK = REGISTRY.register("log_walnut_bark", () -> new BlockItem(ModBlocks.LOG_WALNUT_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED = REGISTRY.register("log_walnut_stripped", () -> new BlockItem(ModBlocks.LOG_WALNUT_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_WALNUT_STRIPPED_BARK = REGISTRY.register("log_walnut_stripped_bark", () -> new BlockItem(ModBlocks.LOG_WALNUT_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> PLANK_WALNUT = REGISTRY.register("plank_walnut", () -> new BlockItem(ModBlocks.PLANK_WALNUT.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_WALNUT = REGISTRY.register("leaves_walnut", () -> new BlockItem(ModBlocks.LEAVES_WALNUT.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_WALNUT = REGISTRY.register("sapling_walnut", () -> new BlockItem(ModBlocks.SAPLING_WALNUT.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<Item> LOG_PLUM = REGISTRY.register("log_plum", () -> new BlockItem(ModBlocks.LOG_PLUM.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_BARK = REGISTRY.register("log_plum_bark", () -> new BlockItem(ModBlocks.LOG_PLUM_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_STRIPPED = REGISTRY.register("log_plum_stripped", () -> new BlockItem(ModBlocks.LOG_PLUM_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_PLUM_STRIPPED_BARK = REGISTRY.register("log_plum_stripped_bark", () -> new BlockItem(ModBlocks.LOG_PLUM_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> PLANK_PLUM = REGISTRY.register("plank_plum", () -> new BlockItem(ModBlocks.PLANK_PLUM.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_PLUM = REGISTRY.register("leaves_plum", () -> new BlockItem(ModBlocks.LEAVES_PLUM.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_PLUM = REGISTRY.register("sapling_plum", () -> new BlockItem(ModBlocks.SAPLING_PLUM.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<Item> LOG_MULBERRY = REGISTRY.register("log_mulberry", () -> new BlockItem(ModBlocks.LOG_MULBERRY.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_BARK = REGISTRY.register("log_mulberry_bark", () -> new BlockItem(ModBlocks.LOG_MULBERRY_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_STRIPPED = REGISTRY.register("log_mulberry_stripped", () -> new BlockItem(ModBlocks.LOG_MULBERRY_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LOG_MULBERRY_STRIPPED_BARK = REGISTRY.register("log_mulberry_stripped_bark", () -> new BlockItem(ModBlocks.LOG_MULBERRY_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> PLANK_MULBERRY = REGISTRY.register("plank_mulberry", () -> new BlockItem(ModBlocks.PLANK_MULBERRY.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> LEAVES_MULBERRY = REGISTRY.register("leaves_mulberry", () -> new BlockItem(ModBlocks.LEAVES_MULBERRY.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> SAPLING_MULBERRY = REGISTRY.register("sapling_mulberry", () -> new BlockItem(ModBlocks.SAPLING_MULBERRY.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<Item> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockItem(ModBlocks.RED_MARBLE.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties().group(ModGroups.BLOCKS)));

    public static RegistryObject<Item> VAT = REGISTRY.register("vat", () -> new BlockItem(ModBlocks.VAT.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> STONE_MILL = REGISTRY.register("stone_mill", () -> new BlockItem(ModBlocks.STONE_MILL.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", () -> new BlockItem(ModBlocks.PAPER_DRYING_RACK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<Item> STOVE = REGISTRY.register("stove", () -> new BlockItem(ModBlocks.STOVE.get(), new Item.Properties().group(ModGroups.BLOCKS)));

    public ModBlockItems(IEventBus bus) {
        SinoCraft.getInstance().getLog().info("Registering block items.");
        REGISTRY.register(bus);
    }
}
