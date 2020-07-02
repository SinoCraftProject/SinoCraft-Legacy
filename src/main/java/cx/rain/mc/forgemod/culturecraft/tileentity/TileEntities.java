package cx.rain.mc.forgemod.culturecraft.tileentity;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, CultureCraft.MODID);

    public static RegistryObject<TileEntityType<TileEntityStove>> STOVE = REGISTRY.register("stove",
            () -> TileEntityType.Builder.create(TileEntityStove::new,
                    RegistryBlock.BLOCKS.get("stove")).build(null));
}
