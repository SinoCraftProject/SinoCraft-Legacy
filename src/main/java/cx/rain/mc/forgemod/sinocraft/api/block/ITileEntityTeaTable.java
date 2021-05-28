package cx.rain.mc.forgemod.sinocraft.api.block;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;

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
     * Try take and remove an element by ray, and return its ItemStack
     * @param startVec start, usually is entity's eye position.
     * @param endVec target, like {@link BlockRayTraceResult#getHitVec()}
     */
    ItemStack take(Vector3d startVec, Vector3d endVec);

    /**
     * Lookup an element from the table.
     * @param startVec start, usually is entity's eye position.
     * @param endVec target, like {@link BlockRayTraceResult#getHitVec()}
     */
    Optional<BaseTableElement> lookup(Vector3d startVec, Vector3d endVec);

    /**
     * Lookup a special typed element from the table.
     * @param startVec start, usually is entity's eye position.
     * @param endVec target, like {@link BlockRayTraceResult#getHitVec()}
     */
    <T extends BaseTableElement> Optional<T> lookup(Vector3d startVec, Vector3d endVec, Class<T> type);

    /**
     * Get a stream contains all elements
     */
    Stream<BaseTableElement> elements();

    /**
     * Get the table's shape
     */
    VoxelShape buildShape();
}
