package cx.rain.mc.forgemod.sinocraft.api.item;

import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;

public interface ISinoItems {

    Lazy<ISinoItems> INSTANCE = new Lazy<>();

    IModItems getItems();

    IKnife getKnifeHelper();
}
