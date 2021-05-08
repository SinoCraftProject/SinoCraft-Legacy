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

    public static RegistryObject<BlockItem> WHITE_RADISH = REGISTRY.register("white_radish", () -> new BlockNamedItem(ModBlocks.WHITE_RADISH_PLANT.get(), new Item.Properties().group(ModGroups.FOODS).food(new Food.Builder().hunger(3).saturation(4).build()).setNoRepair()));
    public static RegistryObject<BlockItem> SUMMER_RADISH = REGISTRY.register("summer_radish", () -> new BlockNamedItem(ModBlocks.SUMMER_RADISH_PLANT.get(), new Item.Properties().group(ModGroups.FOODS).food(new Food.Builder().hunger(2).saturation(3).build()).setNoRepair()));
    public static RegistryObject<BlockItem> GREEN_RADISH = REGISTRY.register("green_radish", () -> new BlockNamedItem(ModBlocks.GREEN_RADISH_PLANT.get(), new Item.Properties().group(ModGroups.FOODS).food(new Food.Builder().hunger(2).saturation(5).build()).setNoRepair()));

    public static RegistryObject<BlockItem> PEACH_LOG = REGISTRY.register("peach_log", () -> new BlockItem(ModBlocks.PEACH_LOG.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PEACH_LOG_BARK = REGISTRY.register("peach_log_bark", () -> new BlockItem(ModBlocks.PEACH_LOG_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PEACH_LOG_STRIPPED = REGISTRY.register("peach_log_stripped", () -> new BlockItem(ModBlocks.PEACH_LOG_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PEACH_LOG_STRIPPED_BARK = REGISTRY.register("peach_log_stripped_bark", () -> new BlockItem(ModBlocks.PEACH_LOG_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PEACH_PLANK = REGISTRY.register("peach_plank", () -> new BlockItem(ModBlocks.PEACH_PLANK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PEACH_LEAVES = REGISTRY.register("peach_leaves", () -> new BlockItem(ModBlocks.PEACH_LEAVES.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PEACH_SAPLING = REGISTRY.register("peach_sapling", () -> new BlockItem(ModBlocks.PEACH_SAPLING.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<BlockItem> WALNUT_LOG = REGISTRY.register("walnut_log", () -> new BlockItem(ModBlocks.WALNUT_LOG.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> WALNUT_LOG_BARK = REGISTRY.register("walnut_log_bark", () -> new BlockItem(ModBlocks.WALNUT_LOG_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> WALNUT_LOG_STRIPPED = REGISTRY.register("walnut_log_stripped", () -> new BlockItem(ModBlocks.WALNUT_LOG_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> WALNUT_LOG_STRIPPED_BARK = REGISTRY.register("walnut_log_stripped_bark", () -> new BlockItem(ModBlocks.WALNUT_LOG_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> WALNUT_PLANK = REGISTRY.register("walnut_plank", () -> new BlockItem(ModBlocks.WALNUT_PLANK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> WALNUT_LEAVES = REGISTRY.register("walnut_leaves", () -> new BlockItem(ModBlocks.WALNUT_LEAVES.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> WALNUT_SAPLING = REGISTRY.register("walnut_sapling", () -> new BlockItem(ModBlocks.WALNUT_SAPLING.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<BlockItem> PLUM_LOG = REGISTRY.register("plum_log", () -> new BlockItem(ModBlocks.PLUM_LOG.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PLUM_LOG_BARK = REGISTRY.register("plum_log_bark", () -> new BlockItem(ModBlocks.PLUM_LOG_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PLUM_LOG_STRIPPED = REGISTRY.register("plum_log_stripped", () -> new BlockItem(ModBlocks.PLUM_LOG_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PLUM_LOG_STRIPPED_BARK = REGISTRY.register("plum_log_stripped_bark", () -> new BlockItem(ModBlocks.PLUM_LOG_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PLUM_PLANK = REGISTRY.register("plum_plank", () -> new BlockItem(ModBlocks.PLUM_PLANK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PLUM_LEAVES = REGISTRY.register("plum_leaves", () -> new BlockItem(ModBlocks.PLUM_LEAVES.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PLUM_SAPLING = REGISTRY.register("plum_sapling", () -> new BlockItem(ModBlocks.PLUM_SAPLING.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<BlockItem> MULBERRY_LOG = REGISTRY.register("mulberry_log", () -> new BlockItem(ModBlocks.MULBERRY_LOG.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> MULBERRY_LOG_BARK = REGISTRY.register("mulberry_log_bark", () -> new BlockItem(ModBlocks.MULBERRY_LOG_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> MULBERRY_LOG_STRIPPED = REGISTRY.register("mulberry_log_stripped", () -> new BlockItem(ModBlocks.MULBERRY_LOG_STRIPPED.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> MULBERRY_LOG_STRIPPED_BARK = REGISTRY.register("mulberry_log_stripped_bark", () -> new BlockItem(ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> MULBERRY_PLANK = REGISTRY.register("mulberry_plank", () -> new BlockItem(ModBlocks.MULBERRY_PLANK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> MULBERRY_LEAVES = REGISTRY.register("mulberry_leaves", () -> new BlockItem(ModBlocks.MULBERRY_LEAVES.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> MULBERRY_SAPLING = REGISTRY.register("mulberry_sapling", () -> new BlockItem(ModBlocks.MULBERRY_SAPLING.get(), new Item.Properties().group(ModGroups.DECORATE)));

    public static RegistryObject<BlockItem> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockItem(ModBlocks.RED_MARBLE.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties().group(ModGroups.BLOCKS)));

    public static RegistryObject<BlockItem> VAT = REGISTRY.register("vat", () -> new BlockItem(ModBlocks.VAT.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> STONE_MILL = REGISTRY.register("stone_mill", () -> new BlockItem(ModBlocks.STONE_MILL.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", () -> new BlockItem(ModBlocks.PAPER_DRYING_RACK.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> STOVE = REGISTRY.register("stove", () -> new BlockItem(ModBlocks.STOVE.get(), new Item.Properties().group(ModGroups.BLOCKS)));
    public static RegistryObject<BlockItem> POT = REGISTRY.register("pot", () -> new BlockItem(ModBlocks.POT.get(), new Item.Properties().group(ModGroups.BLOCKS)));

    public ModBlockItems(IEventBus bus) {
        SinoCraft.getLogger().info("Registering block items.");
        REGISTRY.register(bus);
    }
}
