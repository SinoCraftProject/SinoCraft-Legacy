package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraftforge.fluids.FluidStack;

import java.util.List;

public class ProviderHelper {

    public static void appendItemStack(ITextComponent component, ItemStack itemStack) {
        List<ITextComponent> siblings = component.getSiblings();
        siblings.add(itemStack.getDisplayName());
        int count = itemStack.getCount();
        if (count > 1) {
            siblings.add(new StringTextComponent(" * " + count));
        }
    }

    public static void appendFluidStack(ITextComponent component, FluidStack stack) {
        List<ITextComponent> siblings = component.getSiblings();
        siblings.add(stack.getDisplayName());
        int amount = stack.getAmount();
        if (amount % 1000 == 0) {
            siblings.add(new StringTextComponent(" " + (amount / 1000) + " B"));
        } else {
            siblings.add(new StringTextComponent(" " + amount + " mB"));
        }
    }
}
