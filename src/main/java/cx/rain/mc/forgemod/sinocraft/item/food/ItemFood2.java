package cx.rain.mc.forgemod.sinocraft.item.food;

import cx.rain.mc.forgemod.sinocraft.item.ModGroups;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemFood2 extends Item {

    private static Properties createFoodProperties(EnumFoods food) {
        Properties properties = new Properties()
                .group(ModGroups.AGRICULTURE)
                .food(new Food.Builder().hunger(food.hunger).saturation(food.saturation).build())
                .setNoRepair();
        if (food.maxStack > 0) {
            properties = properties.maxStackSize(food.maxStack);
        }
        return properties;
    }

    private final EnumFoods food;

    public ItemFood2(EnumFoods food) {
        super(createFoodProperties(food));
        this.food = food;
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        ItemStack onFinished = super.onItemUseFinish(stack, worldIn, entityLiving);
        if (food.container != ItemStack.EMPTY) {
            return food.container.copy();
        }
        return onFinished;
    }
}
