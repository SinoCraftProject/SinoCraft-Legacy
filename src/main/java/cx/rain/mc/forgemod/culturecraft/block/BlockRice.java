package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.api.enumerate.RiceType;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockRice extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 17.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 19.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 21.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 24.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 27.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 30.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 32.0D, 16.0D),
    };

    private RiceType riceType = null;

    public BlockRice(RiceType type) {
        super(Block.Properties.from(Blocks.WHEAT));
        riceType = type;
        setDefaultState(getStateContainer().getBaseState().with(getAgeProperty(), 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int age = state.get(getAgeProperty());
        return SHAPE_BY_AGE[age];
    }

    @Override
    public IItemProvider getSeedsItem() {
        switch (riceType) {
            case RICE:
                return RegistryItem.ITEMS.get("seed_rice");
        }
        return null;
    }
}
