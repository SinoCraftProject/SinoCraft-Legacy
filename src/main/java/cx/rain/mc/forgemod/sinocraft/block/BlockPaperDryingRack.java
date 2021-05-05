package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontalActivatable;
import cx.rain.mc.forgemod.sinocraft.block.base.IBlockActivatable;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

@SuppressWarnings("deprecation")
public class BlockPaperDryingRack extends BlockHorizontalActivatable implements IBlockActivatable {
    /**
     * State is a int value shows drying rack's state.
     * 0: There is no paper drying.
     * 1: Paper is drying.
     * 2: Paper drying is finished.
     */
    public static IntegerProperty STATE = IntegerProperty.create("state", 0, 2);
    public static final DirectionProperty FACING = DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);

    public BlockPaperDryingRack() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD).notSolid());
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(STATE);
    }

    @Override
    public boolean ticksRandomly(BlockState p_149653_1_) {
        return true;
    }

    @Override
    public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(STATE) == 1) {
            world.setBlockState(pos, state.with(STATE, 2));
        }
    }

    @Override
    public ActionResultType onClientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack handStack = player.getHeldItem(handIn);
        if (state.get(STATE) == 0 && handStack.getItem() == ModItems.BUCKET_WOOD_PULP.get()) {
            setState(worldIn, pos, state, 1);
            worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
            return ActionResultType.SUCCESS;
        }

        if (state.get(STATE) == 2) {
            setState(worldIn, pos, state, 0);
            worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType onServerActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack handStack = player.getHeldItem(handIn);
        if (state.get(STATE) == 0 && handStack.getItem() == ModItems.BUCKET_WOOD_PULP.get()) {
            setState(worldIn, pos, state, 1);
            worldIn.getPendingBlockTicks().scheduleTick(pos, this, 20 * 15);
            player.setHeldItem(handIn, new ItemStack(Items.BUCKET));
            return ActionResultType.SUCCESS;
        }

        if (state.get(STATE) == 2) {
            setState(worldIn, pos, state, 0);
            worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.EMPTY_XUAN_PAPER.get())));
            return ActionResultType.SUCCESS;
        }
        return ActionResultType.PASS;
    }

    public void setState(World world, BlockPos pos, BlockState state, int stateValue) {
        world.setBlockState(pos, state.with(STATE, stateValue));
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.get(STATE) == 2) {
            if (worldIn.isRemote) {
                worldIn.addEntity(new ItemEntity(worldIn, pos.getX(), pos.getY(), pos.getZ(), new ItemStack(ModItems.EMPTY_XUAN_PAPER.get())));
            }
        }
    }
}
