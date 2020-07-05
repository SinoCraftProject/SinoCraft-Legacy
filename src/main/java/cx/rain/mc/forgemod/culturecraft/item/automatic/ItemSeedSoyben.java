package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.common.Mod;

@ModItem(name = "seed_soyben")
public class ItemSeedSoyben extends Item {
    public ItemSeedSoyben() {
        super(new Properties()
        .group(Groups.MISC)
        .maxStackSize(64));
    }
}
