package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.block.ITileEntityPot;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
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
public class BlockPot extends Block {
    public static final double P1 = 1d / 16d;

    protected static final VoxelShape OUT_SHAPE = VoxelShapes.create(new AxisAlignedBB(P1, 0, P1, P1 * 15, P1 * 6, P1 * 15));
    protected static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(
            OUT_SHAPE, Block.makeCuboidShape(
                    2.0D, 2.0D, 2.0D,
                    14.0D, 6.0D, 14.0D),
            IBooleanFunction.ONLY_FIRST);

    public BlockPot() {
        super(Block.Properties.create(Material.IRON)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.ANVIL));
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityPot();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return clientActivated(state, worldIn, pos, player, handIn, hit);
        }
        else {
            return serverActivated(state, worldIn, pos, player, handIn, hit);
        }
    }

    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityPot) {
            ITileEntityPot tileEntity = (ITileEntityPot) te;
            ItemStack holdingStack = player.getHeldItem(handIn);

            if (!holdingStack.isEmpty()) {
                worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);

                return ActionResultType.SUCCESS;
            } else {
                if (tileEntity.getOutputs().isEmpty()) {
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

    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityPot) {
            ITileEntityPot tileEntity = (ITileEntityPot) te;
            ItemStack holdingStack = player.getHeldItem(handIn);

            if (!holdingStack.isEmpty()) {
                holdingStack.shrink(holdingStack.getCount() - tileEntity.insertInput(holdingStack));

                return ActionResultType.SUCCESS;
            } else {
                if (tileEntity.getOutputs().isEmpty()) {
                    if (player.isSneaking()) {
                        player.setHeldItem(handIn, tileEntity.extractInput());

                        return ActionResultType.SUCCESS;
                    }
                } else {
                    player.setHeldItem(handIn, tileEntity.extractOutput());

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
