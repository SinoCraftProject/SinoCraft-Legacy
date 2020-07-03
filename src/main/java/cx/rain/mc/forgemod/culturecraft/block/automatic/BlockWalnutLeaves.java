package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

@ModBlock(name = "walnut_tree_leaves")
public class BlockWalnutLeaves extends Block {
    public BlockWalnutLeaves() {
        super(Properties.create(Material.LEAVES)
        .sound(SoundType.CROP)
        .harvestLevel(0)
        .hardnessAndResistance(0,2));
    }
}
