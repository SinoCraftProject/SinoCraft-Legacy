package cx.rain.mc.forgemod.culturecraft.entity;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.entity.passive.EntityBuffalo;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class Entities {

    public static final DeferredRegister<EntityType<?>> REGISTRY = new DeferredRegister<>(ForgeRegistries.ENTITIES, CultureCraft.MODID);

    public static RegistryObject<EntityType<EntityBuffalo>> entityBuffalo =
            REGISTRY.register("buffalo", () -> EntityType.Builder
                    .create((EntityType.IFactory<EntityBuffalo>) EntityBuffalo::new, EntityClassification.MISC)
                    .size(1.5F, 1.0F).build("buffalo"));

    public Entities(IEventBus bus) {
        CultureCraft.getInstance().getLog().info("Registering entities.");
        REGISTRY.register(bus);
    }

}
