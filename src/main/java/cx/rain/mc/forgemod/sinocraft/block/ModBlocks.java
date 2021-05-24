package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.base.*;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogState;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogType;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.MarbleType;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreeMulberry;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreePeach;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreePlum;
import cx.rain.mc.forgemod.sinocraft.world.tree.TreeWalnut;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModBlocks {
    public static final DeferredRegister<Block> REGISTRY =
            DeferredRegister.create(ForgeRegistries.BLOCKS, SinoCraft.MODID);

    public static RegistryObject<BlockPlant> WHITE_RADISH_PLANT = REGISTRY.register("white_radish_plant", () -> BlockPlant.create(PlantType.WHITE_RADISH));
    public static RegistryObject<BlockPlant> SUMMER_RADISH_PLANT = REGISTRY.register("summer_radish_plant", () -> BlockPlant.create(PlantType.SUMMER_RADISH));
    public static RegistryObject<BlockPlant> GREEN_RADISH_PLANT = REGISTRY.register("green_radish_plant", () -> BlockPlant.create(PlantType.GREEN_RADISH));
    public static RegistryObject<BlockPlant> CHILI_PEPPER_PLANT = REGISTRY.register("chili_pepper_plant", () -> BlockPlant.create(PlantType.CHILI_PEPPER));
    public static RegistryObject<BlockPlant> GREEN_PEPPER_PLANT = REGISTRY.register("green_pepper_plant", () -> BlockPlant.create(PlantType.GREEN_PEPPER));
    public static RegistryObject<BlockPlant> EGGPLANT_PLANT = REGISTRY.register("eggplant_plant", () -> BlockPlant.create(PlantType.EGGPLANT));
    public static RegistryObject<BlockPlant> CABBAGE_PLANT = REGISTRY.register("cabbage_plant", () -> BlockPlant.create(PlantType.CABBAGE));
    public static RegistryObject<BlockPlant> RICE_PLANT = REGISTRY.register("rice_plant", () -> BlockPlant.create(PlantType.RICE));
    public static RegistryObject<BlockPlant> MILLET_PLANT = REGISTRY.register("millet_plant", () -> BlockPlant.create(PlantType.MILLET));
    public static RegistryObject<BlockPlant> SOYBEAN_PLANT = REGISTRY.register("soybean_plant", () -> BlockPlant.create(PlantType.SOYBEAN));
    public static RegistryObject<BlockPlant> SORGHUM_PLANT = REGISTRY.register("sorghum_plant", () -> BlockPlant.create(PlantType.SORGHUM));

    public static RegistryObject<BlockLog> PEACH_LOG = REGISTRY.register("peach_log", () -> new BlockLog(LogType.PEACH, LogState.LOG));
    public static RegistryObject<BlockLog> PEACH_LOG_BARK = REGISTRY.register("peach_log_bark", () -> new BlockLog(LogType.PEACH, LogState.LOG_BARK));
    public static RegistryObject<BlockLog> PEACH_LOG_STRIPPED = REGISTRY.register("peach_log_stripped", () -> new BlockLog(LogType.PEACH, LogState.LOG_STRIPPED));
    public static RegistryObject<BlockLog> PEACH_LOG_STRIPPED_BARK = REGISTRY.register("peach_log_stripped_bark", () -> new BlockLog(LogType.PEACH, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<BlockPlank> PEACH_PLANK = REGISTRY.register("peach_plank", () -> new BlockPlank(LogType.PEACH));
    public static RegistryObject<BlockLeavesGrowable> PEACH_LEAVES = REGISTRY.register("peach_leaves", () -> new BlockLeavesGrowable(LogType.PEACH, ModItems.PEACH));
    public static RegistryObject<BlockSapling> PEACH_SAPLING = REGISTRY.register("peach_sapling", () -> new BlockSapling(LogType.PEACH, new TreePeach()));

    public static RegistryObject<BlockLog> WALNUT_LOG = REGISTRY.register("walnut_log", () -> new BlockLog(LogType.WALNUT, LogState.LOG));
    public static RegistryObject<BlockLog> WALNUT_LOG_BARK = REGISTRY.register("walnut_log_bark", () -> new BlockLog(LogType.WALNUT, LogState.LOG_BARK));
    public static RegistryObject<BlockLog> WALNUT_LOG_STRIPPED = REGISTRY.register("walnut_log_stripped", () -> new BlockLog(LogType.WALNUT, LogState.LOG_STRIPPED));
    public static RegistryObject<BlockLog> WALNUT_LOG_STRIPPED_BARK = REGISTRY.register("walnut_log_stripped_bark", () -> new BlockLog(LogType.WALNUT, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<BlockPlank> WALNUT_PLANK = REGISTRY.register("walnut_plank", () -> new BlockPlank(LogType.WALNUT));
    public static RegistryObject<BlockLeaves> WALNUT_LEAVES = REGISTRY.register("walnut_leaves", () -> new BlockLeaves(LogType.WALNUT));
    public static RegistryObject<BlockSapling> WALNUT_SAPLING = REGISTRY.register("walnut_sapling", () -> new BlockSapling(LogType.WALNUT, new TreeWalnut()));

    public static RegistryObject<BlockLog> PLUM_LOG = REGISTRY.register("plum_log", () -> new BlockLog(LogType.PLUM, LogState.LOG));
    public static RegistryObject<BlockLog> PLUM_LOG_BARK = REGISTRY.register("plum_log_bark", () -> new BlockLog(LogType.PLUM, LogState.LOG_BARK));
    public static RegistryObject<BlockLog> PLUM_LOG_STRIPPED = REGISTRY.register("plum_log_stripped", () -> new BlockLog(LogType.PLUM, LogState.LOG_STRIPPED));
    public static RegistryObject<BlockLog> PLUM_LOG_STRIPPED_BARK = REGISTRY.register("plum_log_stripped_bark", () -> new BlockLog(LogType.PLUM, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<BlockPlank> PLUM_PLANK = REGISTRY.register("plum_plank", () -> new BlockPlank(LogType.PLUM));
    public static RegistryObject<BlockLeaves> PLUM_LEAVES = REGISTRY.register("plum_leaves", () -> new BlockLeaves(LogType.PLUM));
    public static RegistryObject<BlockSapling> PLUM_SAPLING = REGISTRY.register("plum_sapling", () -> new BlockSapling(LogType.PLUM, new TreePlum()));

    public static RegistryObject<BlockLog> MULBERRY_LOG = REGISTRY.register("mulberry_log", () -> new BlockLog(LogType.MULBERRY, LogState.LOG));
    public static RegistryObject<BlockLog> MULBERRY_LOG_BARK = REGISTRY.register("mulberry_log_bark", () -> new BlockLog(LogType.MULBERRY, LogState.LOG_BARK));
    public static RegistryObject<BlockLog> MULBERRY_LOG_STRIPPED = REGISTRY.register("mulberry_log_stripped", () -> new BlockLog(LogType.MULBERRY, LogState.LOG_STRIPPED));
    public static RegistryObject<BlockLog> MULBERRY_LOG_STRIPPED_BARK = REGISTRY.register("mulberry_log_stripped_bark", () -> new BlockLog(LogType.MULBERRY, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<BlockPlank> MULBERRY_PLANK = REGISTRY.register("mulberry_plank", () -> new BlockPlank(LogType.MULBERRY));
    public static RegistryObject<BlockLeaves> MULBERRY_LEAVES = REGISTRY.register("mulberry_leaves", () -> new BlockLeaves(LogType.MULBERRY));
    public static RegistryObject<BlockSapling> MULBERRY_SAPLING = REGISTRY.register("mulberry_sapling", () -> new BlockSapling(LogType.MULBERRY, new TreeMulberry()));

    public static RegistryObject<BlockMarble> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockMarble(MarbleType.WHITE));
    public static RegistryObject<BlockMarble> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockMarble(MarbleType.RED));
    public static RegistryObject<BlockMarble> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockMarble(MarbleType.BLACK));

    public static RegistryObject<BlockStove> STOVE = REGISTRY.register("stove", BlockStove::new);
    public static RegistryObject<BlockVat> VAT = REGISTRY.register("vat", BlockVat::new);
    public static RegistryObject<BlockStoneMill> STONE_MILL = REGISTRY.register("stone_mill", BlockStoneMill::new);
    public static RegistryObject<BlockPaperDryingRack> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", BlockPaperDryingRack::new);
    public static RegistryObject<BlockPot> POT = REGISTRY.register("pot", BlockPot::new);

    public static RegistryObject<Block> WOOD_PULP_BLOCK = REGISTRY.register("wood_pulp_block",()-> new FlowingFluidBlock(()-> ModFluids.WOOD_PULP.get(), Block.Properties.create(Material.WATER)));

    public static RegistryObject<Block> TEA_TABLE = REGISTRY.register("tea_table", BlockTeaTable::new);

    public ModBlocks(IEventBus bus) {
        SinoCraft.getLogger().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
