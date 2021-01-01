package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.enumerate.LogType;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLog extends RotatedPillarBlock {
    private LogType type;
    private LogState logState;

    public enum LogState {
        LOG,
        LOG_BARK,
        LOG_STRIPPED,
        LOG_STRIPPED_BARK
    }

    public BlockLog(LogType typeIn, LogState logStateIn) {
        super(Block.Properties.create(Material.WOOD, typeIn.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
        logState = logStateIn;
    }

    public LogType getType() {
        return type;
    }

    public LogState getLogState() {
        return logState;
    }
}
