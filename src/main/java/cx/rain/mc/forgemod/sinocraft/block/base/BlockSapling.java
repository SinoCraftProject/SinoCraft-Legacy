package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.TreeType;
import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;

public class BlockSapling extends SaplingBlock {
    private TreeType type;

    public BlockSapling(TreeType typeIn, Tree treeIn) {
        super(treeIn, Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0.0F)
                .sound(SoundType.PLANT));
        type = typeIn;
    }
}
