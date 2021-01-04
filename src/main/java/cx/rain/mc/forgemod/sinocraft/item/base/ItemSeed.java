package cx.rain.mc.forgemod.sinocraft.item.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemSeed extends BlockItem {
    private PlantType type = null;

    public ItemSeed(Block blockIn, PlantType typeIn) {
        super(blockIn, new Item.Properties()
                .group(ModGroups.AGRICULTURE)
                .setNoRepair());
        type = typeIn;
    }
}
