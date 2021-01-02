package cx.rain.mc.forgemod.sinocraft.item.base;

import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemFood extends Item {
    public ItemFood(Food food) {
        super(new Item.Properties()
                .group(ModGroups.FOODS)
                .food(food)
                .setNoRepair());
    }
}
