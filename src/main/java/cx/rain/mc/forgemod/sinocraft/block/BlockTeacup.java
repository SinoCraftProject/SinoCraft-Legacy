package cx.rain.mc.forgemod.sinocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;

public class BlockTeacup extends Block {

    public static final BooleanProperty WITH_TEA = BooleanProperty.create("tea");

    public BlockTeacup() {
        super(Properties.create(Material.GLASS));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(WITH_TEA);
    }
}
