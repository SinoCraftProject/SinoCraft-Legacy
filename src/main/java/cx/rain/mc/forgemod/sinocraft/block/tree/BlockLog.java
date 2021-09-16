package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLog extends RotatedPillarBlock {
    private TreeType type;

    public BlockLog(TreeType typeIn) {
        super(Block.Properties.create(Material.WOOD, typeIn.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
    }

    public TreeType getType() {
        return type;
    }
}
