package cx.rain.mc.forgemod.sinocraft.side.common;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;

public class BlockAdditions {
    public BlockAdditions() {
        addWoodStripping();
    }

    private void addWoodStripping() {
        ProtectedHelper.addAxeStrippingMap(ModBlocks.LOG_PEACH.get(), ModBlocks.LOG_PEACH_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(ModBlocks.LOG_WALNUT.get(), ModBlocks.LOG_WALNUT_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(ModBlocks.LOG_PLUM.get(), ModBlocks.LOG_PLUM_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(ModBlocks.LOG_MULBERRY.get(), ModBlocks.LOG_MULBERRY_STRIPPED.get());
    }
}
