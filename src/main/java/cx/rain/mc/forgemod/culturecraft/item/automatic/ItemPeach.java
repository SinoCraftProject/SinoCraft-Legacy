package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "peach")
public class ItemPeach extends Item {
    private static Food food = new Food.Builder()
            .hunger(3)
            .saturation(5)
            .build();
    public ItemPeach(){
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .maxStackSize(64)
                .setNoRepair());
    }
}
