package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.item.base.ItemTooltiped;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;

public class ItemHeroesAssemble extends ItemTooltiped {

    public ItemHeroesAssemble() {
        super(new Item.Properties()
                .group(ModGroups.AGRICULTURE)
                .food(new Food.Builder().hunger(5).saturation(6).build())
                .setNoRepair()
                .maxStackSize(1), 1);
    }

    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, LivingEntity entityLiving) {
        super.onItemUseFinish(stack, worldIn, entityLiving);
        entityLiving.addPotionEffect(new EffectInstance(Effects.STRENGTH, 10, 1));
        entityLiving.addPotionEffect(new EffectInstance(Effects.REGENERATION, 10, 1));
        return new ItemStack(ModItems.DISH.get());
    }
}
