package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import cx.rain.mc.forgemod.culturecraft.structure.tree.PeachTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

@ModBlock(name = "sapling_peach")
public class BlockSaplingPeach extends SaplingBlock {
    public BlockSaplingPeach() {
        super(new PeachTree(), Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0.0F)
                .sound(SoundType.PLANT));
    }
}
