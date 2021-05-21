package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.item.IKnife;
import cx.rain.mc.forgemod.sinocraft.api.item.IModItems;
import cx.rain.mc.forgemod.sinocraft.api.item.ISinoItems;
import cx.rain.mc.forgemod.sinocraft.api_impl.APIModItems;

public enum APIItems implements ISinoItems {
    INSTANCE;

    @Override
    public IModItems getItems() {
        return APIModItems.INSTANCE;
    }

    @Override
    public IKnife getKnifeHelper() {
        return APIKnife.INSTANCE;
    }
}
