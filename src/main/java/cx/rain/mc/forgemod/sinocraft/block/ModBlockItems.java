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
