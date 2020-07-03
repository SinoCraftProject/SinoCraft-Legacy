package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;

@ModBlock(name = "log_walnut")
public class BlockLogWalnut extends LogBlock {
    public BlockLogWalnut() {
        super(MaterialColor.WOOD, Block.Properties.create(Material.WOOD, MaterialColor.OBSIDIAN)
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
    }
}
