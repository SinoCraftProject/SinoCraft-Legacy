package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import mcp.mobius.waila.api.IComponentProvider;
import mcp.mobius.waila.api.IServerDataProvider;
import mcp.mobius.waila.api.ITooltipRenderer;
import net.minecraft.tileentity.TileEntity;

public interface IWailaProvider extends IServerDataProvider<TileEntity>, IComponentProvider, ITooltipRenderer {
}
