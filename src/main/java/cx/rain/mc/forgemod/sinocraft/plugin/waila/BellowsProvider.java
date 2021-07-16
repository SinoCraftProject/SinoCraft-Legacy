package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityBellows;
import cx.rain.mc.forgemod.sinocraft.capability.empty.NoWind;
import mcp.mobius.waila.api.ICommonAccessor;
import mcp.mobius.waila.api.IDataAccessor;
import mcp.mobius.waila.api.IPluginConfig;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.World;
import net.minecraftforge.common.util.Constants;

import java.awt.*;
import java.util.List;

import static cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityWindEnergy.CAPABILITY;

public enum BellowsProvider implements IWailaProvider {

    INSTANCE;

    public static ResourceLocation RENDERER = new ResourceLocation("sinocraft", "bellows_tooltips");

    @Override
    public void appendServerData(CompoundNBT data, ServerPlayerEntity player, World world, TileEntity tileEntity) {
        if (tileEntity instanceof TileEntityBellows) {
            TileEntityBellows bellows = (TileEntityBellows) tileEntity;
            data.putInt("burn_speed", bellows.getCapability(CAPABILITY).orElse(NoWind.INSTANCE).getWindEnergy());
            Direction linkedDirection = bellows.getLinkedDirection();
            if (linkedDirection != null) {
                data.putInt("direction", linkedDirection.getHorizontalIndex());
            }
        }
    }

    @Override
    public void appendTail(List<ITextComponent> tooltip, IDataAccessor accessor, IPluginConfig config) {
        CompoundNBT serverData = accessor.getServerData();
        tooltip.add(new StringTextComponent("Burn_Speed: " + serverData.getInt("burn_speed")));
        if (serverData.contains("direction", Constants.NBT.TAG_INT)) {
            Direction linkedDirection = Direction.byHorizontalIndex(serverData.getInt("direction"));
            tooltip.add(new StringTextComponent("Link: " + linkedDirection + "(" + accessor.getPosition().offset(linkedDirection) + ")"));
        } else {
            tooltip.add(new StringTextComponent("NOT LINKED"));
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
