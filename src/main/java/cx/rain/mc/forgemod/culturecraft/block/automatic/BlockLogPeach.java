package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.state.DirectionProperty;

@ModBlock(name = "log_peach")
public class BlockLogPeach extends LogBlock {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;
    public BlockLogPeach() {
        super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN)
                        .hardnessAndResistance(2.0F)
                        .sound(SoundType.WOOD));
    }
}
