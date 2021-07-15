package cx.rain.mc.forgemod.sinocraft.entity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.item.ModSpawnEggItem;
import cx.rain.mc.forgemod.sinocraft.utility.Constructor1;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class EntityRegister {

    public static final Map<EntityType<?>, SpawnEggItem> EGGS = ProtectedHelper.getStaticField(SpawnEggItem.class, "field_195987_b");

    private final DeferredRegister<Item> eggRegister;
    public final DeferredRegister<EntityType<?>> entityRegister = DeferredRegister.create(ForgeRegistries.ENTITIES, SinoCraft.MODID);
    public final Map<String, RegistryEntry<?>> entries = new HashMap<>();

    public EntityRegister(DeferredRegister<Item> eggRegister) {
        this.eggRegister = eggRegister;
    }

    public void register(IEventBus bus) {
        entityRegister.register(bus);
    }

    public <E extends Entity> RegistryObject<EntityType<E>> register(String name, String en, String zh, int primaryColor, int secondaryColor, Supplier<EntityType.Builder<E>> sup) {
        RegistryEntry<E> entry = new RegistryEntry<>(en, zh);
        entries.put(name, entry);
        entry.typeObject = entityRegister.register(name, () -> {
            entry.type = sup.get().build(name);
            if (entry.egg == null) {
                SinoCraft.getLogger().warn("No egg for " + name);
            } else {
                assert EGGS != null;
                EGGS.put(entry.type, entry.egg);
            }
            return entry.type;
        });
        entry.eggObject = eggRegister.register("spawn_egg_" + name, () -> entry.egg = new ModSpawnEggItem(entry, primaryColor, secondaryColor));
        return entry.typeObject;
    }

    @OnlyIn(Dist.CLIENT)
    public void registerRenderers() {
        entries.forEach((name, entry) -> net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(entry.type,
                (net.minecraftforge.fml.client.registry.IRenderFactory<Entity>) manager ->
                        (net.minecraft.client.renderer.entity.EntityRenderer) new Constructor1(
                                "cx.rain.mc.forgemod.sinocraft.client.renderer.entity.Renderer" + Character.toUpperCase(name.charAt(0)) + name.substring(1),
                                net.minecraft.client.renderer.entity.EntityRendererManager.class).apply(manager)));
    }

    public static class RegistryEntry<E extends Entity> {

        public ModSpawnEggItem egg;
        public RegistryObject<ModSpawnEggItem> eggObject;
        public EntityType<E> type;
        public RegistryObject<EntityType<E>> typeObject;

        public final String langEn, langZh;

        public RegistryEntry(String langEn, String langZh) {
            this.langEn = langEn;
            this.langZh = langZh;
        }
    }
}
