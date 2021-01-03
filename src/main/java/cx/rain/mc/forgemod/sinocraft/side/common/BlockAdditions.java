package cx.rain.mc.forgemod.sinocraft.side.common;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;

public class BlockAdditions {
    public BlockAdditions() {
        addWoodStripping();
    }

    private void addWoodStripping() {
        ProtectedHelper.addAxeStrippingMap(ModBlocks.PEACH_LOG.get(), ModBlocks.PEACH_LOG_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(ModBlocks.WALNUT_LOG.get(), ModBlocks.WALNUT_LOG_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(ModBlocks.PLUM_LOG.get(), ModBlocks.PLUM_LOG_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(ModBlocks.MULBERRY_LOG.get(), ModBlocks.MULBERRY_LOG_STRIPPED.get());
    }
}
