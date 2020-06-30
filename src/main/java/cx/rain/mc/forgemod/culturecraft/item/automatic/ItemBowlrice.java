package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "bowl_rice")
public class ItemBowlrice extends Item {
    private static Food food = new Food.Builder()
            .hunger(6)
            .saturation(8)
            .build();

    public ItemBowlrice() {
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .maxStackSize(1)
                .setNoRepair()
                .containerItem(RegistryItem.ITEMS.get("plate"))
        );
    }
}