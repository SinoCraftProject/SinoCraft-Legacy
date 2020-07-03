package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

@ModBlock(name = "leaves_peach")
public class BlockLeavesPeach extends LeavesBlock {
    public BlockLeavesPeach() {
        super(Properties.create(Material.LEAVES)
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0.2F)
                .tickRandomly()
                .notSolid());
    }
}
