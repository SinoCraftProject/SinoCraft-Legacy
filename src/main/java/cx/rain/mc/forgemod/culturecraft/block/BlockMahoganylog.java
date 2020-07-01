package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.Rotation;
import net.minecraftforge.common.ToolType;


public class BlockMahoganylog extends Block {
    public BlockMahoganylog() {
        super(Properties.create(Material.WOOD)
                .harvestTool(ToolType.AXE)
                .hardnessAndResistance(3)
                .harvestLevel(0)
                .sound(SoundType.WOOD)
        );
    }
 }

