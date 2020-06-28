package cx.rain.mc.forgemod.culturecraft.item.tmp;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "radish_summer")
public class ItemRadishSummer extends Item {
    private static Food food = new Food.Builder()
            .hunger(1)
            .saturation(2)
            .build();

    public ItemRadishSummer() {
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .setNoRepair()
        );
    }
}
