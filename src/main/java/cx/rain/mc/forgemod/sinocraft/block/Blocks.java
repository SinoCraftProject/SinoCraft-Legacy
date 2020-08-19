package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;

import cx.rain.mc.forgemod.sinocraft.block.base.*;
import cx.rain.mc.forgemod.sinocraft.enumerate.LogType;
import cx.rain.mc.forgemod.sinocraft.enumerate.MarbleType;
import cx.rain.mc.forgemod.sinocraft.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.fluid.Fluids;
import cx.rain.mc.forgemod.sinocraft.item.Items;
import cx.rain.mc.forgemod.sinocraft.structure.tree.TreeMulberry;
import cx.rain.mc.forgemod.sinocraft.structure.tree.TreePeach;
import cx.rain.mc.forgemod.sinocraft.structure.tree.TreePlum;
import cx.rain.mc.forgemod.sinocraft.structure.tree.TreeWalnut;
import net.minecraft.block.Block;
import net.minecraft.block.FlowingFluidBlock;
import net.minecraft.block.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, SinoCraft.MODID);

    public static RegistryObject<Block> WHITE_RADISH_PLANT = REGISTRY.register("white_radish_plant", () -> new BlockPlant(PlantType.WHITE_RADISH, BlockItems.WHITE_RADISH));
    public static RegistryObject<Block> SUMMER_RADISH_PLANT = REGISTRY.register("summer_radish_plant", () -> new BlockPlant(PlantType.SUMMER_RADISH, BlockItems.SUMMER_RADISH));
    public static RegistryObject<Block> GREEN_RADISH_PLANT = REGISTRY.register("green_radish_plant", () -> new BlockPlant(PlantType.GREEN_RADISH, BlockItems.GREEN_RADISH));
    public static RegistryObject<Block> CHILI_PEPPER_PLANT = REGISTRY.register("chili_pepper_plant", () -> new BlockPlant(PlantType.CHILI_PEPPER, Items.CHILI_PEPPER_SEED));
    public static RegistryObject<Block> GREEN_PEPPER_PLANT = REGISTRY.register("green_pepper_plant", () -> new BlockPlant(PlantType.GREEN_PEPPER, Items.GREEN_PEPPER_SEED));
    //public static RegistryObject<Block> EGGPLANT_PLANT = REGISTRY.register("eggplant_plant", () -> new BlockPlant(PlantType.CABBAGE, Items.EGGPLANT_SEED));
    //public static RegistryObject<Block> CABBAGE_PLANT = REGISTRY.register("cabbage_plant", () -> new BlockPlant(PlantType.CABBAGE, Items.CABBAGE_SEED));
    //public static RegistryObject<Block> RICE_PLANT = REGISTRY.register("rice_plant", () -> new BlockPlant(PlantType.RICE, Items.RICE_SEED));
    //public static RegistryObject<Block> MILLET_PLANT = REGISTRY.register("millet_plant", () -> new BlockPlant(PlantType.MILLET, Items.MILLET_SEED));
    //public static RegistryObject<Block> SOYBEAN_PLANT = REGISTRY.register("soybean_plant", () -> new BlockPlant(PlantType.SOYBEAN, Items.SOYBEAN));
    //public static RegistryObject<Block> SORGHUM_PLANT = REGISTRY.register("sorghum_plant", () -> new BlockPlant(PlantType.SORGHUM, Items.SORGHUM_SEED));

    public static RegistryObject<Block> LOG_PEACH = REGISTRY.register("log_peach", () -> new BlockLog(LogType.PEACH, BlockLog.KIND.LOG));
    public static RegistryObject<Block> LOG_PEACH_BARK = REGISTRY.register("log_peach_bark", () -> new BlockLog(LogType.PEACH, BlockLog.KIND.LOG_BARK));
    public static RegistryObject<Block> LOG_PEACH_STRIPPED = REGISTRY.register("log_peach_stripped", () -> new BlockLog(LogType.PEACH, BlockLog.KIND.LOG_STRIPPED));
    public static RegistryObject<Block> LOG_PEACH_STRIPPED_BARK = REGISTRY.register("log_peach_stripped_bark", () -> new BlockLog(LogType.PEACH, BlockLog.KIND.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> PLANK_PEACH = REGISTRY.register("plank_peach", () -> new BlockPlank(LogType.PEACH));
    public static RegistryObject<Block> LEAVES_PEACH = REGISTRY.register("leaves_peach", () -> new BlockLeavesGrowable(LogType.PEACH, Items.PEACH));
    public static RegistryObject<Block> SAPLING_PEACH = REGISTRY.register("sapling_peach", () -> new BlockSapling(LogType.PEACH, new TreePeach()));

    public static RegistryObject<Block> LOG_WALNUT = REGISTRY.register("log_walnut", () -> new BlockLog(LogType.WALNUT, BlockLog.KIND.LOG));
    public static RegistryObject<Block> LOG_WALNUT_BARK = REGISTRY.register("log_walnut_bark", () -> new BlockLog(LogType.WALNUT, BlockLog.KIND.LOG_BARK));
    public static RegistryObject<Block> LOG_WALNUT_STRIPPED = REGISTRY.register("log_walnut_stripped", () -> new BlockLog(LogType.WALNUT, BlockLog.KIND.LOG_STRIPPED));
    public static RegistryObject<Block> LOG_WALNUT_STRIPPED_BARK = REGISTRY.register("log_walnut_stripped_bark", () -> new BlockLog(LogType.WALNUT, BlockLog.KIND.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> PLANK_WALNUT = REGISTRY.register("plank_walnut", () -> new BlockPlank(LogType.WALNUT));
    public static RegistryObject<Block> LEAVES_WALNUT = REGISTRY.register("leaves_walnut", () -> new BlockLeaves(LogType.WALNUT));
    public static RegistryObject<Block> SAPLING_WALNUT = REGISTRY.register("sapling_walnut", () -> new BlockSapling(LogType.WALNUT, new TreeWalnut()));

    public static RegistryObject<Block> LOG_PLUM = REGISTRY.register("log_plum", () -> new BlockLog(LogType.PLUM, BlockLog.KIND.LOG));
    public static RegistryObject<Block> LOG_PLUM_BARK = REGISTRY.register("log_plum_bark", () -> new BlockLog(LogType.PLUM, BlockLog.KIND.LOG_BARK));
    public static RegistryObject<Block> LOG_PLUM_STRIPPED = REGISTRY.register("log_plum_stripped", () -> new BlockLog(LogType.PLUM, BlockLog.KIND.LOG_STRIPPED));
    public static RegistryObject<Block> LOG_PLUM_STRIPPED_BARK = REGISTRY.register("log_plum_stripped_bark", () -> new BlockLog(LogType.PLUM, BlockLog.KIND.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> PLANK_PLUM = REGISTRY.register("plank_plum", () -> new BlockPlank(LogType.PLUM));
    public static RegistryObject<Block> LEAVES_PLUM = REGISTRY.register("leaves_plum", () -> new BlockLeaves(LogType.PLUM));
    public static RegistryObject<Block> SAPLING_PLUM = REGISTRY.register("sapling_plum", () -> new BlockSapling(LogType.PLUM, new TreePlum()));

    public static RegistryObject<Block> LOG_MULBERRY = REGISTRY.register("log_mulberry", () -> new BlockLog(LogType.MULBERRY, BlockLog.KIND.LOG));
    public static RegistryObject<Block> LOG_MULBERRY_BARK = REGISTRY.register("log_mulberry_bark", () -> new BlockLog(LogType.MULBERRY, BlockLog.KIND.LOG_BARK));
    public static RegistryObject<Block> LOG_MULBERRY_STRIPPED = REGISTRY.register("log_mulberry_stripped", () -> new BlockLog(LogType.MULBERRY, BlockLog.KIND.LOG_STRIPPED));
    public static RegistryObject<Block> LOG_MULBERRY_STRIPPED_BARK = REGISTRY.register("log_mulberry_stripped_bark", () -> new BlockLog(LogType.MULBERRY, BlockLog.KIND.LOG_STRIPPED_BARK));
    public static RegistryObject<Block> PLANK_MULBERRY = REGISTRY.register("plank_mulberry", () -> new BlockPlank(LogType.MULBERRY));
    public static RegistryObject<Block> LEAVES_MULBERRY = REGISTRY.register("leaves_mulberry", () -> new BlockLeaves(LogType.MULBERRY));
    public static RegistryObject<Block> SAPLING_MULBERRY = REGISTRY.register("sapling_mulberry", () -> new BlockSapling(LogType.MULBERRY, new TreeMulberry()));

    public static RegistryObject<Block> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockMarble(MarbleType.WHITE));
    public static RegistryObject<Block> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockMarble(MarbleType.RED));
    public static RegistryObject<Block> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockMarble(MarbleType.BLACK));

    public static RegistryObject<Block> STOVE = REGISTRY.register("stove", () -> new BlockStove());
    public static RegistryObject<Block> VAT = REGISTRY.register("vat", () -> new BlockVat());
    public static RegistryObject<Block> STONE_MILL = REGISTRY.register("stone_mill", () -> new BlockStoneMill());
    public static RegistryObject<Block> PAPER_DRYING_RACK = REGISTRY.register("paper_drying_rack", () -> new BlockPaperDryingRack());

    public static RegistryObject<Block> WOOD_PULP_BLOCK = REGISTRY.register("wood_pulp_block",()-> new FlowingFluidBlock(()->Fluids.WOOD_PULP.get(), Block.Properties.create(Material.WATER)));

    public Blocks(IEventBus bus) {
        SinoCraft.getInstance().getLog().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
