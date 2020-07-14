package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.utility.ProtectedHelper;

public class BlockAdditions {
    public BlockAdditions() {
        addWoodStripping();
    }

    private void addWoodStripping() {
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_PEACH.get(), Blocks.LOG_PEACH_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_PEACH_SKIN.get(), Blocks.LOG_PEACH_STRIPPED_SKIN.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_WALNUT.get(), Blocks.LOG_WALNUT_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_WALNUT_SKIN.get(), Blocks.LOG_WALNUT_STRIPPED_SKIN.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_PLUM.get(), Blocks.LOG_PLUM_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_PLUM_SKIN.get(), Blocks.LOG_PLUM_STRIPPED_SKIN.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_MULBERRY.get(), Blocks.LOG_MULBERRY_STRIPPED.get());
        ProtectedHelper.addAxeStrippingMap(Blocks.LOG_MULBERRY_SKIN.get(), Blocks.LOG_MULBERRY_STRIPPED_SKIN.get());

    }
}
