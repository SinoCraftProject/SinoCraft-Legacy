package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.material.Material;

public class BlockLeaves extends LeavesBlock {
    private TreeData type = null;

    public BlockLeaves(TreeData typeIn) {
        super(Properties.of(Material.LEAVES)
                .sound(SoundType.GRASS)
                .strength(0.2F)
                .randomTicks()
                .noOcclusion()
        );

        type = typeIn;
    }
}
