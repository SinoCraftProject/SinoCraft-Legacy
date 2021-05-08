package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.item.base.ItemTooltiped;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class ItemHeroesAssemble extends ItemTooltiped {
    public ItemHeroesAssemble(ItemGroup group, Food food, int tooltipLineCount) {
        super(new Item.Properties()
                .group(group)
                .food(food)
                .setNoRepair()
                .maxStackSize(1), tooltipLineCount);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        return new ItemStack(ModItems.DISH.get());
    }
}
