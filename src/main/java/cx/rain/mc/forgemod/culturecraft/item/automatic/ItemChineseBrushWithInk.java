package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Item;

@ModItem(name = "chinese_brush_with_ink")
public class ItemChineseBrushWithInk extends Item {
    public ItemChineseBrushWithInk() {
        super(new Properties()
                .group(Groups.MISC)
                .maxStackSize(1));
    }
}
