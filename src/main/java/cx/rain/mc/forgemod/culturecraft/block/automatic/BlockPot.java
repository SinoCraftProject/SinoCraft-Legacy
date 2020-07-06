package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

@ModBlock(name = "pot")
public class BlockPot extends Block {
    public BlockPot() {
        super(Properties.create(Material.ANVIL)
                .hardnessAndResistance(2.0F)
                .sound(SoundType.WOOD));
    }
}
