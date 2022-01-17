package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.block.plant.PropertyPlantStage;
import cx.rain.mc.forgemod.sinocraft.block.plant.BlockPlant;
import cx.rain.mc.forgemod.sinocraft.block.plant.PlantData;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
  public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, SinoCraft.MODID);

  public static RegistryObject<BlockPlant> WHITE_RADISH_PLANT = REGISTRY.register("white_radish_plant", () -> new BlockPlant(new PlantData("white_radish", 1, PropertyPlantStage.STAGE_0_3, 0, 2, () -> ModBlockItems.WHITE_RADISH)));
  public static RegistryObject<BlockPlant> SUMMER_RADISH_PLANT = REGISTRY.register("summer_radish_plant", () -> new BlockPlant(new PlantData("summer_radish", 1, PropertyPlantStage.STAGE_0_3, 0, 2, () -> ModBlockItems.SUMMER_RADISH)));
  public static RegistryObject<BlockPlant> GREEN_RADISH_PLANT = REGISTRY.register("green_radish_plant", () -> new BlockPlant(new PlantData("green_radish", 1, PropertyPlantStage.STAGE_0_3, 0, 2, () -> ModBlockItems.GREEN_RADISH))));
  public static RegistryObject<BlockPlant> CHILI_PEPPER_PLANT = REGISTRY.register("chili_pepper_plant", () -> new BlockPlant(new PlantData("chili_pepper", 1, PropertyPlantStage.STAGE_0_7, 2, 5, () -> ModItems.CHILI_PEPPER_SEED)));
  public static RegistryObject<BlockPlant> GREEN_PEPPER_PLANT = REGISTRY.register("green_pepper_plant", () -> new BlockPlant(new PlantData("green_pepper", 1, PropertyPlantStage.STAGE_0_7, 2, 5, () -> ModItems.GREEN_PEPPER_SEED)));
  public static RegistryObject<BlockPlant> EGGPLANT_PLANT = REGISTRY.register("eggplant_plant", () -> new BlockPlant(new PlantData("eggplant", 1, PropertyPlantStage.STAGE_0_7, 2, 5, () -> ModItems.EGGPLANT_SEED)));
  public static RegistryObject<BlockPlant> CABBAGE_PLANT = REGISTRY.register("cabbage_plant", () -> new BlockPlant(new PlantData("cabbage", 1, PropertyPlantStage.STAGE_0_3, 0, 2, () -> ModItems.CABBAGE_SEED)));
  public static RegistryObject<BlockPlant> RICE_PLANT = REGISTRY.register("rice_plant", () -> new BlockPlant(new PlantData("rice", 2, PropertyPlantStage.STAGE_0_7, 2, 5, () -> ModItems.RICE_SEED)));
  public static RegistryObject<BlockPlant> MILLET_PLANT = REGISTRY.register("millet_plant", () -> new BlockPlant(new PlantData("millet", 1, PropertyPlantStage.STAGE_0_7, 2, 5, () -> ModItems.MILLET_SEED)));
  public static RegistryObject<BlockPlant> SOYBEAN_PLANT = REGISTRY.register("soybean_plant", () -> new BlockPlant(new PlantData("soybean", 1, PropertyPlantStage.STAGE_0_3, 0, 2, () -> ModItems.SOYBEAN)));
  public static RegistryObject<BlockPlant> SORGHUM_PLANT = REGISTRY.register("sorghum_plant", () -> new BlockPlant(new PlantData("sorghum", 2, PropertyPlantStage.STAGE_0_3, 0, 2, () -> ModItems.SORGHUM_SEED)));
  public static RegistryObject<BlockLog> PEACH_LOG_STRIPPED = REGISTRY.register("peach_log_stripped", () -> new BlockLog(TreeType.PEACH));
  public static RegistryObject<BlockLogStrippable> PEACH_LOG = REGISTRY.register("peach_log", () -> new BlockLogStrippable(TreeType.PEACH, ModBlocks.PEACH_LOG_STRIPPED));
  public static RegistryObject<BlockLog> PEACH_LOG_STRIPPED_BARK = REGISTRY.register("peach_log_stripped_bark", () -> new BlockLog(TreeType.PEACH));
  public static RegistryObject<BlockLogStrippable> PEACH_LOG_BARK = REGISTRY.register("peach_log_bark", () -> new BlockLogStrippable(TreeType.PEACH, ModBlocks.PEACH_LOG_STRIPPED_BARK));
  public static RegistryObject<BlockPlank> PEACH_PLANK = REGISTRY.register("peach_plank", () -> new BlockPlank(TreeType.PEACH));
  public static RegistryObject<BlockLeavesGrowable> PEACH_LEAVES = REGISTRY.register("peach_leaves", () -> new BlockLeavesGrowable(TreeType.PEACH, ModItems.PEACH));
  public static RegistryObject<BlockSapling> PEACH_SAPLING = REGISTRY.register("peach_sapling", () -> new BlockSapling(TreeType.PEACH, new TreePeach()));
  public static RegistryObject<BlockLog> WALNUT_LOG_STRIPPED = REGISTRY.register("walnut_log_stripped", () -> new BlockLog(TreeType.WALNUT));
  public static RegistryObject<BlockLogStrippable> WALNUT_LOG = REGISTRY.register("walnut_log", () -> new BlockLogStrippable(TreeType.WALNUT, ModBlocks.WALNUT_LOG_STRIPPED));
  public static RegistryObject<BlockLog> WALNUT_LOG_STRIPPED_BARK = REGISTRY.register("walnut_log_stripped_bark", () -> new BlockLog(TreeType.WALNUT));
  public static RegistryObject<BlockLogStrippable> WALNUT_LOG_BARK = REGISTRY.register("walnut_log_bark", () -> new BlockLogStrippable(TreeType.WALNUT, ModBlocks.WALNUT_LOG_STRIPPED_BARK));
  public static RegistryObject<BlockPlank> WALNUT_PLANK = REGISTRY.register("walnut_plank", () -> new BlockPlank(TreeType.WALNUT));
  public static RegistryObject<BlockLeaves> WALNUT_LEAVES = REGISTRY.register("walnut_leaves", () -> new BlockLeaves(TreeType.WALNUT));
  public static RegistryObject<BlockSapling> WALNUT_SAPLING = REGISTRY.register("walnut_sapling", () -> new BlockSapling(TreeType.WALNUT, new TreeWalnut()));
  public static RegistryObject<BlockLog> PLUM_LOG_STRIPPED = REGISTRY.register("plum_log_stripped", () -> new BlockLog(TreeType.PLUM));
  public static RegistryObject<BlockLogStrippable> PLUM_LOG = REGISTRY.register("plum_log", () -> new BlockLogStrippable(TreeType.PLUM, ModBlocks.PLUM_LOG_STRIPPED));
  public static RegistryObject<BlockLog> PLUM_LOG_STRIPPED_BARK = REGISTRY.register("plum_log_stripped_bark", () -> new BlockLog(TreeType.PLUM));
  public static RegistryObject<BlockLogStrippable> PLUM_LOG_BARK = REGISTRY.register("plum_log_bark", () -> new BlockLogStrippable(TreeType.PLUM, ModBlocks.PLUM_LOG_STRIPPED_BARK));
  public static RegistryObject<BlockPlank> PLUM_PLANK = REGISTRY.register("plum_plank", () -> new BlockPlank(TreeType.PLUM));
  public static RegistryObject<BlockLeaves> PLUM_LEAVES = REGISTRY.register("plum_leaves", () -> new BlockLeaves(TreeType.PLUM));
  public static RegistryObject<BlockSapling> PLUM_SAPLING = REGISTRY.register("plum_sapling", () -> new BlockSapling(TreeType.PLUM, new TreePlum()));
  public static RegistryObject<BlockLog> MULBERRY_LOG_STRIPPED = REGISTRY.register("mulberry_log_stripped", () -> new BlockLog(TreeType.MULBERRY));
  public static RegistryObject<BlockLogStrippable> MULBERRY_LOG = REGISTRY.register("mulberry_log", () -> new BlockLogStrippable(TreeType.MULBERRY, ModBlocks.MULBERRY_LOG_STRIPPED));
  public static RegistryObject<BlockLog> MULBERRY_LOG_STRIPPED_BARK = REGISTRY.register("mulberry_log_stripped_bark", () -> new BlockLog(TreeType.MULBERRY));
  public static RegistryObject<BlockLogStrippable> MULBERRY_LOG_BARK = REGISTRY.register("mulberry_log_bark", () -> new BlockLogStrippable(TreeType.MULBERRY, ModBlocks.MULBERRY_LOG_STRIPPED_BARK));
  public static RegistryObject<BlockPlank> MULBERRY_PLANK = REGISTRY.register("mulberry_plank", () -> new BlockPlank(TreeType.MULBERRY));
  public static RegistryObject<BlockLeaves> MULBERRY_LEAVES = REGISTRY.register("mulberry_leaves", () -> new BlockLeaves(TreeType.MULBERRY));
  public static RegistryObject<BlockSapling> MULBERRY_SAPLING = REGISTRY.register("mulberry_sapling", () -> new BlockSapling(TreeType.MULBERRY, new TreeMulberry()));
  public static RegistryObject<BlockMarble> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockMarble(MarbleType.WHITE));
  public static RegistryObject<BlockMarble> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockMarble(MarbleType.RED));
  public static RegistryObject<BlockMarble> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockMarble(MarbleType.BLACK));
  public static RegistryObject<FlowingFluidBlock> WOOD_PULP_BLOCK = REGISTRY.register("wood_pulp_block", () -> new FlowingFluidBlock(ModFluids.WOOD_PULP, Block.Properties.create(Material.WATER)));
  public static RegistryObject<BlockBellows> BELLOWS = REGISTRY.register("bellows", BlockBellows::new);
  public static RegistryObject<BlockPaperDryingRack> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", BlockPaperDryingRack::new);
  public static RegistryObject<BlockPot> POT = REGISTRY.register("pot", BlockPot::new);
  public static RegistryObject<BlockStoneMill> STONE_MILL = REGISTRY.register("stone_mill", BlockStoneMill::new);
  public static RegistryObject<BlockStove> STOVE = REGISTRY.register("stove", BlockStove::new);
  public static RegistryObject<BlockTeacup> TEACUP = REGISTRY.register("teacup", BlockTeacup::new);
  public static RegistryObject<BlockTeapot> TEAPOT = REGISTRY.register("teapot", BlockTeapot::new);
  public static RegistryObject<BlockTeaTable> TEA_TABLE = REGISTRY.register("tea_table", BlockTeaTable::new);
  public static RegistryObject<BlockVat> VAT = REGISTRY.register("vat", BlockVat::new);

  public ModBlocks(IEventBus bus) {
    SinoCraft.getLogger().info("Registering blocks.");
    REGISTRY.register(bus);
  }
}
