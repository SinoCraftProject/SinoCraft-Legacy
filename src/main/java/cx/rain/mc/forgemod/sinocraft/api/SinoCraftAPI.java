package cx.rain.mc.forgemod.sinocraft.api;

import cx.rain.mc.forgemod.sinocraft.api.block.ISinoBlocks;
import cx.rain.mc.forgemod.sinocraft.api.crafting.ISinoRecipes;
import cx.rain.mc.forgemod.sinocraft.api.item.ISinoItems;

public class SinoCraftAPI {

    public static ISinoRecipes getRecipes() {
        return ISinoRecipes.INSTANCE.get();
    }

    public static ISinoItems getItems() {
        return ISinoItems.INSTANCE.get();
    }

    public static ISinoBlocks getBlocks() {
        return ISinoBlocks.INSTANCE.get();
    }
}
