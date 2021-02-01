package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.base.BlockMachineBase;
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
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.IBooleanFunction;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockVat extends BlockMachineBase {
    protected static final VoxelShape OUT_SHAPE = VoxelShapes.fullCube();
    protected static final VoxelShape SHAPE = VoxelShapes.combineAndSimplify(
            OUT_SHAPE, Block.makeCuboidShape(
                    2.0D, 2.0D, 2.0D,
                    14.0D, 16.0D, 14.0D),
            IBooleanFunction.ONLY_FIRST);

    public BlockVat() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityVat();
    }

    @Override
    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (handIn != Hand.MAIN_HAND) {
            return ActionResultType.PASS;
        }
        if (worldIn.isRemote) {
            if (worldIn.getTileEntity(pos) instanceof TileEntityVat) {
                TileEntityVat tileEntity = (TileEntityVat) worldIn.getTileEntity(pos);
                if (FluidUtil.getFluidHandler(player.getHeldItem(handIn)).orElse(null) != null) {
                    IFluidHandler handler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);
                    FluidActionResult action = FluidUtil.tryEmptyContainer(player.getHeldItem(handIn), handler, 999999, player, true);
                    if (action.success) {
                        worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResultType.SUCCESS;
                    } else {
                        action = FluidUtil.tryFillContainer(player.getHeldItem(handIn), handler, 999999, player, true);
                        if (action.success) {
                            worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                            return ActionResultType.SUCCESS;
                        }
                    }
                    return ActionResultType.FAIL;
                } else {
                    IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
                    if (!player.getHeldItem(handIn).isEmpty()) {
                        ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                        ItemStack backup = stack;
                        backup = handler.insertItem(1, backup, true);
                        if (backup != stack) {
                            worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            return ActionResultType.SUCCESS;
                        } else {
                            return ActionResultType.FAIL;
                        }
                    } else {
                        if (!handler.getStackInSlot(0).isEmpty()) {
                            worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            return ActionResultType.SUCCESS;
                        } else if (!handler.getStackInSlot(1).isEmpty()) {
                            worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 1.0f, 1.0f);
                            return ActionResultType.SUCCESS;
                        }
                        return ActionResultType.FAIL;
                    }
                }
            }
            return ActionResultType.PASS;
        }
        return ActionResultType.PASS;
    }

    @Override
    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return ActionResultType.PASS;
        }
        if (worldIn.getTileEntity(pos) instanceof TileEntityVat) {
            TileEntityVat tileEntity = (TileEntityVat) worldIn.getTileEntity(pos);
            if (FluidUtil.getFluidHandler(player.getHeldItem(handIn)).orElse(null) != null) {
                IFluidHandler handler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);
                ItemStack stack = player.getHeldItem(handIn);
                FluidActionResult action = FluidUtil.tryEmptyContainer(stack, handler, 999999, player, true);
                if (action.success) {
                    worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                    return ActionResultType.SUCCESS;
                } else {
                    action = FluidUtil.tryFillContainer(stack, handler, 999999, player, true);
                    if (action.success) {
                        worldIn.playSound(player, pos, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResultType.SUCCESS;
                    }
                }
                return ActionResultType.FAIL;
            } else {
                IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
                if (handler != null) {
                    if (!player.getHeldItem(handIn).isEmpty()) {
                        ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                        ItemStack backup = stack;
                        backup = handler.insertItem(1, backup, false);
                        if (backup != stack) {
                            player.getHeldItem(handIn).shrink(1);
                            return ActionResultType.SUCCESS;
                        } else {
                            return ActionResultType.FAIL;
                        }
                    } else {
                        if (!handler.getStackInSlot(0).isEmpty()) {
                            ItemStack stack = new ItemStack(handler.getStackInSlot(0).getItem());
                            player.setHeldItem(handIn, stack);
                            handler.extractItem(0, 1, false);
                            return ActionResultType.SUCCESS;
                        } else if (!handler.getStackInSlot(1).isEmpty()) {
                            ItemStack stack = new ItemStack(handler.getStackInSlot(1).getItem());
                            player.setHeldItem(handIn, stack);
                            handler.extractItem(1, 1, false);
                            return ActionResultType.SUCCESS;
                        }
                        return ActionResultType.FAIL;
                    }
                } else {
                    return ActionResultType.FAIL;
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
