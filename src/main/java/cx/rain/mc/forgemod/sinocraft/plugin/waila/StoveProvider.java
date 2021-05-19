package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStove;
import cx.rain.mc.forgemod.sinocraft.utility.CapabilityHelper;
import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.awt.*;
import java.util.List;

public enum StoveProvider implements IWailaProvider {

    INSTANCE;

    public static ResourceLocation RENDERER = new ResourceLocation("sinocraft", "stove_tooltips");

    @Override
    public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityStove) {
            TileEntityStove stove = (TileEntityStove) tileEntity;
            if (stove.isBurning()) {
                data.putInt("burning", stove.getBurnTime());
            }
            data.putInt("heat", CapabilityHelper.getHeat(stove).getHeat());
        }
    }

    @Override
    public void appendTail(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        CompoundNBT serverData = accessor.getServerData();
        int heat = serverData.getInt("heat");
        tooltip.add(new StringTextComponent("Heat: " + heat));
        if (serverData.contains("burning", Constants.NBT.TAG_INT)) {
            tooltip.add(new StringTextComponent("BurningTime: " + serverData.getInt("burning")));
        }
    }

    @Override
    public Dimension getSize(CompoundNBT data, ICommonAccessor accessor) {
        return new Dimension(0, 0);
    }

    @Override
    public void draw(CompoundNBT data, ICommonAccessor accessor, int x, int y) {

    }
}
