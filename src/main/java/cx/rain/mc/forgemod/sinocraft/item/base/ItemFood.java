package cx.rain.mc.forgemod.sinocraft.item.base;

import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;

public class ItemFood extends Item {
    public ItemFood(ItemGroup group, Food food) {
        super(new Item.Properties()
                .group(group)
                .food(food)
                .setNoRepair());
    }
}
