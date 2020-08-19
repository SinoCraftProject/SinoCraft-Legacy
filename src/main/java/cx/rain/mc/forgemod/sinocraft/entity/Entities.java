package cx.rain.mc.forgemod.sinocraft.entity;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.entity.monster.EntityTerraCotta;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityBuffalo;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityEmperor;
import cx.rain.mc.forgemod.sinocraft.entity.passive.EntityGoal;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Entities {

    //EntityType.Builder.build()的参数可以随便写一个字符串，因为data fixer目前不能用于mod，写啥都会抛出一个WARN
    public static final EntityType<EntityBuffalo> ENTITY_BUFFALO = EntityType.Builder
            .create((EntityType.IFactory<EntityBuffalo>) EntityBuffalo::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("null");
    public static final EntityType<EntityGoal> ENTITY_GOAL = EntityType.Builder//山羊
            .create(EntityGoal::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("null");
    public static final EntityType<EntityTerraCotta> ENTITY_TERRACOTTA = EntityType.Builder//兵马俑
            .create((EntityType.IFactory<EntityTerraCotta>) EntityTerraCotta::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("null");
    public static final EntityType<EntityEmperor> ENTITY_EMPEROR = EntityType.Builder//国王
            .create((EntityType.IFactory<EntityEmperor>) EntityEmperor::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("null");

    /**
     * 之前使用DeferredRegister注册实体，但无法使注册实体在注册刷怪蛋之前触发，导致注册刷怪蛋出错
     * 也许是我操作有误，但目前我只能用@SubscribeEvent注册实体了
     */
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        SinoCraft.getInstance().getLog().info("Registering entities.");
        event.getRegistry().register(ENTITY_BUFFALO.setRegistryName("buffalo"));
        /*
        event.getRegistry().register(ENTITY_TERRACOTTA.setRegistryName("terracotta"));
        event.getRegistry().register(ENTITY_EMPEROR.setRegistryName("emperor"));
        event.getRegistry().register(ENTITY_GOAL.setRegistryName("goal"));
         */
    }

    @SubscribeEvent
    public static void registerSpawnEggs(final RegistryEvent.Register<Item> event) {
        SinoCraft.getInstance().getLog().info("Registering entity spawn eggs.");
        //by SQwatermark:
        //primaryColorIn是底色，secondaryColorIn是斑点的颜色
        //填入的为十六进制RGB颜色转换为的十进制值
        //直接获取十进制颜色的网页：https://www.shuxuele.com/hexadecimal-decimal-colors.html
        //下面建议注册的ID 为 spawn_egg_***(错误
        //不能加注册ID!!!
        event.getRegistry().register(new SpawnEggItem(ENTITY_BUFFALO, 11434029, 13027014,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("spawn_egg_buffalo"));
        /*
        event.getRegistry().register(new SpawnEggItem(ENTITY_TERRACOTTA, 7963006, 10587648,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("spawn_egg_terracotta"));
        event.getRegistry().register(new SpawnEggItem(ENTITY_GOAL, 9411221, 1860469,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("spawn_egg_goal"));
         */
    }
}

