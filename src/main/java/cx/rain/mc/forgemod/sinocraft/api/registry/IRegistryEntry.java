package cx.rain.mc.forgemod.sinocraft.api.registry;

import net.minecraft.resources.ResourceLocation;

// Todo: qyl: There is another Registry. No implementation yet. 2022.1.22.
public interface IRegistryEntry<T> {
    ResourceLocation getRegistryKey();

    void setRegistryKey(ResourceLocation key);

    T getType();
}
