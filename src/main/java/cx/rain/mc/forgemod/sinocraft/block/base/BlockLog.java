package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogState;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogType;
import net.minecraft.block.Block;
import net.minecraft.block.RotatedPillarBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLog extends RotatedPillarBlock {
    private LogType type;
    private LogState state;

    public BlockLog(LogType typeIn, LogState stateIn) {
        super(Block.Properties.create(Material.WOOD, typeIn.getColor())
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
        type = typeIn;
        state = stateIn;
    }

    public LogType getType() {
        return type;
    }

    public LogState getLogState() {
        return state;
    }
}
