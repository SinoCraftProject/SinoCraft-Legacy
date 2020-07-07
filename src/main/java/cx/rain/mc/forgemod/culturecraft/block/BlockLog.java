package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.enumerate.LogType;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockLog extends LogBlock {
    private LogType type = null;

    public BlockLog(LogType typeIn) {
        super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, typeIn.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
    }
}
