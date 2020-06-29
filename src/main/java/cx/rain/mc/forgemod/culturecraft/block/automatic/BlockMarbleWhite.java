package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.common.ToolType;

@ModBlock(name = "marble_white")
public class BlockMarbleWhite extends Block {
    public BlockMarbleWhite() {
        super(Properties.create(Material.ROCK, MaterialColor.WHITE_TERRACOTTA)
                .harvestTool(ToolType.PICKAXE)
                .harvestLevel(2)
                .hardnessAndResistance(1.5F, 6.0F)
        );
    }
}
