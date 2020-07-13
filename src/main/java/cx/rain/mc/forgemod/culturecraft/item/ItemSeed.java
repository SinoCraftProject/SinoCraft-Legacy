package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.enumerate.PlantType;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

public class ItemSeed extends Item {
    private PlantType type = null;

    public ItemSeed(PlantType typeIn) {
        super(new Item.Properties()
                .group(Groups.AGRICULTURE)
                .setNoRepair());
        type = typeIn;
    }
}
