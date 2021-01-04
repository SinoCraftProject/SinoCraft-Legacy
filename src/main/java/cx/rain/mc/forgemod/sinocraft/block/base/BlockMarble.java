package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.MarbleType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockMarble extends Block {
    private MarbleType type = null;

    public BlockMarble(MarbleType typeIn) {
        super(Properties.create(Material.ROCK, typeIn.getColor())
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .hardnessAndResistance(1.5F, 6.0F)
        );

        type = typeIn;
    }
}
