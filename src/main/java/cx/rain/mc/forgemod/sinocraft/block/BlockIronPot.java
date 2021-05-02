package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.base.BlockMachineBase;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IMachine;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityIronPot;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

/**
 * @author NmmOC7
 */
public class BlockIronPot extends BlockMachineBase {
    protected static final VoxelShape OUT_SHAPE = VoxelShapes.create(new AxisAlignedBB(0, 0, 0, 1, 0.5, 1));
    protected static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(
            OUT_SHAPE, Block.makeCuboidShape(
                    2.0D, 2.0D, 2.0D,
                    14.0D, 8.0D, 14.0D),
            IBooleanFunction.ONLY_FIRST);

    public BlockIronPot() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.ANVIL));

        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(STATE, IMachine.MachineState.CLOSE));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityIronPot();
    }

    @Override
    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityIronPot) {
            TileEntityIronPot tileEntity = (TileEntityIronPot) worldIn.getTileEntity(pos);
            ItemStack holdingStack = player.getHeldItem(handIn);

            if (!holdingStack.isEmpty()) {
                worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);

                return ActionResultType.SUCCESS;
            } else {
                if (tileEntity.getOutput().isEmpty()) {
                    if (player.isSneaking()) {
                        worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);

                        return ActionResultType.SUCCESS;
                    }
                } else {
                    worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);

                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.FAIL;
    }

    @Override
    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityIronPot) {
            TileEntityIronPot tileEntity = (TileEntityIronPot) worldIn.getTileEntity(pos);
            ItemStack holdingStack = player.getHeldItem(handIn);

            if (!holdingStack.isEmpty()) {
                holdingStack.shrink(holdingStack.getCount() - tileEntity.addStackToInput(holdingStack));

                return ActionResultType.SUCCESS;
            } else {
                if (tileEntity.getStackInSlot(6).isEmpty()) {
                    if (player.isSneaking()) {
                        player.setHeldItem(handIn, tileEntity.removeStackOnInput());

                        return ActionResultType.SUCCESS;
                    }
                } else {
                    player.setHeldItem(handIn, tileEntity.removeStackOnOutput());

                    return ActionResultType.SUCCESS;
                }
            }
        }

        return ActionResultType.FAIL;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public VoxelShape getRaytraceShape(BlockState state, IBlockReader worldIn, BlockPos pos) {
        return OUT_SHAPE;
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
