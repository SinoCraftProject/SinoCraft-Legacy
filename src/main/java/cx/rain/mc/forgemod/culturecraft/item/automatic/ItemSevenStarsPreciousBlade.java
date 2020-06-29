package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;

@ModItem(name = "seven_stars_precious_blade")
public class ItemSevenStarsPreciousBlade extends SwordItem {
    public ItemSevenStarsPreciousBlade() {
        super(ItemTier.IRON, 3, -2.4F, new Properties()
                .group(Groups.COMBAT)
                .maxStackSize(1));
    }
}
