package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.*;

@ModItem(name = "chirping_wildoose")
public class ItemChirpingWildoose extends SwordItem {
    public ItemChirpingWildoose() {
        super(ItemTier.DIAMOND, 9, -2.4F, new Item.Properties()
                .group(Groups.COMBAT)
                .setNoRepair()
                .maxStackSize(1));

}}