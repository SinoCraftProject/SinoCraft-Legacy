package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;

import cx.rain.mc.forgemod.sinocraft.block.base.*;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogState;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogType;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.MarbleType;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty;
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

    public static RegistryObject<Block> WHITE_RADISH_PLANT = REGISTRY.register("white_radish_plant", () -> new BlockPlant(PlantType.WHITE_RADISH, ModBlockItems.WHITE_RADISH, StageProperty.STAGE_0_3));
    public static RegistryObject<Block> SUMMER_RADISH_PLANT = REGISTRY.register("summer_radish_plant", () -> new BlockPlant(PlantType.SUMMER_RADISH, ModBlockItems.SUMMER_RADISH, StageProperty.STAGE_0_3));
    public static RegistryObject<Block> GREEN_RADISH_PLANT = REGISTRY.register("green_radish_plant", () -> new BlockPlant(PlantType.GREEN_RADISH, ModBlockItems.GREEN_RADISH, StageProperty.STAGE_0_3));
    public static RegistryObject<Block> CHILI_PEPPER_PLANT = REGISTRY.register("chili_pepper_plant", () -> new BlockPlant(PlantType.CHILI_PEPPER, ModItems.CHILI_PEPPER_SEED, StageProperty.STAGE_0_7));
    public static RegistryObject<Block> GREEN_PEPPER_PLANT = REGISTRY.register("green_pepper_plant", () -> new BlockPlant(PlantType.GREEN_PEPPER, ModItems.GREEN_PEPPER_SEED, StageProperty.STAGE_0_7));
    public static RegistryObject<Block> EGGPLANT_PLANT = REGISTRY.register("eggplant_plant", () -> new BlockPlant(PlantType.CABBAGE, ModItems.EGGPLANT_SEED, StageProperty.STAGE_0_7));
    public static RegistryObject<Block> CABBAGE_PLANT = REGISTRY.register("cabbage_plant", () -> new BlockPlant(PlantType.CABBAGE, ModItems.CABBAGE_SEED, StageProperty.STAGE_0_3));
    public static RegistryObject<Block> RICE_PLANT = REGISTRY.register("rice_plant", () -> new BlockPlant(PlantType.RICE, ModItems.RICE_SEED, StageProperty.STAGE_0_7));
    public static RegistryObject<Block> MILLET_PLANT = REGISTRY.register("millet_plant", () -> new BlockPlant(PlantType.MILLET, ModItems.MILLET_SEED, StageProperty.STAGE_0_7));
    public static RegistryObject<Block> SOYBEAN_PLANT = REGISTRY.register("soybean_plant", () -> new BlockPlant(PlantType.SOYBEAN, ModItems.SOYBEAN, StageProperty.STAGE_0_3));
    public static RegistryObject<Block> SORGHUM_PLANT = REGISTRY.register("sorghum_plant", () -> new BlockPlant(PlantType.SORGHUM, ModItems.SORGHUM_SEED, StageProperty.STAGE_0_3));

    public static RegistryObject<Block> PEACH_LOG = REGISTRY.register("peach_log", () -> new BlockLog(LogType.PEACH, LogState.LOG));
    public static RegistryObject<Block> PEACH_LOG_BARK = REGISTRY.register("peach_log_bark", () -> new BlockLog(LogType.PEACH, LogState.LOG_BARK));
    public static RegistryObject<Block> PEACH_LOG_STRIPPED = REGISTRY.register("peach_log_stripped", () -> new BlockLog(LogType.PEACH, LogState.LOG_STRIPPED));
    public static RegistryObject<Block> PEACH_LOG_STRIPPED_BARK = REGISTRY.register("peach_log_stripped_bark", () -> new BlockLog(LogType.PEACH, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> PEACH_PLANK = REGISTRY.register("peach_plank", () -> new BlockPlank(LogType.PEACH));
    public static RegistryObject<Block> PEACH_LEAVES = REGISTRY.register("peach_leaves", () -> new BlockLeavesGrowable(LogType.PEACH, ModItems.PEACH));
    public static RegistryObject<Block> PEACH_SAPLING = REGISTRY.register("peach_sapling", () -> new BlockSapling(LogType.PEACH, new TreePeach()));

    public static RegistryObject<Block> WALNUT_LOG = REGISTRY.register("walnut_log", () -> new BlockLog(LogType.WALNUT, LogState.LOG));
    public static RegistryObject<Block> WALNUT_LOG_BARK = REGISTRY.register("walnut_log_bark", () -> new BlockLog(LogType.WALNUT, LogState.LOG_BARK));
    public static RegistryObject<Block> WALNUT_LOG_STRIPPED = REGISTRY.register("walnut_log_stripped", () -> new BlockLog(LogType.WALNUT, LogState.LOG_STRIPPED));
    public static RegistryObject<Block> WALNUT_LOG_STRIPPED_BARK = REGISTRY.register("walnut_log_stripped_bark", () -> new BlockLog(LogType.WALNUT, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> WALNUT_PLANK = REGISTRY.register("walnut_plank", () -> new BlockPlank(LogType.WALNUT));
    public static RegistryObject<Block> WALNUT_LEAVES = REGISTRY.register("walnut_leaves", () -> new BlockLeaves(LogType.WALNUT));
    public static RegistryObject<Block> WALNUT_SAPLING = REGISTRY.register("walnut_sapling", () -> new BlockSapling(LogType.WALNUT, new TreeWalnut()));

    public static RegistryObject<Block> PLUM_LOG = REGISTRY.register("plum_log", () -> new BlockLog(LogType.PLUM, LogState.LOG));
    public static RegistryObject<Block> PLUM_LOG_BARK = REGISTRY.register("plum_log_bark", () -> new BlockLog(LogType.PLUM, LogState.LOG_BARK));
    public static RegistryObject<Block> PLUM_LOG_STRIPPED = REGISTRY.register("plum_log_stripped", () -> new BlockLog(LogType.PLUM, LogState.LOG_STRIPPED));
    public static RegistryObject<Block> PLUM_LOG_STRIPPED_BARK = REGISTRY.register("plum_log_stripped_bark", () -> new BlockLog(LogType.PLUM, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> PLUM_PLANK = REGISTRY.register("plum_plank", () -> new BlockPlank(LogType.PLUM));
    public static RegistryObject<Block> PLUM_LEAVES = REGISTRY.register("plum_leaves", () -> new BlockLeaves(LogType.PLUM));
    public static RegistryObject<Block> PLUM_SAPLING = REGISTRY.register("plum_sapling", () -> new BlockSapling(LogType.PLUM, new TreePlum()));

    public static RegistryObject<Block> MULBERRY_LOG = REGISTRY.register("mulberry_log", () -> new BlockLog(LogType.MULBERRY, LogState.LOG));
    public static RegistryObject<Block> MULBERRY_LOG_BARK = REGISTRY.register("mulberry_log_bark", () -> new BlockLog(LogType.MULBERRY, LogState.LOG_BARK));
    public static RegistryObject<Block> MULBERRY_LOG_STRIPPED = REGISTRY.register("mulberry_log_stripped", () -> new BlockLog(LogType.MULBERRY, LogState.LOG_STRIPPED));
    public static RegistryObject<Block> MULBERRY_LOG_STRIPPED_BARK = REGISTRY.register("mulberry_log_stripped_bark", () -> new BlockLog(LogType.MULBERRY, LogState.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> MULBERRY_PLANK = REGISTRY.register("mulberry_plank", () -> new BlockPlank(LogType.MULBERRY));
    public static RegistryObject<Block> MULBERRY_LEAVES = REGISTRY.register("mulberry_leaves", () -> new BlockLeaves(LogType.MULBERRY));
    public static RegistryObject<Block> MULBERRY_SAPLING = REGISTRY.register("mulberry_sapling", () -> new BlockSapling(LogType.MULBERRY, new TreeMulberry()));

    public static RegistryObject<Block> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockMarble(MarbleType.WHITE));
    public static RegistryObject<Block> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockMarble(MarbleType.RED));
    public static RegistryObject<Block> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockMarble(MarbleType.BLACK));

    public static RegistryObject<Block> STOVE = REGISTRY.register("stove", BlockStove::new);
    public static RegistryObject<Block> VAT = REGISTRY.register("vat", BlockVat::new);
    public static RegistryObject<Block> STONE_MILL = REGISTRY.register("stone_mill", BlockStoneMill::new);
    public static RegistryObject<Block> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", BlockPaperDryingRack::new);

    public static RegistryObject<Block> WOOD_PULP_BLOCK = REGISTRY.register("wood_pulp_block",()-> new FlowingFluidBlock(()-> ModFluids.WOOD_PULP.get(), Block.Properties.create(Material.WATER)));

    public ModBlocks(IEventBus bus) {
        SinoCraft.getLog().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
