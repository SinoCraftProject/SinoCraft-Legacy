package cx.rain.mc.forgemod.culturecraft.entity;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.entity.passive.EntityBuffalo;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = CultureCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class Entities {

    //EntityType.Builder.build()的参数可以随便写一个字符串，因为data fixer目前不能用于mod，写啥都会抛出一个WARN
    public static final EntityType<EntityBuffalo> ENTITY_BUFFALO = EntityType.Builder
            .create((EntityType.IFactory<EntityBuffalo>) EntityBuffalo::new, EntityClassification.MISC)
            .size(1.4F, 1.4F).build("null");

    /**
     * 之前使用DeferredRegister注册实体，但无法使注册实体在注册刷怪蛋之前触发，导致注册刷怪蛋出错
     * 也许是我操作有误，但目前我只能用@SubscribeEvent注册实体了
     */
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        CultureCraft.getInstance().getLog().info("Registering entities.");
        event.getRegistry().register(ENTITY_BUFFALO.setRegistryName("buffalo"));
    }

    @SubscribeEvent
    public static void registerSpawnEggs(final RegistryEvent.Register<Item> event) {
        CultureCraft.getInstance().getLog().info("Registering spawn eggs.");
        //by SQwatermark:
        //primaryColorIn是底色，secondaryColorIn是斑点的颜色
        //填入的为十六进制RGB颜色转换为的十进制值
        //直接获取十进制颜色的网页：https://www.shuxuele.com/hexadecimal-decimal-colors.html
        event.getRegistry().register(new SpawnEggItem(ENTITY_BUFFALO, 11434029, 13027014,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("buffalo_spawn_egg"));
    }

}