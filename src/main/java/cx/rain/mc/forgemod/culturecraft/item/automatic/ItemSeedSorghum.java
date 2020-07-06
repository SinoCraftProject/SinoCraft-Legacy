package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;
import org.lwjgl.system.CallbackI;

@ModItem(name = "seed_sorghum")
public class ItemSeedSorghum extends Item {
    public ItemSeedSorghum() {
        super(new Properties()
        .group(Groups.MISC)
        .maxStackSize(64));
    }
}
