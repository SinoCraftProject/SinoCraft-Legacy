package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.group.Groups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;

public class ItemSeed extends BlockItem {
    private PlantType type = null;

    public ItemSeed(Block blockIn, PlantType typeIn) {
        super(blockIn, new Item.Properties()
                .group(Groups.AGRICULTURE)
                .setNoRepair());
        type = typeIn;
    }
}
