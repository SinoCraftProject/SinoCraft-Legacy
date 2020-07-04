package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

@ModItem(name = "spirits")
public class ItemSpirits extends Item {
    public Food food = new Food.Builder()
            .hunger(2)
            .setAlwaysEdible()
            .saturation(3)
            .build();

    public ItemSpirits() {
        super(new Properties()
                .group(Groups.FOODS));
    }
}