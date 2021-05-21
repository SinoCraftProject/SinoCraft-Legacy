package cx.rain.mc.forgemod.sinocraft.api.item;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;

public interface ISinoItems {

    Lazy<ISinoItems> INSTANCE = new Lazy<>();

    /**
     * An instance to get all items from the mod.
     */
    IModItems getItems();

    /**
     * Get an instance to create knife recipe or get knife item by tier.
     */
    IKnife getKnifeHelper();
}
