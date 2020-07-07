package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.enumerate.LogType;
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockLeavesGrowable extends LeavesBlock implements IGrowable {
    public static BooleanProperty MATURE = BooleanProperty.create("mature");

    private LogType type = null;
    private ItemStack fruit = null;

    public BlockLeavesGrowable(LogType typeIn, ItemStack fruitIn) {
        super(Properties.create(Material.LEAVES)
                .sound(SoundType.PLANT)
                .hardnessAndResistance(0.2F)
                .tickRandomly()
                .notSolid()
        );
        type = typeIn;
        fruit = fruitIn;

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
        return true;
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
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random rand) {
        super.tick(state, world, pos, rand);

        if (isMature(state)) {
            return;
        }

        if (isPersistent(state)) {
            return;
        }

        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(world, pos, state, rand.nextInt(15) == 1)) {
            grow(world, rand, pos, state);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(world, pos, state);
        }
    }

    @Override
    public void onBlockClicked(BlockState state, World world, BlockPos pos, PlayerEntity playerEntity) {
        super.onBlockClicked(state, world, pos, playerEntity);

        if (isMature(state)) {
            playerEntity.dropItem(fruit, true, false);
            setMature(world, pos, state, false);
        }
    }
}
