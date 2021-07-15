package cx.rain.mc.forgemod.sinocraft.data.modifier;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.data.modifier.serialize.SeedDropSerialize;
import net.minecraftforge.common.loot.GlobalLootModifierSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModModifiers {
    public static final DeferredRegister<GlobalLootModifierSerializer<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.LOOT_MODIFIER_SERIALIZERS, SinoCraft.MODID);

    public static RegistryObject<GlobalLootModifierSerializer<GrassDropModifier>> SEED_DROP = REGISTRY.register("seed_drop", SeedDropSerialize::new);

    public ModModifiers(IEventBus bus) {
        REGISTRY.register(bus);
        SinoCraft.getLogger().info("Registering loot modifiers");
    }
}