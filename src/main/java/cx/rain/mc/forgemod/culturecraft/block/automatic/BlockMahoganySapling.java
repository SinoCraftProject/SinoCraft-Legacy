package cx.rain.mc.forgemod.culturecraft.block.automatic;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.block.trees.Tree;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraftforge.event.world.SaplingGrowTreeEvent;

import java.util.Properties;
import java.util.Random;

@ModBlock(name = "mahogany_sapling")
public class BlockMahoganySapling extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
};

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int age = state.get(getAgeProperty());
        int stage = age / 1;
        return SHAPE_BY_AGE[stage];
    }
    protected BlockMahoganySapling(Properties builder) {
        super(Block.Properties.from(Blocks.ACACIA_SAPLING));
        setDefaultState(getStateContainer().getBaseState().with(getAgeProperty(), 0));
    }
}
