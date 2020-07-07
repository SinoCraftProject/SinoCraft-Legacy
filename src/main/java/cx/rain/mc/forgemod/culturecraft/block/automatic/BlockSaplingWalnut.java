package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import cx.rain.mc.forgemod.culturecraft.structure.tree.WalnutTree;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;

@ModBlock(name = "sapling_walnut")
public class BlockSaplingWalnut extends SaplingBlock {
    public BlockSaplingWalnut() {
        super(new WalnutTree(), Block.Properties.create(Material.PLANTS)
                .doesNotBlockMovement()
                .tickRandomly()
                .hardnessAndResistance(0.0F)
                .sound(SoundType.PLANT));
    }
}


