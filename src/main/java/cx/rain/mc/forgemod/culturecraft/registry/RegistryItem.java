package cx.rain.mc.forgemod.culturecraft.registry;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.api.annotation.ModItem;
import cx.rain.mc.forgemod.culturecraft.api.annotation.HandheldModel;
import cx.rain.mc.forgemod.culturecraft.registry.client.RegistrySpecialHandheldModel;
import cx.rain.mc.forgemod.culturecraft.utility.AnnotationsHelper;
import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.IItemFactory;

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
                IItemFactory factory = modItem.factory().newInstance();
                Object[][] args = modItem.args().newInstance().call();
                if (!registryName.isEmpty()) {
                    // Fixme
                    Item item = factory.get((Class<? extends Item>) clazz, args)
                            .setRegistryName(CultureCraft.MODID, registryName);
                    ITEMS.put(registryName, item);

                    String handheld = modItem.handheldModel().value();
                    if (!handheld.isEmpty()) {
                        RegistrySpecialHandheldModel.ITEM_SPECIAL_HANDHELD_MODEL.put(item, handheld);
                    }
                    /*
                    if (args == null) {
                        Item item = factory.get((Class<? extends Item>) clazz, null)
                                .setRegistryName(CultureCraft.MODID, registryName);
                        ITEMS.put(registryName, item);

                        String handheld = modItem.handheldModel().value();
                        if (!handheld.isEmpty()) {
                            RegistrySpecialHandheldModel.ITEM_SPECIAL_HANDHELD_MODEL.put(item, handheld);
                        }
                    } else {
                        for (int i = 0; i < args.length; i++) {
                            Item item = factory.get((Class<? extends Item>) clazz, args[i])
                                    .setRegistryName(CultureCraft.MODID, registryName);
                            ITEMS.put(registryName, item);

                            String handheld = modItem.handheldModel().value();
                            if (!handheld.isEmpty()) {
                                RegistrySpecialHandheldModel.ITEM_SPECIAL_HANDHELD_MODEL.put(item, handheld);
                            }
                        }
                    }
                     */
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @SubscribeEvent
    public static void onRegisterItem(RegistryEvent.Register<Item> event) {
        CultureCraft.getInstance().getLog().info("Registering items.");
        ITEMS.forEach((name, item) -> {
            event.getRegistry().register(item);
        });
    }
}
