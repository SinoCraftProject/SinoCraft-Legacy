package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "milk_soybean")
public class ItemSoybeanMilk extends Item {
    private static Food food = new Food.Builder()
            .hunger(3)
            .saturation(6)
            .build();
    public ItemSoybeanMilk() {
        super(new Properties()
        .maxStackSize(1)
        .group(Groups.FOODS));
    }
}
