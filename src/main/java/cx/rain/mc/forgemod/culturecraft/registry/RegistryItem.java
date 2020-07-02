package cx.rain.mc.forgemod.culturecraft.registry;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.utility.AnnotationsHelper;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedHashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = CultureCraft.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class RegistryItem {
    public static Map<String, Item> ITEMS = new LinkedHashMap<>();

    static {
        String itemsPackage = "cx.rain.mc.forgemod.culturecraft.item.automatic";
        for (Class<?> clazz : AnnotationsHelper.getClassAnnotated(itemsPackage, ModItem.class)) {
            try {
                ModItem modItem = clazz.getAnnotation(ModItem.class);
                String registryName = modItem.name();
                if (!registryName.isEmpty()) {
                    Item item = ((Item) clazz.getConstructor().newInstance())
                            .setRegistryName(CultureCraft.MODID, registryName);
                    ITEMS.put(registryName, item);
                }
            } catch (NoSuchMethodException
                    | IllegalAccessException
                    | InstantiationException
                    | InvocationTargetException ex) {
                ex.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        CultureCraft.getInstance().getLog().info("Registering more items.");
        ITEMS.forEach((name, item) -> {
            event.getRegistry().register(item);
        });
    }
}
