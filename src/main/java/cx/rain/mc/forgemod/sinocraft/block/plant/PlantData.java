package cx.rain.mc.forgemod.sinocraft.block.plant;

import cx.rain.mc.forgemod.sinocraft.api.block.plant.IPlantData;
import cx.rain.mc.forgemod.sinocraft.api.block.plant.PropertyPlantStage;
import net.minecraft.util.Mth;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;
import java.util.function.Supplier;

public record PlantData(String name, int maxHeight,
                        PropertyPlantStage property, int minBonemeal,
                        int maxBonemeal,
                        Supplier<RegistryObject<? extends Item>> seed) implements IPlantData {
//    CHILI_PEPPER("chili_pepper", 1, STAGE_0_7, 2, 5, () -> ModItems.CHILI_PEPPER_SEED, () -> ModBlocks.CHILI_PEPPER_PLANT),
//    GREEN_PEPPER("green_pepper", 1, STAGE_0_7, 2, 5, () -> ModItems.GREEN_PEPPER_SEED, () -> ModBlocks.GREEN_PEPPER_PLANT),
//    EGGPLANT("eggplant", 1, STAGE_0_7, 2, 5, () -> ModItems.EGGPLANT_SEED, () -> ModBlocks.EGGPLANT_PLANT),
//    CABBAGE("cabbage", 1, STAGE_0_3, 0, 2, () -> ModItems.CABBAGE_SEED, () -> ModBlocks.CABBAGE_PLANT),
//    RICE("rice", 2, STAGE_0_7, 2, 5, () -> ModItems.RICE_SEED, () -> ModBlocks.RICE_PLANT),
//    MILLET("millet", 1, STAGE_0_7, 2, 5, () -> ModItems.MILLET_SEED, () -> ModBlocks.MILLET_PLANT),
//    SOYBEAN("soybean", 1, STAGE_0_3, 0, 2, () -> ModItems.SOYBEAN, () -> ModBlocks.SOYBEAN_PLANT),
//    SORGHUM("sorghum", 2, STAGE_0_3, 0, 2, () -> ModItems.SORGHUM_SEED, () -> ModBlocks.SORGHUM_PLANT);

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public PropertyPlantStage getProperty() {
        return property;
    }

    @Override
    public RegistryObject<? extends Item> getSeed() {
        return seed.get();
    }

    @Override
    public int growWithBonus(Random random) {
        return Mth.nextInt(random, minBonemeal, maxBonemeal);
    }
}
