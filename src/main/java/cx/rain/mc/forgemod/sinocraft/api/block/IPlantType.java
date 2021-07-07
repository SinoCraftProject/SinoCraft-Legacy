package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty;
import net.minecraft.block.CropsBlock;
import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

public interface IPlantType {
    String getName();

    int getMaxHeight();

    StageProperty getProperty();

    RegistryObject<? extends Item> getSeed();

    RegistryObject<? extends CropsBlock> getPlant();

    int randomGrowAge(Random random);
}
