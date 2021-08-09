package cx.rain.mc.forgemod.sinocraft.item.base;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.Color;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

import javax.annotation.Nullable;
import java.util.List;

public abstract class ItemTooltiped extends Item {
    protected int tooltipLines;

    public ItemTooltiped(Properties properties, int tooltipLineCount) {
        super(properties);

        tooltipLines = tooltipLineCount;
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);

        for (int i = 1; i <= tooltipLines; i++) {
            TranslationTextComponent component = new TranslationTextComponent("tooltip." + getRegistryName().getPath() + "." + i);
            component.setStyle(component.getStyle().setColor(Color.fromInt(TextFormatting.GRAY.getColor())));
            tooltip.add(component);
        }
    }
}
