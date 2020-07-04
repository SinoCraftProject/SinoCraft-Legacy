package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;

@ModItem(name = "chirping_wildgoose")
public class ItemChirpingWildGoose extends SwordItem {
    public ItemChirpingWildGoose() {
        super(ItemTier.DIAMOND, 9, -2.4F, new Item.Properties()
                .group(Groups.COMBAT)
                .setNoRepair()
                .maxStackSize(1));
    }
}