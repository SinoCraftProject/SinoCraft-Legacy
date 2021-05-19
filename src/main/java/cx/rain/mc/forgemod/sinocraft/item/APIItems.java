package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.api.item.IKnife;
import cx.rain.mc.forgemod.sinocraft.api.item.ISinoItems;

public enum APIItems implements ISinoItems {
    INSTANCE;

    @Override
    public IKnife getKnife() {
        return APIKnife.INSTANCE;
    }
}
