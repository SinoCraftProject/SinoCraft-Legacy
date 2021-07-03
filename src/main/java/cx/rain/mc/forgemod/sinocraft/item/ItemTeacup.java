package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.table.TableTeacup;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeacup;
import cx.rain.mc.forgemod.sinocraft.item.base.TableItem;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemTeacup extends TableItem {

    @Override
    public BaseTableElement createElement(double x, double y, double z) {
        return new TableTeacup(x, y, z);
    }

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, net.minecraft.client.util.ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CapabilityTeacup.CapTeacup teacup = CapabilityHelper.getTeacup(stack);
        tooltip.add(new StringTextComponent("茶: " + teacup.getTea() + "/" + teacup.getCapacity() + " mb"));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new CapabilityTeacup.CapTeacup();
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        if (nbt != null && nbt.contains("teacup", Constants.NBT.TAG_COMPOUND)) {
            CapabilityHelper.getTeacup(stack).deserializeNBT(nbt.getCompound("teacup"));
        }
        super.readShareTag(stack, nbt);
    }

    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CompoundNBT nbt = super.getShareTag(stack);
        if (nbt == null) {
            nbt = new CompoundNBT();
        }
        nbt.put("teacup", CapabilityHelper.getTeacup(stack).serializeNBT());
        return nbt;
    }
}
