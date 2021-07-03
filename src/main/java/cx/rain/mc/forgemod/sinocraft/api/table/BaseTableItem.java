package cx.rain.mc.forgemod.sinocraft.api.table;

import net.minecraft.item.Item;

/**
 * An item can place on a table.
 */
public abstract class BaseTableItem extends Item {

    public BaseTableItem(Properties properties) {
        super(properties);
    }

    /**
     * Create an element on table at x, y, z
     */
    public abstract BaseTableElement createElement(double x, double y, double z);
}
