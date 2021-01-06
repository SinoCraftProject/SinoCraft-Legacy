package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, SinoCraft.MODID);

    public static RegistryObject<TileEntityType<TileEntityVat>> VAT = REGISTRY.register("vat",
            () -> TileEntityType.Builder.create(TileEntityVat::new, ModBlocks.VAT.get()).build(null));

    public static RegistryObject<TileEntityType<TileEntityStoneMill>> STONE_MILL = REGISTRY.register("stone_mill",
            () -> TileEntityType.Builder.create(TileEntityStoneMill::new, ModBlocks.STONE_MILL.get()).build(null));

    public ModTileEntities(IEventBus bus) {
        SinoCraft.getLogger().info("Registering tile entities.");
        REGISTRY.register(bus);
    }
}
