package cx.rain.mc.forgemod.sinocraft.item.food;

import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public enum EnumFoods {
    PEACH(2, 3, -1, ItemStack.EMPTY),
    CHILI_PEPPER(1, 2, -1, ItemStack.EMPTY),
    GREEN_PEPPER(2, 3, -1, ItemStack.EMPTY),
    EGGPLANT(2, 2, -1, ItemStack.EMPTY),
    CABBAGE(2, 2, -1, ItemStack.EMPTY),
    ADUST_FOOD(1, 0, -1, ItemStack.EMPTY),
    COOKED_DUMPLING(6, 0.2f, -1, ItemStack.EMPTY),

    BOWL_WITH_RICE(8, 4, 1, new ItemStack(Items.BOWL)),
    BOWL_WITH_WATER(0, 0, 1, new ItemStack(Items.BOWL)),
    BOWL_WITH_PORRIDGE(6, 2, 1, new ItemStack(Items.BOWL)),
    ;

    public final float saturation;
    public final int hunger, maxStack;
    public final ItemStack container;

    EnumFoods(int hunger, float saturation, int maxStack, ItemStack container) {
        this.saturation = saturation;
        this.hunger = hunger;
        this.maxStack = maxStack;
        this.container = container;
    }
}
