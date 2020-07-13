package cx.rain.mc.forgemod.culturecraft.block.base;

import cx.rain.mc.forgemod.culturecraft.enumerate.MarbleType;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

public class BlockMarble extends Block {
    private MarbleType type = null;

    public BlockMarble(MarbleType typeIn) {
        super(Properties.create(Material.ROCK, typeIn.getColor())
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
        );

        type = typeIn;
    }
}
