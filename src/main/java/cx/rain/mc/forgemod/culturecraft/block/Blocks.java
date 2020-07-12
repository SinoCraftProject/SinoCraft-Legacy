package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;

import cx.rain.mc.forgemod.culturecraft.block.automatic.BlockPlantRice;
import cx.rain.mc.forgemod.culturecraft.enumerate.LogType;
import cx.rain.mc.forgemod.culturecraft.enumerate.PepperType;
import cx.rain.mc.forgemod.culturecraft.enumerate.RadishType;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import cx.rain.mc.forgemod.culturecraft.structure.tree.TreePeach;
import cx.rain.mc.forgemod.culturecraft.structure.tree.TreeWalnut;
import net.minecraft.block.Block;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, CultureCraft.MODID);

    public static RegistryObject<Block> RADISH_WHITE_PLANT =
            REGISTRY.register("radish_white_plant", () -> new BlockRadish(RadishType.White));
    public static RegistryObject<Block> RADISH_SUMMER_PLANT =
            REGISTRY.register("radish_summer_plant", () -> new BlockRadish(RadishType.Summer));
    public static RegistryObject<Block> RADISH_GREEN_PLANT =
            REGISTRY.register("radish_green_plant", () -> new BlockRadish(RadishType.Green));

    public static RegistryObject<Block> PEPPER_CHILI_PLANT =
            REGISTRY.register("pepper_chili_plant", () -> new BlockPepper(PepperType.Chili));
    public static RegistryObject<Block> PEPPER_GREEN_PLANT =
            REGISTRY.register("pepper_green_plant", () -> new BlockPepper(PepperType.Green));

    public static RegistryObject<Block> RICE_PLANT =
            REGISTRY.register("plant_rice", () ->
                    new BlockPlantRice(Block.Properties.from(net.minecraft.block.Blocks.WHEAT)));
    public static RegistryObject<Block> SOYBEAN_PLANT =
            REGISTRY.register("plant_soybean", () ->
                    new BlockPlantRice(Block.Properties.from(net.minecraft.block.Blocks.WHEAT)));

    public static RegistryObject<Block> LOG_PEACH =
            REGISTRY.register("log_peach", () -> new BlockLog(LogType.Peach));
    public static RegistryObject<Block> LOG_PEACH_SKIN =
            REGISTRY.register("log_peach_skin", () -> new BlockLog(LogType.Peach));
    public static RegistryObject<Block> LOG_PEACH_STRIPPED =
            REGISTRY.register("log_peach_stripped", () -> new BlockLog(LogType.Peach));
    public static RegistryObject<Block> LOG_PEACH_STRIPPED_SKIN =
            REGISTRY.register("log_peach_stripped_skin", () -> new BlockLog(LogType.Peach));
    public static RegistryObject<Block> PLANK_PEACH =
            REGISTRY.register("plank_peach", () -> new BlockPlank(LogType.Peach));
    public static RegistryObject<Block> LEAVES_PEACH =
            REGISTRY.register("leaves_peach", () -> new BlockLeavesGrowable(LogType.Peach,
                    new ItemStack(RegistryItem.ITEMS.get("peach"))));
    public static RegistryObject<Block> SAPLING_PEACH =
            REGISTRY.register("sapling_peach", () -> new BlockSapling(LogType.Peach, new TreePeach()));

    public static RegistryObject<Block> LOG_WALNUT =
            REGISTRY.register("log_walnut", () -> new BlockLog(LogType.Walnut));
    public static RegistryObject<Block> LOG_WALNUT_SKIN =
            REGISTRY.register("log_walnut_skin", () -> new BlockLog(LogType.Walnut));
    public static RegistryObject<Block> LOG_WALNUT_STRIPPED =
            REGISTRY.register("log_walnut_stripped", () -> new BlockLog(LogType.Walnut));
    public static RegistryObject<Block> LOG_WALNUT_STRIPPED_SKIN =
            REGISTRY.register("log_walnut_stripped_skin", () -> new BlockLog(LogType.Walnut));
    public static RegistryObject<Block> PLANK_WALNUT =
            REGISTRY.register("plank_walnut", () -> new BlockPlank(LogType.Walnut));
    public static RegistryObject<Block> LEAVES_WALNUT =
            REGISTRY.register("leaves_walnut", () -> new BlockLeaves(LogType.Walnut));
    public static RegistryObject<Block> SAPLING_WALNUT =
            REGISTRY.register("sapling_walnut", () -> new BlockSapling(LogType.Walnut, new TreeWalnut()));

    public Blocks(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering blocks.");
        REGISTRY.register(bus);
    }
}
