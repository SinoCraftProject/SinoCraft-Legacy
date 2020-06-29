package cx.rain.mc.forgemod.culturecraft.item.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.group.Groups;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
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

@ModItem(name = "heroes_assemble")
public class ItemHeroesAssemble extends Item {
    private static Food food = new Food.Builder()
            .hunger(6)
            .saturation(8)
            .build();

    public ItemHeroesAssemble() {
        super(new Properties()
                .group(Groups.FOODS)
                .food(food)
                .maxStackSize(1)
                .setNoRepair()
                .containerItem(RegistryItem.ITEMS.get("plate"))
        );
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
        tooltip.add(new TranslationTextComponent("tooltip.culturecraft.heroes_assemble")
                .setStyle(new Style().setColor(TextFormatting.GRAY)));
        super.addInformation(stack, worldIn, tooltip, flagIn);
    }
}
