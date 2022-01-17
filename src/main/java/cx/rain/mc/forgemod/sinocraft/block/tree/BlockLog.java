package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLog extends RotatedPillarBlock {

    protected final TreeData type;

    public BlockLog(TreeData type) {
        super(Block.Properties.create(Material.WOOD, type.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        this.type = type;
    }

    public TreeData getType() {
        return type;
    }
}
