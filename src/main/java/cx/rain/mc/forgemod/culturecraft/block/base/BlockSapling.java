package cx.rain.mc.forgemod.culturecraft.block.base;

import cx.rain.mc.forgemod.culturecraft.enumerate.LogType;
import net.minecraft.block.Block;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;

public class BlockSapling extends SaplingBlock {
    private LogType type = null;

    public BlockSapling(LogType typeIn, Tree treeIn) {
        super(treeIn, Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0.0F)
                .sound(SoundType.PLANT));
        type = typeIn;
    }
}
