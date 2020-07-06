package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

@ModItem(name = "seed_soybean")
public class ItemSeedSoybean extends Item {
    public ItemSeedSoybean() {
        super(new Properties()
        .group(Groups.MISC)
        .maxStackSize(64));
    }
}
