package cx.rain.mc.forgemod.sinocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;

public class BlockTeapot extends Block {

    /**
     * 0 - teapot.json
     * 1 - teapot_without_lid.json
     * 2 - teapot_without_lid_with_tea.json
     */
    public static final IntegerProperty FLUID = IntegerProperty.create("fluid", 0, 2);

    public BlockTeapot() {
        super(Properties.create(Material.GLASS));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FLUID);
    }
}
