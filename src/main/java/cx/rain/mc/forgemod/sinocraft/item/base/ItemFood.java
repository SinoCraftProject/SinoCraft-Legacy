package cx.rain.mc.forgemod.sinocraft.item.base;

import cx.rain.mc.forgemod.sinocraft.item.ModGroups;
import net.minecraft.item.Food;
import net.minecraft.item.Item;

public class ItemFood extends Item {
    
    public ItemFood(int hunger, float saturation, int maxStack) {
        super(new Item.Properties()
                .group(ModGroups.AGRICULTURE)
                .food(new Food.Builder().hunger(hunger).saturation(saturation).build())
                .maxStackSize(maxStack)
                .setNoRepair());
    }
    public ItemFood(int hunger, float saturation) {
        super(new Item.Properties()
                .group(ModGroups.AGRICULTURE)
                .food(new Food.Builder().hunger(hunger).saturation(saturation).build())
                .setNoRepair());
    }

}
