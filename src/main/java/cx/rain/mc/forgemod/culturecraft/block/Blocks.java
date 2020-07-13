package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;

import cx.rain.mc.forgemod.culturecraft.block.base.*;
import cx.rain.mc.forgemod.culturecraft.enumerate.LogType;
import cx.rain.mc.forgemod.culturecraft.enumerate.MarbleType;
import cx.rain.mc.forgemod.culturecraft.enumerate.PlantType;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import cx.rain.mc.forgemod.culturecraft.structure.tree.TreePeach;
import cx.rain.mc.forgemod.culturecraft.structure.tree.TreeWalnut;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, CultureCraft.MODID);

    public static RegistryObject<Block> WHITE_RADISH_PLANT = REGISTRY.register("white_radish_plant", () -> new BlockPlant(PlantType.WHITE_RADISH, BlockItems.WHITE_RADISH));
    public static RegistryObject<Block> SUMMER_RADISH_PLANT = REGISTRY.register("summer_radish_plant", () -> new BlockPlant(PlantType.SUMMER_RADISH, BlockItems.SUMMER_RADISH));
    public static RegistryObject<Block> GREEN_RADISH_PLANT = REGISTRY.register("green_radish_plant", () -> new BlockPlant(PlantType.GREEN_RADISH, BlockItems.GREEN_RADISH));
    public static RegistryObject<Block> CHILI_PEPPER_PLANT = REGISTRY.register("chili_pepper_plant", () -> new BlockPlant(PlantType.CHILI_PEPPER, Items.CHILI_PEPPER_SEED));
    public static RegistryObject<Block> GREEN_PEPPER_PLANT = REGISTRY.register("green_pepper_plant", () -> new BlockPlant(PlantType.GREEN_PEPPER, Items.GREEN_PEPPER_SEED));
    public static RegistryObject<Block> EGGPLANT_PLANT = REGISTRY.register("eggplant_plant", () -> new BlockPlant(PlantType.CABBAGE, Items.EGGPLANT_SEED));
    public static RegistryObject<Block> CABBAGE_PLANT = REGISTRY.register("cabbage_plant", () -> new BlockPlant(PlantType.CABBAGE, Items.CABBAGE_SEED));
    public static RegistryObject<Block> RICE_PLANT = REGISTRY.register("rice_plant", () -> new BlockPlant(PlantType.RICE, Items.RICE_SEED));
    public static RegistryObject<Block> MILLET_PLANT = REGISTRY.register("millet_plant", () -> new BlockPlant(PlantType.MILLET, Items.MILLET_SEED));
    public static RegistryObject<Block> SOYBEAN_PLANT = REGISTRY.register("soybean_plant", () -> new BlockPlant(PlantType.SOYBEAN, Items.SOYBEAN_SEED));

    public static RegistryObject<Block> LOG_PEACH = REGISTRY.register("log_peach", () -> new BlockLog(LogType.PEACH));
    public static RegistryObject<Block> LOG_PEACH_SKIN = REGISTRY.register("log_peach_skin", () -> new BlockLog(LogType.PEACH));
    public static RegistryObject<Block> LOG_PEACH_STRIPPED = REGISTRY.register("log_peach_stripped", () -> new BlockLog(LogType.PEACH));
    public static RegistryObject<Block> LOG_PEACH_STRIPPED_SKIN = REGISTRY.register("log_peach_stripped_skin", () -> new BlockLog(LogType.PEACH));
    public static RegistryObject<Block> PLANK_PEACH = REGISTRY.register("plank_peach", () -> new BlockPlank(LogType.PEACH));
    public static RegistryObject<Block> LEAVES_PEACH = REGISTRY.register("leaves_peach", () -> new BlockLeavesGrowable(LogType.PEACH, Items.PEACH));
    public static RegistryObject<Block> SAPLING_PEACH = REGISTRY.register("sapling_peach", () -> new BlockSapling(LogType.PEACH, new TreePeach()));

    public static RegistryObject<Block> LOG_WALNUT = REGISTRY.register("log_walnut", () -> new BlockLog(LogType.WALNUT));
    public static RegistryObject<Block> LOG_WALNUT_SKIN = REGISTRY.register("log_walnut_skin", () -> new BlockLog(LogType.WALNUT));
    public static RegistryObject<Block> LOG_WALNUT_STRIPPED = REGISTRY.register("log_walnut_stripped", () -> new BlockLog(LogType.WALNUT));
    public static RegistryObject<Block> LOG_WALNUT_STRIPPED_SKIN = REGISTRY.register("log_walnut_stripped_skin", () -> new BlockLog(LogType.WALNUT));
    public static RegistryObject<Block> PLANK_WALNUT = REGISTRY.register("plank_walnut", () -> new BlockPlank(LogType.WALNUT));
    public static RegistryObject<Block> LEAVES_WALNUT = REGISTRY.register("leaves_walnut", () -> new BlockLeaves(LogType.WALNUT));
    public static RegistryObject<Block> SAPLING_WALNUT = REGISTRY.register("sapling_walnut", () -> new BlockSapling(LogType.WALNUT, new TreeWalnut()));

    public static RegistryObject<Block> WHITE_MARBLE = REGISTRY.register("white_marble", () -> new BlockMarble(MarbleType.WHITE));
    public static RegistryObject<Block> RED_MARBLE = REGISTRY.register("red_marble", () -> new BlockMarble(MarbleType.RED));
    public static RegistryObject<Block> BLACK_MARBLE = REGISTRY.register("black_marble", () -> new BlockMarble(MarbleType.BLACK));

    public static RegistryObject<Block> STOVE = REGISTRY.register("stove", () -> new BlockStove());

    public Blocks(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
