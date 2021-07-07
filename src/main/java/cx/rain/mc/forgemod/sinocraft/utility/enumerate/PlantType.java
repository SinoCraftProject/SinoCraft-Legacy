package cx.rain.mc.forgemod.sinocraft.utility.enumerate;

import cx.rain.mc.forgemod.sinocraft.api.block.IPlantType;
import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.base.BlockPlant;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;
import java.util.function.Supplier;

import static cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty.STAGE_0_3;
import static cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty.STAGE_0_7;

public enum PlantType implements IPlantType {

    WHITE_RADISH("white_radish", 1, STAGE_0_3, 0, 2, () -> ModBlockItems.WHITE_RADISH, () -> ModBlocks.WHITE_RADISH_PLANT),
    SUMMER_RADISH("summer_radish", 1, STAGE_0_3, 0, 2, () -> ModBlockItems.SUMMER_RADISH, () -> ModBlocks.SUMMER_RADISH_PLANT),
    GREEN_RADISH("green_radish", 1, STAGE_0_3, 0, 2, () -> ModBlockItems.GREEN_RADISH, () -> ModBlocks.GREEN_RADISH_PLANT),
    CHILI_PEPPER("chili_pepper", 1, STAGE_0_7, 2, 5, () -> ModItems.CHILI_PEPPER_SEED, () -> ModBlocks.CHILI_PEPPER_PLANT),
    GREEN_PEPPER("green_pepper", 1, STAGE_0_7, 2, 5, () -> ModItems.GREEN_PEPPER_SEED, () -> ModBlocks.GREEN_PEPPER_PLANT),
    EGGPLANT("eggplant", 1, STAGE_0_7, 2, 5, () -> ModItems.EGGPLANT_SEED, () -> ModBlocks.EGGPLANT_PLANT),
    CABBAGE("cabbage", 1, STAGE_0_3, 0, 2, () -> ModItems.CABBAGE_SEED, () -> ModBlocks.CABBAGE_PLANT),
    RICE("rice", 2, STAGE_0_7, 2, 5, () -> ModItems.RICE_SEED, () -> ModBlocks.RICE_PLANT),
    MILLET("millet", 1, STAGE_0_7, 2, 5, () -> ModItems.MILLET_SEED, () -> ModBlocks.MILLET_PLANT),
    SOYBEAN("soybean", 1, STAGE_0_3, 0, 2, () -> ModItems.SOYBEAN, () -> ModBlocks.SOYBEAN_PLANT),
    SORGHUM("sorghum", 2, STAGE_0_3, 0, 2, () -> ModItems.SORGHUM_SEED, () -> ModBlocks.SORGHUM_PLANT);

    private final String name;
    private final int maxHeight;
    private final StageProperty property;
    private final Supplier<RegistryObject<? extends Item>> seed;
    private final Supplier<RegistryObject<? extends CropsBlock>> plant;
    private final int minBonemeal, maxBonemeal;

    PlantType(String name, int maxHeight, StageProperty property, int minBonemeal, int maxBonemeal,
              Supplier<RegistryObject<? extends Item>> seed, Supplier<RegistryObject<? extends CropsBlock>> plant) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.property = property;
        this.minBonemeal = minBonemeal;
        this.maxBonemeal = maxBonemeal;
        this.seed = seed;
        this.plant = plant;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getMaxHeight() {
        return maxHeight;
    }

    @Override
    public StageProperty getProperty() {
        return property;
    }

    @Override
    public RegistryObject<? extends Item> getSeed() {
        return seed.get();
    }

    @Override
    public RegistryObject<? extends CropsBlock> getPlant() {
        return plant.get();
    }

    @Override
    public int randomGrowAge(Random random) {
        return MathHelper.nextInt(random, minBonemeal, maxBonemeal);
    }
}
