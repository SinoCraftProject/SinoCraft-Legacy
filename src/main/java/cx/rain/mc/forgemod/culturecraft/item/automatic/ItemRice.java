package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "rice")
public class ItemRice extends Item {
    public static Food food = new Food.Builder()
            .hunger(2)
            .saturation(3)
            .build();

    public ItemRice() {
        super(new Properties()
                .food(food)
                .maxStackSize(64)
                .group(Groups.FOODS)
        );
    }
}
