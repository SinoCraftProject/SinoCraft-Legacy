package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.item.ItemTabs;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockItems {
    public static final DeferredRegister<Item> BLOCK_ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, SinoCraft.MODID);

    // Fixme: qyl: Foods saturationMod value is wrong. 2022/1/17.
    public static RegistryObject<ItemNameBlockItem> WHITE_RADISH = BLOCK_ITEMS.register("white_radish", () -> new ItemNameBlockItem(ModBlocks.WHITE_RADISH_PLANT.get(), new Item.Properties().tab(ItemTabs.AGRICULTURE).food(new FoodProperties.Builder().nutrition(3).saturationMod(4.0f).build()).setNoRepair()));
    public static RegistryObject<ItemNameBlockItem> SUMMER_RADISH = BLOCK_ITEMS.register("summer_radish", () -> new ItemNameBlockItem(ModBlocks.SUMMER_RADISH_PLANT.get(), new Item.Properties().tab(ItemTabs.AGRICULTURE).food(new FoodProperties.Builder().nutrition(2).saturationMod(3.0f).build()).setNoRepair()));
    public static RegistryObject<ItemNameBlockItem> GREEN_RADISH = BLOCK_ITEMS.register("green_radish", () -> new ItemNameBlockItem(ModBlocks.GREEN_RADISH_PLANT.get(), new Item.Properties().tab(ItemTabs.AGRICULTURE).food(new FoodProperties.Builder().nutrition(3).saturationMod(5.0f).build()).setNoRepair()));

//    public static RegistryObject<BlockItem> PEACH_LOG_STRIPPED = BLOCK_ITEMS.register("peach_log_stripped", () -> new BlockItem(ModBlocks.PEACH_LOG_STRIPPED.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PEACH_LOG = BLOCK_ITEMS.register("peach_log", () -> new BlockItem(ModBlocks.PEACH_LOG.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PEACH_LOG_STRIPPED_BARK = BLOCK_ITEMS.register("peach_log_stripped_bark", () -> new BlockItem(ModBlocks.PEACH_LOG_STRIPPED_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PEACH_LOG_BARK = BLOCK_ITEMS.register("peach_log_bark", () -> new BlockItem(ModBlocks.PEACH_LOG_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PEACH_PLANK = BLOCK_ITEMS.register("peach_plank", () -> new BlockItem(ModBlocks.PEACH_PLANK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//
//    public static RegistryObject<BlockItem> PEACH_LEAVES = BLOCK_ITEMS.register("peach_leaves", () -> new BlockItem(ModBlocks.PEACH_LEAVES.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_LEAVES = BLOCK_ITEMS.register("walnut_leaves", () -> new BlockItem(ModBlocks.WALNUT_LEAVES.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_LEAVES = BLOCK_ITEMS.register("plum_leaves", () -> new BlockItem(ModBlocks.PLUM_LEAVES.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_LEAVES = BLOCK_ITEMS.register("mulberry_leaves", () -> new BlockItem(ModBlocks.MULBERRY_LEAVES.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//
//    public static RegistryObject<BlockItem> PEACH_SAPLING = BLOCK_ITEMS.register("peach_sapling", () -> new BlockItem(ModBlocks.PEACH_SAPLING.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_LOG_STRIPPED = BLOCK_ITEMS.register("walnut_log_stripped", () -> new BlockItem(ModBlocks.WALNUT_LOG_STRIPPED.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_LOG = BLOCK_ITEMS.register("walnut_log", () -> new BlockItem(ModBlocks.WALNUT_LOG.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_LOG_STRIPPED_BARK = BLOCK_ITEMS.register("walnut_log_stripped_bark", () -> new BlockItem(ModBlocks.WALNUT_LOG_STRIPPED_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_LOG_BARK = BLOCK_ITEMS.register("walnut_log_bark", () -> new BlockItem(ModBlocks.WALNUT_LOG_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_PLANK = BLOCK_ITEMS.register("walnut_plank", () -> new BlockItem(ModBlocks.WALNUT_PLANK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> WALNUT_SAPLING = BLOCK_ITEMS.register("walnut_sapling", () -> new BlockItem(ModBlocks.WALNUT_SAPLING.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_LOG_STRIPPED = BLOCK_ITEMS.register("plum_log_stripped", () -> new BlockItem(ModBlocks.PLUM_LOG_STRIPPED.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_LOG = BLOCK_ITEMS.register("plum_log", () -> new BlockItem(ModBlocks.PLUM_LOG.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_LOG_STRIPPED_BARK = BLOCK_ITEMS.register("plum_log_stripped_bark", () -> new BlockItem(ModBlocks.PLUM_LOG_STRIPPED_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_LOG_BARK = BLOCK_ITEMS.register("plum_log_bark", () -> new BlockItem(ModBlocks.PLUM_LOG_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_PLANK = BLOCK_ITEMS.register("plum_plank", () -> new BlockItem(ModBlocks.PLUM_PLANK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PLUM_SAPLING = BLOCK_ITEMS.register("plum_sapling", () -> new BlockItem(ModBlocks.PLUM_SAPLING.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_LOG_STRIPPED = BLOCK_ITEMS.register("mulberry_log_stripped", () -> new BlockItem(ModBlocks.MULBERRY_LOG_STRIPPED.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_LOG = BLOCK_ITEMS.register("mulberry_log", () -> new BlockItem(ModBlocks.MULBERRY_LOG.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_LOG_STRIPPED_BARK = BLOCK_ITEMS.register("mulberry_log_stripped_bark", () -> new BlockItem(ModBlocks.MULBERRY_LOG_STRIPPED_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_LOG_BARK = BLOCK_ITEMS.register("mulberry_log_bark", () -> new BlockItem(ModBlocks.MULBERRY_LOG_BARK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_PLANK = BLOCK_ITEMS.register("mulberry_plank", () -> new BlockItem(ModBlocks.MULBERRY_PLANK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> MULBERRY_SAPLING = BLOCK_ITEMS.register("mulberry_sapling", () -> new BlockItem(ModBlocks.MULBERRY_SAPLING.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));

//    public static RegistryObject<BlockItem> WHITE_MARBLE = BLOCK_ITEMS.register("white_marble", () -> new BlockItem(ModBlocks.WHITE_MARBLE.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> RED_MARBLE = BLOCK_ITEMS.register("red_marble", () -> new BlockItem(ModBlocks.RED_MARBLE.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> BLACK_MARBLE = BLOCK_ITEMS.register("black_marble", () -> new BlockItem(ModBlocks.BLACK_MARBLE.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> BELLOWS = BLOCK_ITEMS.register("bellows", () -> new BlockItem(ModBlocks.BELLOWS.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> PAPER_DRYING_RACK = BLOCK_ITEMS.register("paper_drying_rack", () -> new BlockItem(ModBlocks.PAPER_DRYING_RACK.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> POT = BLOCK_ITEMS.register("pot", () -> new BlockItem(ModBlocks.POT.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> STONE_MILL = BLOCK_ITEMS.register("stone_mill", () -> new BlockItem(ModBlocks.STONE_MILL.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> STOVE = BLOCK_ITEMS.register("stove", () -> new BlockItem(ModBlocks.STOVE.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));
//    public static RegistryObject<BlockItem> VAT = BLOCK_ITEMS.register("vat", () -> new BlockItem(ModBlocks.VAT.get(), new Item.Properties().tab(ItemTabs.BLOCKS)));

    public ModBlockItems(IEventBus bus) {
        SinoCraft.getInstance().getLogger().info("Registering block items.");
        BLOCK_ITEMS.register(bus);
    }
}
