package cx.rain.mc.forgemod.sinocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockTeapot extends Block {

    /**
     * 0 - teapot.json
     * 1 - teapot_without_lid.json
     * 2 - teapot_without_lid_with_tea.json
     */
    public static final IntegerProperty FLUID = IntegerProperty.create("fluid", 0, 2);
    public static final VoxelShape SHAPE0 = VoxelShapes.or(VoxelShapes.empty(),
            VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.0625, 0.75),
            VoxelShapes.create(0.25, 0.0625, 0.25, 0.75, 0.5, 0.75),
            VoxelShapes.create(0.25, 0.0625, 0.25, 0.75, 0.5, 0.75),
            VoxelShapes.create(0.25, 0.5, 0.25, 0.75, 0.5625, 0.75),
            VoxelShapes.create(0.3125, 0.5625, 0.3125, 0.6875, 0.625, 0.6875),
            VoxelShapes.create(0.4375, 0.625, 0.4375, 0.5625, 0.6875, 0.5625));
    public static final VoxelShape SHAPE1 = VoxelShapes.or(VoxelShapes.empty(),
            VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.0625, 0.75),
            VoxelShapes.create(0.25, 0.0625, 0.25, 0.75, 0.5, 0.75),
            VoxelShapes.create(0.25, 0.0625, 0.25, 0.75, 0.5, 0.75));

    public BlockTeapot() {
        super(Properties.create(Material.GLASS));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(FLUID);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(FLUID) == 0 ? SHAPE0 : SHAPE1;
    }
}
