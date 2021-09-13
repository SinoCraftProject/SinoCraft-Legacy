package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.TreeType;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockPlank extends Block {
    private TreeType type = null;

    public BlockPlank(TreeType typeIn) {
        super(Properties.create(Material.WOOD)
                .harvestTool(ToolType.AXE)
                .hardnessAndResistance(2.0F, 3.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
    }
}
