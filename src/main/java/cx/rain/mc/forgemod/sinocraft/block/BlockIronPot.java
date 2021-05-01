package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.base.BlockMachineBase;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityIronPot;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.Util;
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
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityIronPot();
    }

    @Override
    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) instanceof TileEntityIronPot) {
                TileEntityIronPot tileEntity = (TileEntityIronPot) worldIn.getTileEntity(pos);
                ItemStack holdingStack = player.getHeldItem(handIn);

                if (!holdingStack.isEmpty()) {
                    holdingStack.setCount(tileEntity.addStackToInput(holdingStack));

                    return ActionResultType.SUCCESS;
                } else {
                    if (tileEntity.getStackInSlot(7).isEmpty()) {
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
        }

        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) instanceof TileEntityIronPot) {
                TileEntityIronPot tileEntity = (TileEntityIronPot) worldIn.getTileEntity(pos);
                ItemStack holdingStack = player.getHeldItem(handIn);

                if (!holdingStack.isEmpty()) {
                    holdingStack.setCount(holdingStack.getCount() - tileEntity.addStackToInput(holdingStack));

                    return ActionResultType.SUCCESS;
                } else {
                    if (tileEntity.getStackInSlot(7).isEmpty()) {
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
        }

        return ActionResultType.PASS;
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
