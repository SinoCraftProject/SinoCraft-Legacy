package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.table.TableTeapot;
import cx.rain.mc.forgemod.sinocraft.capability.CapabilityTeapot;
import cx.rain.mc.forgemod.sinocraft.item.base.TableItem;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.Constants;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.server.ServerLifecycleHooks;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemTeapot extends TableItem {

    @Override
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, net.minecraft.client.util.ITooltipFlag flagIn) {
        super.addInformation(stack, worldIn, tooltip, flagIn);
        CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
        tooltip.add(new StringTextComponent("茶: " + teapot.getTea() + " mb"));
        tooltip.add(new StringTextComponent("水: " + teapot.getWater() + " mb"));
        tooltip.add(new StringTextComponent("茶叶: " + String.format("%.2f", teapot.getLeaves())));
    }

    @Nullable
    @Override
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundNBT nbt) {
        return new CapabilityTeapot.CapTeapot();
    }

    @Override
    public BaseTableElement createElement(double x, double y, double z) {
        return new TableTeapot(x, y, z);
    }

    @Nullable
    @Override
    public CompoundNBT getShareTag(ItemStack stack) {
        CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
//        new RuntimeException("log: ").printStackTrace();
        CompoundNBT shareTag = super.getShareTag(stack);
        if (FMLEnvironment.dist.isDedicatedServer()) {
            if (shareTag == null) {
                shareTag = new CompoundNBT();
            }
            if (teapot.isValid() && !teapot.isEmpty()) {
                CompoundNBT data = teapot.serializeNBT();
                data.putBoolean("pour", teapot.isPouring());
                shareTag.put("teapot", data);
            }
        }
        return shareTag;
    }

    @Override
    public void readShareTag(ItemStack stack, @Nullable CompoundNBT nbt) {
        if (nbt != null && nbt.contains("teapot", Constants.NBT.TAG_COMPOUND)) {
            CompoundNBT data = nbt.getCompound("teapot");
            CapabilityTeapot.CapTeapot teapot = CapabilityHelper.getTeapot(stack);
            teapot.deserializeNBT(data);
            teapot.setPouring(data.getBoolean("pour"));
            nbt.remove("teapot");
        }
        super.readShareTag(stack, nbt);
    }
}
