package cx.rain.mc.forgemod.sinocraft.plugin.waila;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.BlockPot;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.block.BlockVat;
import mcp.mobius.waila.api.IRegistrar;
import mcp.mobius.waila.api.IWailaPlugin;
import mcp.mobius.waila.api.TooltipPosition;
import mcp.mobius.waila.api.WailaPlugin;
import net.minecraft.util.ResourceLocation;

@WailaPlugin
public class WailaSupport implements IWailaPlugin {

    public static final ResourceLocation CONFIG_ENABLED = new ResourceLocation(SinoCraft.MODID, "enabled");

    @Override
    public void register(IRegistrar registrar) {
        registrar.addConfig(CONFIG_ENABLED, true);

        registerBlock(registrar, PotProvider.RENDERER, PotProvider.INSTANCE, BlockPot.class);
        registerBlock(registrar, VatProvider.RENDERER, VatProvider.INSTANCE, BlockVat.class);
        registerBlock(registrar, StoveProvider.RENDERER, StoveProvider.INSTANCE, BlockStove.class);
    }

    private void registerBlock(IRegistrar registrar, ResourceLocation tooltip, IWailaProvider provider, Class<?> block) {
        registrar.registerBlockDataProvider(provider, block);
        registrar.registerComponentProvider(provider, TooltipPosition.TAIL, block);
        registrar.registerTooltipRenderer(tooltip, provider);
    }
}
