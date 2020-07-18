package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemFood extends Item {
	private LogType type;

    public ItemFood(LogType typeIn) {
        super(new Item.Properties().group(Groups.MISC));
        this.type=typeIn;
    }
}
