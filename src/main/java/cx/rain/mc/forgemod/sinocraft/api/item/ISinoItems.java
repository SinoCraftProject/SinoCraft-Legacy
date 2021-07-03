package cx.rain.mc.forgemod.sinocraft.api.item;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableItem;
import cx.rain.mc.forgemod.sinocraft.api.table.TableElementFactory;
import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;

public interface ISinoItems {

    Lazy<ISinoItems> INSTANCE = new Lazy<>();

    /**
     * An instance to get all items from the mod.
     */
    IModItems getItems();

    /**
     * An instance to get all item groups from the mod.
     */
    IModGroups getGroups();

    /**
     * Get an instance to create knife recipe or get knife item by tier.
     */
    IKnife getKnifeHelper();

    /**
     * Create a BaseTableItem object with default implement.
     */
    BaseTableItem newTableItem(TableElementFactory factory);
}
