package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTier;
import net.minecraft.item.SwordItem;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

import javax.annotation.Nullable;
import java.util.List;

@ModItem(name = "seven_stars_precious_blade")
public class ItemSevenStarsPreciousBlade extends SwordItem {
    public ItemSevenStarsPreciousBlade() {
        super(ItemTier.IRON, 6, -2.4F, new Item.Properties()
                .group(Groups.COMBAT)
                .setNoRepair()
                .maxStackSize(1));
    }
    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.culturecraft.seven_stars_precious_blade")
                .setStyle(new Style().setColor(TextFormatting.GRAY)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
