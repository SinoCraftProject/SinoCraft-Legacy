package cx.rain.mc.forgemod.culturecraft.item.tmp;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "radish_green")
public class ItemRadishGreen extends Item {
    private static Food food = new Food.Builder()
            .hunger(3)
            .saturation(6)
            .build();

    public ItemRadishGreen() {
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .setNoRepair()
        );
    }
}
