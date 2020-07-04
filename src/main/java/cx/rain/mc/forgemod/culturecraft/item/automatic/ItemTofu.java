package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

@ModItem(name = "tofu")
public class ItemTofu extends Item {
    private static Food food = new Food.Builder()
            .hunger(2)
            .saturation(3)
            .build();

    public ItemTofu() {
        super(new Properties().food(food).group(Groups.FOODS));
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.culturecraft.tofu")
                .setStyle(new Style().setColor(TextFormatting.GRAY)));
        tooltip.add(new TranslationTextComponent("tooltip.culturecraft.tofu.2")
                .setStyle(new Style().setColor(TextFormatting.GRAY)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
