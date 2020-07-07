package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.enumerate.LogType;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlockLeaves extends LeavesBlock {
    private LogType type = null;

    public BlockLeaves(LogType typeIn) {
        super(Properties.create(Material.LEAVES)
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0.2F)
                .tickRandomly()
                .notSolid()
        );

        type = typeIn;
    }
}
