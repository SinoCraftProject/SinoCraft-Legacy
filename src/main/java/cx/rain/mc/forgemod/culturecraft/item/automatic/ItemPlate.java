package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

@ModItem(name = "plate")
public class ItemPlate extends Item {
    public ItemPlate() {
        super(new Properties()
                .group(Groups.MISC)
                .setNoRepair());
    }
}
