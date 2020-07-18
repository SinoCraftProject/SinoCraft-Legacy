package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.Groups;
import cx.rain.mc.forgemod.sinocraft.enumerate.LogType;
import net.minecraft.item.Item;

public class ItemFood extends Item {
	private LogType type;

    public ItemFood(LogType typeIn) {
        super(new Item.Properties().group(Groups.MISC));
        this.type=typeIn;
    }
}
