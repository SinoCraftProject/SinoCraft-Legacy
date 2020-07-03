package cx.rain.mc.forgemod.culturecraft.registry;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import cx.rain.mc.forgemod.culturecraft.api.annotation.ModTileEntity;
import cx.rain.mc.forgemod.culturecraft.utility.AnnotationsHelper;
import net.minecraft.block.Block;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Deprecated
@Mod.EventBusSubscriber(modid = CultureCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryTileEntity {
    public static Map<String, Pair<List<Block>, TileEntity>> TILE_ENTITIES = new LinkedHashMap<>();

    static {

    }

    /*
    static {
        String tileEntitiesPackage = "cx.rain.mc.forgemod.culturecraft.tileentity.automatic";
        for (Class<?> clazz : AnnotationsHelper.getClassAnnotated(tileEntitiesPackage, ModTileEntity.class)) {
            try {
                ModTileEntity modTileEntity = clazz.getAnnotation(ModTileEntity.class);
                String registryName = modTileEntity.name();
                if (!registryName.isEmpty()) {
                    Class<? extends Block>[] blockClasses = modTileEntity.validBlocks();
                    List<Block> blocks = new ArrayList<>();
                    for (Class<? extends Block> bClazz : blockClasses) {
                        for (Block b : RegistryBlock.BLOCKS.values()) {
                            if (b instanceof bClazz.getClass()) {

                            }
                        }
                    }
                    TILE_ENTITIES.put(registryName, new Pair<>())
                }
            } catch (NoSuchMethodException
                    | IllegalAccessException
                    | InstantiationException
                    | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }
     */

    @SubscribeEvent
    public static void onRegisterTileEntity(RegistryEvent.Register<TileEntityType<?>> event) {
        TILE_ENTITIES.forEach((name, pair) -> {
            TileEntity te = pair.getValue();
            Block[] blocks = pair.getKey().toArray(new Block[]{});
            TileEntityType<?> type = TileEntityType.Builder.create(() -> te, blocks).build(null);
            type.setRegistryName(CultureCraft.MODID, name);
            event.getRegistry().register(type);
        });
    }
}
