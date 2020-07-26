package cx.rain.mc.forgemod.sinocraft.tileentity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.TILE_ENTITIES, SinoCraft.MODID);

    /*public static RegistryObject<TileEntityType<TileEntityStove>> STOVE = REGISTRY.register("stove",
            () -> TileEntityType.Builder.create(TileEntityStove::new, Blocks.STOVE.get()).build(null));*/
}
