package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.LogType;
import net.minecraft.block.*;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.fml.RegistryObject;

import java.util.Random;

public class BlockLeavesGrowable extends BlockLeaves implements IGrowable {
    public static BooleanProperty MATURE = BooleanProperty.create("mature");

    private LogType type = null;
    private RegistryObject<Item> fruit = null;

    public BlockLeavesGrowable(LogType typeIn, RegistryObject<Item> fruitRegistryIn) {
        super(typeIn);
        type = typeIn;
        fruit = fruitRegistryIn;

        setDefaultState(getStateContainer().getBaseState()
                .with(DISTANCE, 7)
                .with(PERSISTENT, false)
                .with(getMatureProperty(), false));
    }

    public static BooleanProperty getMatureProperty() {
        return MATURE;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(getMatureProperty());
    }

    @Override
    public boolean canGrow(IBlockReader iBlockReader, BlockPos blockPos, BlockState blockState, boolean b) {
        return !isMature(blockState);
    }

    @Override
    public boolean canUseBonemeal(World world, Random random, BlockPos blockPos, BlockState blockState) {
        return false;
    }

    @Override
    public void grow(ServerWorld world, Random rand, BlockPos pos, BlockState state) {
        setMature(world, pos, state, true);
    }

    public boolean isMature(BlockState state) {
        return state.get(getMatureProperty());
    }

    public void setMature(World world, BlockPos pos, BlockState state, boolean mature) {
        world.setBlockState(pos, state.with(getMatureProperty(), mature));
    }

    public boolean isPersistent(BlockState state) {
        return state.get(PERSISTENT);
    }

    @Override
    public boolean ticksRandomly(BlockState state) {
        return !isPersistent(state);
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        super.randomTick(state, worldIn, pos, rand);

        if (isMature(state)) {
            return;
        }

        if (isPersistent(state)) {
            return;
        }

        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, rand.nextInt(25) == 1)) {
            grow(worldIn, rand, pos, state);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
        }
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity,
                                             Hand hand, BlockRayTraceResult traceResult) {
        if (isMature(state)) {
            world.addEntity(new ItemEntity(world, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(fruit.get())));
            setMature(world, pos, state, false);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.FAIL;
    }
}
