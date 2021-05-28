package cx.rain.mc.forgemod.sinocraft.item.base;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableItem;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public abstract class TableItem extends BaseTableItem {

    public TableItem() {
        super(new Item.Properties().setNoRepair().maxStackSize(1).group(ModGroups.MISC));
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockPos up = pos.up();
        TileEntity tileEntity = world.getTileEntity(up);
        if (!(tileEntity instanceof TileEntityTeaTable)) {
            if (ModBlocks.TEA_TABLE.get().canExist(world, up)) {
                world.setBlockState(up, ModBlocks.TEA_TABLE.get().getDefaultState(), 2);
            }
        }
        tileEntity = world.getTileEntity(up);
        if (tileEntity instanceof TileEntityTeaTable) {
            Vector3d hitVec = context.getHitVec();
            if (((TileEntityTeaTable) tileEntity).put(context, hitVec.x - pos.getX(), hitVec.z - pos.getZ(), context.getItem())) {
                context.getItem().shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onItemUse(context);
    }
}
