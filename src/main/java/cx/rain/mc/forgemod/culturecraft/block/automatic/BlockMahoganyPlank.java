package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

@ModBlock(name = "mahogany_plank")
public class BlockMahoganyPlank extends Block {
    public BlockMahoganyPlank() {
        super(Properties.create(Material.WOOD)
        .harvestTool(ToolType.AXE)
        .harvestLevel(0)
        .hardnessAndResistance(3));
    }
}
