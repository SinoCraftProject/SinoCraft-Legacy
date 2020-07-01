package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;


public class BlockMahoganyplank extends Block {

    public BlockMahoganyplank() {
        super(Properties.create(Material.WOOD)
        .harvestTool(ToolType.AXE)
        .harvestLevel(0)
        .hardnessAndResistance(3));
    }
}
