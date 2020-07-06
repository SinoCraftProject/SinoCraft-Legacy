package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

/**
 * @author 86133
 */
@ModItem(name = "crown")
public class ItemCrown extends Item {
    public ItemCrown() {
        super(new Properties()
                .group(Groups.MISC)
                .maxStackSize(1));
    }
}
