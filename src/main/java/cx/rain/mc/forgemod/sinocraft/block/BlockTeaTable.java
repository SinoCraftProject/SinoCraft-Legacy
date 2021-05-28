package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.utility.property.CollectionHelper;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

import javax.annotation.Nullable;

public class BlockTeaTable extends Block {

    private static final Material MATERIAL = new Material(MaterialColor.AIR, false, true, false, false, false, false, PushReaction.DESTROY);

    public BlockTeaTable() {
        super(Properties.create(MATERIAL).zeroHardnessAndResistance().doesNotBlockMovement());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityTeaTable();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityTeaTable) {
            TileEntityTeaTable table = (TileEntityTeaTable) te;
            Vector3d look = player.getLook(1.0f);
            ItemStack heldItem = player.getHeldItem(handIn);
            if (heldItem.isEmpty()) {
                player.setHeldItem(handIn, table.take(look, hit.getHitVec()));
                return ActionResultType.SUCCESS;
            }
            return table.lookup(look, hit.getHitVec())
                    .map(element -> element.onActivated(state, worldIn, pos, player, handIn, hit))
                    .orElse(ActionResultType.FAIL);
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityTeaTable) {
            NonNullList<ItemStack> stacks = ((TileEntityTeaTable) tileEntity).elements()
                    .map(BaseTableElement::makeItem)
                    .collect(CollectionHelper.toNonnullList());
            InventoryHelper.dropItems(worldIn, pos, stacks);
        }
        super.onReplaced(state, worldIn, pos, newState, isMoving);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityTeaTable) {
            return ((TileEntityTeaTable) te).buildShape();
        }
        return VoxelShapes.empty();
    }

    public boolean canExist(@Nullable IBlockReader world, BlockPos pos) {
        if (world == null) return false;
        BlockPos down = pos.down();
        BlockState state = world.getBlockState(down);
        Block block = state.getBlock();
        if (block.isAir(state, world, down)) {
            return false;
        }
        if (!state.isSolid()) {
            return false;
        }
        boolean[] canExist = new boolean[] {false};
        VoxelShape shape = block.getCollisionShape(state, world, down);
        shape.forEachBox((x0, y0, z0, x1, y1, z1) -> {
            if (!canExist[0] && y1 == 1) {
                if (MathHelper.epsilonEquals(x0, 0)
                        && MathHelper.epsilonEquals(z0, 0)
                        && MathHelper.epsilonEquals(x1, 1)
                        && MathHelper.epsilonEquals(z1, 1)) {
                    canExist[0] = true;
                }
            }
        });
        return canExist[0];
    }

    @Override
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        super.neighborChanged(state, worldIn, pos, blockIn, fromPos, isMoving);
        if (fromPos.equals(pos.down())) {
            if (isMoving || !canExist(worldIn, fromPos)) {
                TileEntity tileEntity = worldIn.getTileEntity(pos);
                if (!(tileEntity instanceof TileEntityTeaTable)) {
                    worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
                    return;
                }
                TileEntityTeaTable table = (TileEntityTeaTable) tileEntity;
                NonNullList<ItemStack> droppedItems = table.elements()
                        .map(BaseTableElement::makeItem)
                        .collect(CollectionHelper.toNonnullList());
                InventoryHelper.dropItems(worldIn, pos, droppedItems);
                worldIn.setBlockState(pos, Blocks.AIR.getDefaultState());
            }
        }
    }
}
