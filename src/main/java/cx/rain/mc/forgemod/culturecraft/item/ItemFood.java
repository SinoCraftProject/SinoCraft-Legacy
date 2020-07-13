package cx.rain.mc.forgemod.culturecraft.item;

import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemFood extends Item {
    public ItemFood(Food food) {
        super(new Item.Properties()
                .group(Groups.FOODS)
                .food(food)
                .setNoRepair());
    }
}
