package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.api.enumerate.RadishType;
import cx.rain.mc.forgemod.culturecraft.block.BlockRadish;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockNamedItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Items {
    public static final DeferredRegister<Item> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ITEMS, CultureCraft.MODID);

    public static RegistryObject<Item> RADISH_WHITE =
            REGISTRY.register("radish_white", () -> new BlockItem(Blocks.RADISH_WHITE.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(4).saturation(6).build())
                            .setNoRepair()));

    public static RegistryObject<Item> RADISH_SUMMER =
            REGISTRY.register("radish_summer", () -> new BlockItem(Blocks.RADISH_SUMMER.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(3).saturation(5).build())
                            .setNoRepair()));

    public static RegistryObject<Item> RADISH_GREEN =
            REGISTRY.register("radish_green", () -> new BlockItem(Blocks.RADISH_GREEN.get(),
                    new Item.Properties()
                            .group(Groups.FOODS)
                            .food(new Food.Builder().hunger(1).saturation(2).build())
                            .setNoRepair()));
}
