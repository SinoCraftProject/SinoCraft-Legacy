package cx.rain.mc.forgemod.sinocraft.utility.enumerate;

import cx.rain.mc.forgemod.sinocraft.block.ModBlockItems;
import cx.rain.mc.forgemod.sinocraft.api.block.IPlantType;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty;
import net.minecraft.item.Item;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

import static cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty.STAGE_0_3;
import static cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty.STAGE_0_7;

public enum PlantType implements IPlantType {

    WHITE_RADISH("white_radish", 1, STAGE_0_3, ModBlockItems.WHITE_RADISH, 0, 2),
    SUMMER_RADISH("summer_radish", 1, STAGE_0_3, ModBlockItems.SUMMER_RADISH, 0, 2),
    GREEN_RADISH("green_radish", 1, STAGE_0_3, ModBlockItems.GREEN_RADISH, 0, 2),
    CHILI_PEPPER("chili_pepper", 1, STAGE_0_7, ModItems.CHILI_PEPPER_SEED, 2, 5),
    GREEN_PEPPER("green_pepper", 1, STAGE_0_7, ModItems.GREEN_PEPPER_SEED, 2, 5),
    EGGPLANT("eggplant", 1, STAGE_0_7, ModItems.EGGPLANT_SEED, 2, 5),
    CABBAGE("cabbage", 1, STAGE_0_3, ModItems.CABBAGE_SEED, 0, 2),
    RICE("rice", 2, STAGE_0_7, ModItems.RICE_SEED, 2, 5),
    MILLET("millet", 1, STAGE_0_7, ModItems.MILLET_SEED, 2, 5),
    SOYBEAN("soybean", 1, STAGE_0_3, ModItems.SOYBEAN, 0, 2),
    SORGHUM("sorghum", 2, STAGE_0_3, ModItems.SORGHUM_SEED, 0, 2);

    private final String name;
    private final int maxHeight;
    private final StageProperty property;
    private final RegistryObject<? extends Item> seed;
    private final int minBonemeal, maxBonemeal;

    PlantType(String name, int maxHeight, StageProperty property, RegistryObject<? extends Item> seed, int minBonemeal, int maxBonemeal) {
        this.name = name;
        this.maxHeight = maxHeight;
        this.property = property;
        this.seed = seed;
        this.minBonemeal = minBonemeal;
        this.maxBonemeal = maxBonemeal;
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
        return seed;
    }

    @Override
    public int randomGrowAge(Random random) {
        return MathHelper.nextInt(random, minBonemeal, maxBonemeal);
    }
}
