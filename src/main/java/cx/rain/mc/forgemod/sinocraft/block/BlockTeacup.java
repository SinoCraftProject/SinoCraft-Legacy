package cx.rain.mc.forgemod.sinocraft.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class BlockTeacup extends Block {

    public static final BooleanProperty WITH_TEA = BooleanProperty.create("tea");
    public static final VoxelShape SHAPE = VoxelShapes.or(VoxelShapes.empty(),
            VoxelShapes.create(0.25, 0, 0.25, 0.75, 0.0625, 0.75),
            VoxelShapes.create(0.1875, 0.0625, 0.1875, 0.8125, 0.4375, 0.8125));

    public BlockTeacup() {
        super(Properties.create(Material.GLASS));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(WITH_TEA);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
