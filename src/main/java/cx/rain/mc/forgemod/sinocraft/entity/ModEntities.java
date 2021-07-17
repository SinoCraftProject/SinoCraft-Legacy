package cx.rain.mc.forgemod.sinocraft.entity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.entity.monster.EntityTerraCotta;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityBuffalo;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityEmperor;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityGoal;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MobEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEntities {
    public static final EntityRegister REGISTRY = new EntityRegister(ModItems.REGISTRY);

    public static final EntityType<?> EMPTY_ENTITY_TYPE = EntityType.Builder.create(EntityClassification.MISC).build("empty");

    public static final RegistryObject<EntityType<EntityBuffalo>> ENTITY_BUFFALO = REGISTRY.<EntityBuffalo>register("buffalo")
            .entity(() -> EntityType.Builder.create(EntityBuffalo::new, EntityClassification.MISC).size(1.4F, 1.4F))
            .egg(0xae782d, 0xc6c6c6)
            .lang("Buffalo", "水牛")
            .getTypeObj();

    public static final EntityType<EntityGoal> ENTITY_GOAL = EntityType.Builder//山羊
            .create(EntityGoal::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow");
    public static final EntityType<EntityTerraCotta> ENTITY_TERRACOTTA = EntityType.Builder//兵马俑
            .create((EntityType.IFactory<EntityTerraCotta>) EntityTerraCotta::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow");
    public static final EntityType<EntityEmperor> ENTITY_EMPEROR = EntityType.Builder//国王
            .create((EntityType.IFactory<EntityEmperor>) EntityEmperor::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("arrow");

    public ModEntities(IEventBus bus) {
        SinoCraft.getLogger().info("Registering entities.");
        REGISTRY.register(bus);
    }

    @SubscribeEvent
    public static void onCreateAttributes(EntityAttributeCreationEvent event) {
        event.put(ENTITY_BUFFALO.get(), MobEntity.func_233666_p_()
                .createMutableAttribute(Attributes.MAX_HEALTH, 20.0D)
                .createMutableAttribute(Attributes.MOVEMENT_SPEED, 0.2D).create());
    }
}

