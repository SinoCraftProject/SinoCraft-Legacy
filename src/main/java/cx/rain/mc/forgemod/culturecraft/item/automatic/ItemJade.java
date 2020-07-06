package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;
@ModItem(name = "jade")
public class ItemJade extends Item {
    public ItemJade() {
        super(new Properties()
        .group(Groups.MISC)
        .maxStackSize(32));
    }
}
