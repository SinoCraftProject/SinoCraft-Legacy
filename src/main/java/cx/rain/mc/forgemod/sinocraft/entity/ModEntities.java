package cx.rain.mc.forgemod.sinocraft.entity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.entity.monster.EntityTerraCotta;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityBuffalo;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityEmperor;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityGoal;
import cx.rain.mc.forgemod.sinocraft.item.ModSpawnEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final DeferredRegister<EntityType<?>> REGISTRY =
            DeferredRegister.create(ForgeRegistries.ENTITIES, SinoCraft.MODID);

    public static final RegistryObject<EntityType<EntityBuffalo>> ENTITY_BUFFALO = REGISTRY.register("buffalo", () -> EntityType.Builder
            .create(EntityBuffalo::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow"));

    public static final EntityType<EntityGoal> ENTITY_GOAL = EntityType.Builder//山羊
            .create(EntityGoal::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow");
    public static final EntityType<EntityTerraCotta> ENTITY_TERRACOTTA = EntityType.Builder//兵马俑
            .create((EntityType.IFactory<EntityTerraCotta>) EntityTerraCotta::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow");
    public static final EntityType<EntityEmperor> ENTITY_EMPEROR = EntityType.Builder//国王
            .create((EntityType.IFactory<EntityEmperor>) EntityEmperor::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow");

    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        SinoCraft.getInstance().getLogger().info("Registering entity spawn eggs.");
        ModSpawnEggItem.initEggs();
    }

    public ModEntities(IEventBus bus) {
        SinoCraft.getLogger().info("Registering entities.");
        REGISTRY.register(bus);
    }
}

