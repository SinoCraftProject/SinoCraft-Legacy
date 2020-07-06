package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.enumerate.PepperType;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockPepper extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
    };

    private PepperType pepperType = null;

    protected BlockPepper(PepperType type) {
        super(Block.Properties.from(Blocks.CARROTS));
        pepperType = type;
        setDefaultState(getStateContainer().getBaseState().with(getAgeProperty(), 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int age = state.get(getAgeProperty());
        int stage = age / 2;
        return SHAPE_BY_AGE[stage];

    }

    @Override
    public IItemProvider getSeedsItem() {
        switch (pepperType) {
            case CHILI:
                return Items.PEPPER_CHILI_SEED.get();
            case GREEN:
                return Items.PEPPER_GREEN_SEED.get();
        }
        return null;

    }

}