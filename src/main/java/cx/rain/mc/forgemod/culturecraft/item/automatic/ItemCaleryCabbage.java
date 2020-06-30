package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "calery_cabbage")
public class ItemCaleryCabbage extends Item {
    public static Food food = new Food.Builder()
            .hunger(1)
            .saturation(3)
            .build();
    public ItemCaleryCabbage(){
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .maxStackSize(64));
    }
}
