package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.table.PlacedTableElement;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;

public class ItemTeaTableElement extends Item {

    private final ElementFactory factory;

    public ItemTeaTableElement(ItemGroup group, ElementFactory factory) {
        super(new Item.Properties().maxStackSize(1).group(group));
        this.factory = factory;
    }

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        World world = context.getWorld();
        BlockPos pos = context.getPos();
        BlockPos up = pos.up();
        TileEntity tileEntity = world.getTileEntity(up);
        if (!(tileEntity instanceof TileEntityTeaTable)) {
            if (TileEntityTeaTable.canExist(world, up)) {
                world.setBlockState(up, ModBlocks.TEA_TABLE.get().getDefaultState(), 2);
            }
        }
        tileEntity = world.getTileEntity(up);
        if (tileEntity instanceof TileEntityTeaTable) {
            Vector3d hitVec = context.getHitVec();
            if (((TileEntityTeaTable) tileEntity).put(hitVec.x - pos.getX(), hitVec.z - pos.getZ(), context.getItem())) {
                context.getItem().shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return super.onItemUse(context);
    }

    public PlacedTableElement createElement(double x, double z) {
        return factory.create(x, z);
    }

    @FunctionalInterface
    public interface ElementFactory {
        PlacedTableElement create(double x, double z);
    }
}
