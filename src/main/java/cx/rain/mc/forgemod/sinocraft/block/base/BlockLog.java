package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.enumerate.LogType;
import net.minecraft.block.Block;
import net.minecraft.block.LogBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

public class BlockLog extends LogBlock {
    public LogType type = null;

    public enum KIND{
        LOG,
        LOG_BARK,
        LOG_STRIPPED,
        LOG_STRIPPED_BARK
    }

    public KIND kind;

    public BlockLog(LogType typeIn,KIND kindIn) {
        super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, typeIn.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
        kind = kindIn;
    }
}
