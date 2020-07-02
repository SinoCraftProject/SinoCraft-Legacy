package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;

@ModBlock(name = "mahogany_leaves")
public class BlockMahoganyLeaves extends Block {
    public BlockMahoganyLeaves() {
        super(Properties.create(Material.LEAVES)
        .harvestLevel(0)
        .hardnessAndResistance(1));
    }
}
