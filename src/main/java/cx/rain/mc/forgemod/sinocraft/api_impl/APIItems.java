package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.api.item.*;
import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableItem;
import cx.rain.mc.forgemod.sinocraft.api.table.TableElementFactory;
import cx.rain.mc.forgemod.sinocraft.item.base.TableItem;

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

    @Override
    public BaseTableItem newTableItem(TableElementFactory factory) {
        return new TableItem() {
            @Override
            public BaseTableElement createElement(double x, double y, double z) {
                return factory.create(x, y, z);
            }
        };
    }
}
