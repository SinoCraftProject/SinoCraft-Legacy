package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.base.*;
import cx.rain.mc.forgemod.sinocraft.fluid.ModFluids;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.TreeType;
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

    public static RegistryObject<BlockLog> PEACH_LOG = REGISTRY.register("peach_log", () -> new BlockLog(TreeType.PEACH));
    public static RegistryObject<BlockLog> PEACH_LOG_BARK = REGISTRY.register("peach_log_bark", () -> new BlockLog(TreeType.PEACH));
    public static RegistryObject<BlockLog> PEACH_LOG_STRIPPED = REGISTRY.register("peach_log_stripped", () -> new BlockLog(TreeType.PEACH));
    public static RegistryObject<BlockLog> PEACH_LOG_STRIPPED_BARK = REGISTRY.register("peach_log_stripped_bark", () -> new BlockLog(TreeType.PEACH));
    public static RegistryObject<BlockPlank> PEACH_PLANK = REGISTRY.register("peach_plank", () -> new BlockPlank(TreeType.PEACH));
    public static RegistryObject<BlockLeavesGrowable> PEACH_LEAVES = REGISTRY.register("peach_leaves", () -> new BlockLeavesGrowable(TreeType.PEACH, ModItems.PEACH));
    public static RegistryObject<BlockSapling> PEACH_SAPLING = REGISTRY.register("peach_sapling", () -> new BlockSapling(TreeType.PEACH, new TreePeach()));

    public static RegistryObject<BlockLog> WALNUT_LOG = REGISTRY.register("walnut_log", () -> new BlockLog(TreeType.WALNUT));
    public static RegistryObject<BlockLog> WALNUT_LOG_BARK = REGISTRY.register("walnut_log_bark", () -> new BlockLog(TreeType.WALNUT));
    public static RegistryObject<BlockLog> WALNUT_LOG_STRIPPED = REGISTRY.register("walnut_log_stripped", () -> new BlockLog(TreeType.WALNUT));
    public static RegistryObject<BlockLog> WALNUT_LOG_STRIPPED_BARK = REGISTRY.register("walnut_log_stripped_bark", () -> new BlockLog(TreeType.WALNUT));
    public static RegistryObject<BlockPlank> WALNUT_PLANK = REGISTRY.register("walnut_plank", () -> new BlockPlank(TreeType.WALNUT));
    public static RegistryObject<BlockLeaves> WALNUT_LEAVES = REGISTRY.register("walnut_leaves", () -> new BlockLeaves(TreeType.WALNUT));
    public static RegistryObject<BlockSapling> WALNUT_SAPLING = REGISTRY.register("walnut_sapling", () -> new BlockSapling(TreeType.WALNUT, new TreeWalnut()));

    public static RegistryObject<BlockLog> PLUM_LOG = REGISTRY.register("plum_log", () -> new BlockLog(TreeType.PLUM));
    public static RegistryObject<BlockLog> PLUM_LOG_BARK = REGISTRY.register("plum_log_bark", () -> new BlockLog(TreeType.PLUM));
    public static RegistryObject<BlockLog> PLUM_LOG_STRIPPED = REGISTRY.register("plum_log_stripped", () -> new BlockLog(TreeType.PLUM));
    public static RegistryObject<BlockLog> PLUM_LOG_STRIPPED_BARK = REGISTRY.register("plum_log_stripped_bark", () -> new BlockLog(TreeType.PLUM));
    public static RegistryObject<BlockPlank> PLUM_PLANK = REGISTRY.register("plum_plank", () -> new BlockPlank(TreeType.PLUM));
    public static RegistryObject<BlockLeaves> PLUM_LEAVES = REGISTRY.register("plum_leaves", () -> new BlockLeaves(TreeType.PLUM));
    public static RegistryObject<BlockSapling> PLUM_SAPLING = REGISTRY.register("plum_sapling", () -> new BlockSapling(TreeType.PLUM, new TreePlum()));

    public static RegistryObject<BlockLog> MULBERRY_LOG = REGISTRY.register("mulberry_log", () -> new BlockLog(TreeType.MULBERRY));
    public static RegistryObject<BlockLog> MULBERRY_LOG_BARK = REGISTRY.register("mulberry_log_bark", () -> new BlockLog(TreeType.MULBERRY));
    public static RegistryObject<BlockLog> MULBERRY_LOG_STRIPPED = REGISTRY.register("mulberry_log_stripped", () -> new BlockLog(TreeType.MULBERRY));
    public static RegistryObject<BlockLog> MULBERRY_LOG_STRIPPED_BARK = REGISTRY.register("mulberry_log_stripped_bark", () -> new BlockLog(TreeType.MULBERRY));
    public static RegistryObject<BlockPlank> MULBERRY_PLANK = REGISTRY.register("mulberry_plank", () -> new BlockPlank(TreeType.MULBERRY));
    public static RegistryObject<BlockLeaves> MULBERRY_LEAVES = REGISTRY.register("mulberry_leaves", () -> new BlockLeaves(TreeType.MULBERRY));
    public static RegistryObject<BlockSapling> MULBERRY_SAPLING = REGISTRY.register("mulberry_sapling", () -> new BlockSapling(TreeType.MULBERRY, new TreeMulberry()));

    public static RegistryObject<BlockMarble> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockMarble(MarbleType.WHITE));
    public static RegistryObject<BlockMarble> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockMarble(MarbleType.RED));
    public static RegistryObject<BlockMarble> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockMarble(MarbleType.BLACK));

    public static RegistryObject<BlockStove> STOVE = REGISTRY.register("stove", BlockStove::new);
    public static RegistryObject<BlockVat> VAT = REGISTRY.register("vat", BlockVat::new);
    public static RegistryObject<BlockStoneMill> STONE_MILL = REGISTRY.register("stone_mill", BlockStoneMill::new);
    public static RegistryObject<BlockPaperDryingRack> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", BlockPaperDryingRack::new);
    public static RegistryObject<BlockPot> POT = REGISTRY.register("pot", BlockPot::new);
    public static RegistryObject<BlockBellows> BELLOWS = REGISTRY.register("bellows", BlockBellows::new);

    public static RegistryObject<FlowingFluidBlock> WOOD_PULP_BLOCK = REGISTRY.register("wood_pulp_block",()-> new FlowingFluidBlock(()-> ModFluids.WOOD_PULP.get(), Block.Properties.create(Material.WATER)));

    // Use for render
    public static RegistryObject<BlockTeaTable> TEA_TABLE = REGISTRY.register("tea_table", BlockTeaTable::new);
    public static RegistryObject<BlockTeacup> TEACUP = REGISTRY.register("teacup", BlockTeacup::new);
    public static RegistryObject<BlockTeapot> TEAPOT = REGISTRY.register("teapot", BlockTeapot::new);

    public ModBlocks(IEventBus bus) {
        SinoCraft.getLogger().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
