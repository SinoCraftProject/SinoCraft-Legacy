package cx.rain.mc.forgemod.sinocraft.tileentity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class TileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SinoCraft.MODID);

    /*public static RegistryObject<TileEntityType<TileEntityStove>> STOVE = REGISTRY.register("stove",
            () -> TileEntityType.Builder.create(TileEntityStove::new, Blocks.STOVE.get()).build(null));*/

    public static RegistryObject<TileEntityType<TileEntityVat>> VAT = REGISTRY.register("vat",
            () -> TileEntityType.Builder.create(TileEntityVat::new, Blocks.VAT.get()).build(null));

    public static RegistryObject<TileEntityType<TileEntityStoneMill>> STONE_MILL = REGISTRY.register("stone_mill",
            () -> TileEntityType.Builder.create(TileEntityStoneMill::new, Blocks.STONE_MILL.get()).build(null));

    public TileEntities(IEventBus bus) {
        SinoCraft.getLog().info("Registering tileentities.");
        REGISTRY.register(bus);
    }
}
