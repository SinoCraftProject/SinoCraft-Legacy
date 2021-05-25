package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.item.IKnife;
import cx.rain.mc.forgemod.sinocraft.api.item.IModGroups;
import cx.rain.mc.forgemod.sinocraft.api.item.IModItems;
import cx.rain.mc.forgemod.sinocraft.api.item.ISinoItems;

public enum APIItems implements ISinoItems {

    INSTANCE;

    @Override
    public IModItems getItems() {
        return APIModItems.INSTANCE;
    }

    @Override
    public IModGroups getGroups() {
        return APIGroups.INSTANCE;
    }

    @Override
    public IKnife getKnifeHelper() {
        return APIKnife.INSTANCE;
    }
}
