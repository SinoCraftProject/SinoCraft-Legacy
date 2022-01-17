package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockPlank extends Block {
    private TreeData type = null;

    public BlockPlank(TreeData typeIn) {
        super(Properties.create(Material.WOOD)
                .harvestTool(ToolType.AXE)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
    }
}
