package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

@ModItem(name = "green_pepper_shredded_meat")
public class ItemPepperGreenShreddedMeat extends Item {
    private static Food food = new Food.Builder()
            .hunger(7)
            .saturation(10)
            .build();

    public ItemPepperGreenShreddedMeat() {
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .maxStackSize(1)
                .setNoRepair()
                .containerItem(RegistryItem.ITEMS.get("plate"))
        );
    }
}