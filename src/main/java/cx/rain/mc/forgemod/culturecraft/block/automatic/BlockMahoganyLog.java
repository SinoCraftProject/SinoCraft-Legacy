package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraftforge.common.ToolType;

@ModBlock(name = "mahogany_log")
public class BlockMahoganyLog extends Block {
    public BlockMahoganyLog() {
        super(Properties.create(Material.WOOD)
                .harvestTool(ToolType.AXE)
                .hardnessAndResistance(3)
                .harvestLevel(0)
                .sound(SoundType.WOOD)
        );
    }
 }

