package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.api.enumerate.RadishType;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Blocks {
    public static final DeferredRegister<Block> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.BLOCKS, CultureCraft.MODID);

    public static RegistryObject<Block> RADISH_WHITE =
            REGISTRY.register("radish_white", () -> new BlockRadish(RadishType.WHITE));
    public static RegistryObject<Block> RADISH_SUMMER =
            REGISTRY.register("radish_summer", () -> new BlockRadish(RadishType.SUMMER));
    public static RegistryObject<Block> RADISH_GREEN =
            REGISTRY.register("radish_green", () -> new BlockRadish(RadishType.GREEN));

}
