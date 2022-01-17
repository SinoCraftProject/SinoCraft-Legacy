package cx.rain.mc.forgemod.sinocraft.api.block.plant;

import cx.rain.mc.forgemod.sinocraft.api.block.IData;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public interface IPlantData extends IData {
    String getName();

    int getMaxHeight();

    PropertyPlantStage getProperty();

    RegistryObject<? extends Item> getSeed();

    int growWithBonus(Random random);
}
