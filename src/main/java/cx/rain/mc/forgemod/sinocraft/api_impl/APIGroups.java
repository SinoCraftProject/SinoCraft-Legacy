package cx.rain.mc.forgemod.sinocraft.api_impl;

import cx.rain.mc.forgemod.sinocraft.api.item.IModGroups;
import cx.rain.mc.forgemod.sinocraft.item.ModGroups;
import net.minecraft.item.ItemGroup;

public enum APIGroups implements IModGroups {

    INSTANCE;

    @Override
    public ItemGroup blocks() {
        return ModGroups.BLOCKS;
    }

    @Override
    public ItemGroup agriculture() {
        return ModGroups.AGRICULTURE;
    }

    @Override
    public ItemGroup misc() {
        return ModGroups.MISC;
    }
}
