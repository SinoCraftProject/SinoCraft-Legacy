package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

@ModItem(name = "seed_rice")
public class ItemSeedRice extends Item {
    public ItemSeedRice() {
        super(new Properties()
        .group(Groups.MISC)
        .maxStackSize(64));
    }
}
