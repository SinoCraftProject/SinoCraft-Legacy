package cx.rain.mc.forgemod.sinocraft.item.base;

import cx.rain.mc.forgemod.sinocraft.item.ModGroups;
import cx.rain.mc.forgemod.sinocraft.block.plant.PlantType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemSeed extends BlockItem {

    public ItemSeed(PlantType typeIn) {
        super(typeIn.getPlant().get(), new Item.Properties()
                .group(ModGroups.AGRICULTURE)
                .setNoRepair());
    }
}
