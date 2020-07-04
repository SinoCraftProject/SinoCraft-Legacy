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

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class Entities {

    public static final EntityType<EntityBuffalo> ENTITY_BUFFALO = EntityType.Builder
            .create((EntityType.IFactory<EntityBuffalo>) EntityBuffalo::new, EntityClassification.MISC)
            .size(1.5F, 1.5F).build("buffalo");

    /**
     * 之前使用DeferredRegister注册实体，但无法使注册实体在注册刷怪蛋之前触发，导致注册刷怪蛋出错
     * 也许是我操作有误，但目前我只能用@SubscribeEvent注册实体了
     */
    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerEntities(final RegistryEvent.Register<EntityType<?>> event) {
        CultureCraft.getInstance().getLog().info("Registering entities.");
        event.getRegistry().register(ENTITY_BUFFALO.setRegistryName("buffalo"));
    }

    @SuppressWarnings("unused")
    @SubscribeEvent
    public static void registerSpawnEggs(final RegistryEvent.Register<Item> event) {
        CultureCraft.getInstance().getLog().info("Registering spawn eggs.");
        event.getRegistry().register(new SpawnEggItem(ENTITY_BUFFALO, 0, 0,
                new Item.Properties().group(ItemGroup.MISC)).setRegistryName("buffalo_spawn_egg"));
    }

}
