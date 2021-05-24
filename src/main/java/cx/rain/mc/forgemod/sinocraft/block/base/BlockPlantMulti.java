package cx.rain.mc.forgemod.sinocraft.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockPlantMulti extends BlockPlant {

    public static final BooleanProperty IS_TOP = BooleanProperty.create("top");
    protected static final VoxelShape FULL_SHAPE = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D);

    protected BlockPlantMulti() {
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return isTop(state) ? super.getShape(state, worldIn, pos, context) : FULL_SHAPE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(IS_TOP);
    }

    @Override
    public void grow(World worldIn, BlockPos pos, BlockState state, int age) {
        int maxHeight = getType().getMaxHeight();
        int maxAge = getMaxAge();
        int nextAge = age + getAge(state);
        if (isTop(state)) {
            if (nextAge <= maxAge) {
                worldIn.setBlockState(pos, withAge(nextAge), 2);
                updateDown(worldIn, pos.down(), nextAge);
            } else {
                if (getHeight(worldIn, pos, state) < maxHeight) {
                    int upAge = Math.min(maxAge, nextAge - maxAge);
                    BlockPos up = pos.up();
                    if (worldIn.setBlockState(up, withAge(upAge).with(IS_TOP, true), 2)) {
                        updateDown(worldIn, pos, upAge);
                    } else {
                        worldIn.setBlockState(pos, withAge(maxAge).with(IS_TOP, true), 2);
                        updateDown(worldIn, pos.down(), maxAge);
                    }
                }
            }
        } else {
            BlockPos up = pos.up();
            BlockState top = worldIn.getBlockState(up);
            if (top.getBlock() == this) {
                grow(worldIn, up, top, age);
            } else {
                worldIn.setBlockState(up, Blocks.AIR.getDefaultState(), 2);
            }
        }
    }

    @Override
    public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        if (isMaxAge(state)) {
            if (isTop(state)) {
                return getHeight(worldIn, pos, state) < getType().getMaxHeight();
            } else {
                BlockPos up = pos.up();
                BlockState top = worldIn.getBlockState(up);
                if (top.getBlock() == this) {
                    return canGrow(worldIn, up, top, isClient);
                }
            }
        }
        return true;
    }

    @Override
    protected boolean canGrowTick(BlockState state, ServerWorld worldIn, BlockPos pos) {
        return isTop(state) && canGrow(worldIn, pos, state, false);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return isTop(state);
    }

    @Override
    protected boolean isValidGround(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return super.isValidGround(state, worldIn, pos) || (state.getBlock() == this && !isTop(state));
    }

    public int getHeight(IBlockReader worldIn, BlockPos pos, BlockState state) {
        if (isTop(state)) {
            int height = 1;
            while (worldIn.getBlockState(pos.down(height)).getBlock() == this) {
                height++;
            }
            return height;
        } else {
            BlockPos up = pos.up();
            BlockState top = worldIn.getBlockState(up);
            if (top.getBlock() == this) {
                return getHeight(worldIn, up, top);
            } else {
                return 1;
            }
        }
    }

    public boolean isTop(BlockState state) {
        return state.get(IS_TOP);
    }

    private void updateDown(World worldIn, BlockPos pos, int age) {
        while (worldIn.getBlockState(pos).getBlock() == this) {
            worldIn.setBlockState(pos, withAge(age).with(IS_TOP, false), 2);
            pos = pos.down();
        }
    }
}