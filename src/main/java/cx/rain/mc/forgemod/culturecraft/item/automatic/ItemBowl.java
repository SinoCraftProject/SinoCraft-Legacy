package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

@ModItem(name = "bowl")
public class ItemBowl extends Item
{
    public ItemBowl() {
        super(new Properties()
        .group(Groups.MISC)
        .maxStackSize(64));
    }
}
