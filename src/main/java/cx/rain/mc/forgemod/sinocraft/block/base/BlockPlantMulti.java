package cx.rain.mc.forgemod.sinocraft.block.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockPlantMulti extends BlockPlant {

    public static final BooleanProperty IS_TOP = BooleanProperty.create("top");

    protected BlockPlantMulti() {
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(IS_TOP);
    }

    @Override
    protected void grow(World worldIn, BlockPos pos, BlockState state, int age) {
        int maxHeight = getType().getMaxHeight();
        int maxAge = getMaxAge();
        int nextAge = maxAge + getAge(state);
        if (isTop(state)) {
            if (nextAge <= maxAge) {
                worldIn.setBlockState(pos, withAge(nextAge), 2);
                updateDown(worldIn, pos, nextAge);
            } else {
                int height = 1;
                while (worldIn.getBlockState(pos.down(height)).getBlock() == this) {
                    height++;
                }
                if (height < maxHeight) {
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

    public boolean isTop(BlockState state) {
        return state.get(IS_TOP);
    }

    private void updateDown(World worldIn, BlockPos pos, int age) {
        BlockPos down = pos.down();
        while (worldIn.getBlockState(down).getBlock() == this) {
            worldIn.setBlockState(down, withAge(age).with(IS_TOP, false), 2);
            down = down.down();
        }
    }
}