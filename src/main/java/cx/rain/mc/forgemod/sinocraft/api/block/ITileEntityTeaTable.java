package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.shapes.VoxelShape;

import java.util.Optional;
import java.util.stream.Stream;

public interface ITileEntityTeaTable {

    /**
     * Try put an element to this table.
     *
     * @param element element item must extends BaseTableItem
     * @return True if element can be placed on the table.
     */
    boolean put(ItemUseContext context, double x, double z, ItemStack element);

    /**
     * Try take and remove an element and return its ItemStack
     */
    ItemStack take(double x, double y, double z);

    /**
     * Lookup an element from the table.
     */
    Optional<BaseTableElement> lookup(double x, double y, double z);

    /**
     * Lookup a special typed element from the table.
     */
    <T extends BaseTableElement> Optional<T> lookup(double x, double y, double z, Class<T> type);

    /**
     * Get a stream contains all elements
     */
    Stream<BaseTableElement> elements();

    /**
     * Get the table's shape
     */
    VoxelShape buildShape();
}
