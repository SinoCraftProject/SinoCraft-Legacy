package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;
@ModItem(name = "knife")
public class ItemKnife extends Item {
    public ItemKnife(){
        super(new Properties()
        .maxStackSize(1)
        .group(Groups.MISC));
    }
}
