package cx.rain.mc.forgemod.sinocraft.world.gen;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Ores {
    @SubscribeEvent
    public static void onSetUpEvent(FMLCommonSetupEvent event) {
        // Fixme: I dont know how to fix it.
//        for (Biome biome : ForgeRegistries.BIOMES) {
//            biome.generateFeatures(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Feature.ORE.withConfiguration(
//                            Features.BLACK_MARBLE_CONFIG
//                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128)))
//            );
//
//            biome.generateFeatures(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Feature.ORE.withConfiguration(
//                            Features.WHITE_MARBLE_CONFIG
//                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128)))
//            );
//
//            biome.generateFeatures(GenerationStage.Decoration.UNDERGROUND_ORES,
//                    Feature.ORE.withConfiguration(
//                            Features.RED_MARBLE_CONFIG
//                    ).withPlacement(Placement.COUNT_RANGE.configure(new CountRangeConfig(20, 0, 0, 128)))
//            );
//        }
    }
}
