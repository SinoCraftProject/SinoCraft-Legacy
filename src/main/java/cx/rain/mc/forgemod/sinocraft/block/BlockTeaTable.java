package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class BlockTeaTable extends Block {

    public BlockTeaTable() {
        super(Properties.create(Material.WOOD));
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
                player.setHeldItem(Hand.MAIN_HAND, table.take(x, z));
                return ActionResultType.SUCCESS;
            }
            Item item = stack.getItem();
            if (item == ModItems.TEACUP_TEA.get() || item == ModItems.TEACUP_EMPTY.get()) {
                if (table.putTeacup(x, z, stack)) {
                    stack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            } else if (item == ModItems.TEAPOT.get()) {
                if (table.putTeapot(x, z, stack)) {
                    stack.shrink(1);
                    return ActionResultType.SUCCESS;
                }
            }
        }
        return ActionResultType.FAIL;
    }
}
