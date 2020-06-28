package cx.rain.mc.forgemod.culturecraft.item.tmp;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "radish_white")
public class ItemRadishWhite extends Item {
    private static Food food = new Food.Builder()
            .hunger(4)
            .saturation(6)
            .build();

    public ItemRadishWhite() {
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .setNoRepair()
        );
    }
}
