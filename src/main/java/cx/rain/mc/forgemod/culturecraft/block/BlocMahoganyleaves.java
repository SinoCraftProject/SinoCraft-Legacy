package cx.rain.mc.forgemod.culturecraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;

public class BlocMahoganyleaves extends Block {
    public BlocMahoganyleaves() {
        super(Properties.create(Material.LEAVES)
        .harvestLevel(0)
        .hardnessAndResistance(1));
    }
}
