package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontal;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.VatFluidHandler;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.VatItemHandler;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
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

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockVat extends BlockHorizontal {
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

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityVat();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (!worldIn.isRemote()) {
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityVat) {
                VatFluidHandler vatFluidHandler = ((TileEntityVat) te).getFluidHandler();
                VatItemHandler vatItemHandler = ((TileEntityVat) te).getItemHandler();
                ItemStack heldItem = player.getHeldItem(handIn);
                if (heldItem.isEmpty()) {
                    if (player.isSneaking()) {
                        vatFluidHandler.clear();
                        return ActionResultType.SUCCESS;
                    } else {
                        ItemStack output = vatItemHandler.getStackInSlot(0);
                        if (!output.isEmpty()) {
                            vatItemHandler.setStackInSlot(0, ItemStack.EMPTY);
                            player.setHeldItem(handIn, output.copy());
                            return ActionResultType.SUCCESS;
                        }
                        ItemStack input = vatItemHandler.getStackInSlot(1);
                        if (!input.isEmpty()) {
                            vatItemHandler.setStackInSlot(1, ItemStack.EMPTY);
                            player.setHeldItem(handIn, input.copy());
                            return ActionResultType.SUCCESS;
                        }
                    }
                } else {
                    FluidActionResult empty = FluidUtil.tryEmptyContainer(heldItem, vatFluidHandler, 1000, player, true);
                    if (empty.success) {
                        setHeldItem(worldIn, pos, player, handIn, heldItem, empty);
                        return ActionResultType.SUCCESS;
                    }
                    FluidActionResult fill = FluidUtil.tryFillContainer(heldItem, vatFluidHandler, 1000, player, true);
                    if (fill.success) {
                        setHeldItem(worldIn, pos, player, handIn, heldItem, fill);
                        return ActionResultType.SUCCESS;
                    }
                    ItemStack insertItem = vatItemHandler.insertItem(1, heldItem, false);
                    player.setHeldItem(handIn, insertItem);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.SUCCESS;
    }

    private void setHeldItem(World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, ItemStack heldItem, FluidActionResult result) {
        ItemStack resultItem = result.getResult();
        if (heldItem.getCount() == 1) {
            player.setHeldItem(handIn, resultItem);
        } else {
            heldItem.shrink(1);
            if (!player.inventory.addItemStackToInventory(resultItem)) {
                spawnAsEntity(worldIn, pos, resultItem);
            }
        }
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
