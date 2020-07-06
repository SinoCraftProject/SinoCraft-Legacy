package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;

@ModItem(name = "knife")
public class ItemKnife extends SwordItem {
    public ItemKnife(){
        super(ItemTier.IRON, 1, -1F, new Properties()
                .maxStackSize(1)
                .group(Groups.MISC));
    }
}