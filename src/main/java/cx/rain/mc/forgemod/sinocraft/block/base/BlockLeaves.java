package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.TreeType;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeaves extends LeavesBlock {
    private TreeType type = null;

    public BlockLeaves(TreeType typeIn) {
        super(Properties.create(Material.LEAVES)
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0.2F)
                .tickRandomly()
                .notSolid()
        );

        type = typeIn;
    }
}
