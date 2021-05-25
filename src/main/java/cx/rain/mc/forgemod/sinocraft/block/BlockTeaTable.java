package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.item.ItemTeaTableElement;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.PushReaction;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

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
            Vector3d hitVec = hit.getHitVec();
            float x = (float) (pos.getX() - hitVec.x);
            float z = (float) (pos.getZ() - hitVec.z);
            ItemStack stack = player.getHeldItemMainhand();
            if (stack.isEmpty()) {
                player.setHeldItem(Hand.MAIN_HAND, table.take(x, z, false));
                return ActionResultType.SUCCESS;
            }
            Item item = stack.getItem();
            if (item instanceof ItemTeaTableElement) {
                if (table.put(x, z, stack)) {
                    stack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.FAIL;
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        TileEntity tileEntity = worldIn.getTileEntity(pos);
        if (tileEntity instanceof TileEntityTeaTable) {
            NonNullList<ItemStack> list = ((TileEntityTeaTable) tileEntity).removeAll();
            InventoryHelper.dropItems(worldIn, pos, list);
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
}
