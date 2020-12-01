package cx.rain.mc.forgemod.sinocraft.entity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.entity.monster.EntityTerraCotta;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityBuffalo;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityEmperor;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityGoal;
import cx.rain.mc.forgemod.sinocraft.item.ModSpawnEggItem;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Entities {
    public static final DeferredRegister<EntityType<?>> REGISTRY =
            new DeferredRegister<>(ForgeRegistries.ENTITIES, SinoCraft.MODID);

    public static final RegistryObject<EntityType<EntityBuffalo>> ENTITY_BUFFALO = REGISTRY.register("buffalo", () -> EntityType.Builder
            .create((EntityType.IFactory<EntityBuffalo>) EntityBuffalo::new, EntityClassification.MISC)
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

    /**
     * 之前使用DeferredRegister注册实体，但无法使注册实体在注册刷怪蛋之前触发，导致注册刷怪蛋出错
     * 也许是我操作有误，但目前我只能用@SubscribeEvent注册实体了
     */
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        SinoCraft.getInstance().getLog().info("Registering entity spawn eggs.");
        ModSpawnEggItem.initEggs();
    }

    /*@SubscribeEvent
    public static void registerSpawnEggs(final RegistryEvent.Register<Item> event) {
        //by SQwatermark:
        //primaryColorIn是底色，secondaryColorIn是斑点的颜色
        //填入的为十六进制RGB颜色转换为的十进制值
        //直接获取十进制颜色的网页：https://www.shuxuele.com/hexadecimal-decimal-colors.html
        //下面建议注册的ID 为 spawn_egg_***(错误
        //不能加注册ID!!!
        /*
        event.getRegistry().register(new SpawnEggItem(ENTITY_TERRACOTTA, 7963006, 10587648,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("spawn_egg_terracotta"));
        event.getRegistry().register(new SpawnEggItem(ENTITY_GOAL, 9411221, 1860469,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("spawn_egg_goal"));

    }*/

    public Entities(IEventBus bus) {
        SinoCraft.getLog().info("Registering entities.");
        REGISTRY.register(bus);
    }
}

