package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;

public class BlockSapling extends SaplingBlock {
    private TreeData type;

    public BlockSapling(TreeData typeIn, Tree treeIn) {
        super(treeIn, Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0.0F)
                .sound(SoundType.PLANT));
        type = typeIn;
    }
}
