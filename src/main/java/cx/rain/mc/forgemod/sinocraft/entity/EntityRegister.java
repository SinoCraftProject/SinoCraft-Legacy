package cx.rain.mc.forgemod.sinocraft.entity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.item.ModSpawnEggItem;
import cx.rain.mc.forgemod.sinocraft.utility.Constructor1;
import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
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

    public <E extends Entity> RegistryEntry<E> register(String name) {
        return new RegistryEntry<>(name);
    }

    @OnlyIn(Dist.CLIENT)
    public void registerRenderers() {
        entries.forEach((name, entry) -> net.minecraftforge.fml.client.registry.RenderingRegistry.registerEntityRenderingHandler(entry.type,
                (net.minecraftforge.fml.client.registry.IRenderFactory<Entity>) manager ->
                        (net.minecraft.client.renderer.entity.EntityRenderer) new Constructor1(entry.rendererClass, net.minecraft.client.renderer.entity.EntityRendererManager.class)
                                .apply(manager)));
    }

    public class RegistryEntry<E extends Entity> {

        String name;
        String eggName;
        SpawnEggItem egg;
        RegistryObject<SpawnEggItem> eggObject;
        EntityType<E> type;
        RegistryObject<EntityType<E>> typeObject;
        String rendererClass;

        public String langEn, langZh;

        public RegistryEntry(String name) {
            this.name = name.toLowerCase(Locale.ROOT);
            String uName = Character.toUpperCase(name.charAt(0)) + name.substring(1);
            this.langEn = uName;
            this.langZh = uName;
            this.rendererClass = "cx.rain.mc.forgemod.sinocraft.client.renderer.entity.Renderer" + uName;
            this.eggName = "spawn_egg_" + name;
        }

        public RegistryEntry<E> entity(Supplier<EntityType.Builder<E>> sup) {
            typeObject = entityRegister.register(name, () -> {
                this.type = sup.get().build(name);
                if (this.egg == null) {
                    SinoCraft.getLogger().warn("No egg for " + name);
                } else {
                    assert EGGS != null;
                    EGGS.put(this.type, this.egg);
                }
                return this.type;
            });
            return this;
        }

        public RegistryEntry<E> lang(String en, String zh) {
            langEn = en;
            langZh = zh;
            return this;
        }

        public RegistryEntry<E> egg(int primaryColor, int secondaryColor) {
            eggObject = eggRegister.register(eggName, () -> this.egg = new ModSpawnEggItem(this, primaryColor, secondaryColor));
            return this;
        }

        public RegistryEntry<E> egg(String eggName, int primaryColor, int secondaryColor) {
            this.eggName = eggName;
            return egg(primaryColor, secondaryColor);
        }

        public RegistryEntry<E> egg(Supplier<SpawnEggItem> sup) {
            eggObject = eggRegister.register(eggName, () -> this.egg = sup.get());
            return this;
        }

        public RegistryEntry<E> egg(String eggName, Supplier<SpawnEggItem> sup) {
            this.eggName = eggName;
            return egg(sup);
        }

        public RegistryEntry<E> renderer(String rendererClass) {
            this.rendererClass = rendererClass;
            return this;
        }

        public RegistryObject<EntityType<E>> getTypeObj() {
            if (eggObject == null) {
                egg(0xf800f8, 0x000000);
            }
            return typeObject;
        }

        public EntityType<E> getType() {
            return type;
        }

        public RegistryObject<SpawnEggItem> getEggObj() {
            return eggObject;
        }

        public SpawnEggItem getEgg() {
            return egg;
        }

        public String getEggName() {
            return eggName;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RegistryEntry<?> entry = (RegistryEntry<?>) o;
            return Objects.equals(name, entry.name);
        }

        @Override
        public int hashCode() {
            return Objects.hash(name);
        }
    }
}
