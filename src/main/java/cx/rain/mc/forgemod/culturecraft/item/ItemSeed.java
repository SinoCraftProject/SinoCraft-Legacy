package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.enumerate.PlantType;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
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
