package cx.rain.mc.forgemod.sinocraft.api.block.plant;

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
