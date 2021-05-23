package cx.rain.mc.forgemod.sinocraft.event;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.client.ModelHandheld;
import net.minecraft.client.renderer.model.*;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

import java.util.LinkedHashMap;
import java.util.Map;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class SpecialHandheldModel {
    public static Map<Item, String> ITEM_SPECIAL_HANDHELD_MODEL = new LinkedHashMap<>();

    static {

    }

    @SubscribeEvent
    public static void onFMLClientSetup(FMLClientSetupEvent event) {
        ITEM_SPECIAL_HANDHELD_MODEL.forEach((item, additionString) -> ModelLoader.addSpecialModel(new ModelResourceLocation(
                item.getRegistryName() + "_" + additionString, "inventory")));
    }

    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> map = event.getModelRegistry();

        ITEM_SPECIAL_HANDHELD_MODEL.forEach((item, additionString) ->{
            ResourceLocation itemName = item.getRegistryName();
            ModelResourceLocation itemInventory = new ModelResourceLocation(itemName, "inventory");
            ModelResourceLocation ItemHandheld =
                    new ModelResourceLocation(itemName + "_" + additionString, "inventory");

            IBakedModel modelInventory = map.get(itemInventory);
            IBakedModel modelHandheld = map.get(ItemHandheld);
            IBakedModel modelWrapper = new ModelHandheld(modelInventory, modelHandheld);
            map.put(itemInventory, modelWrapper);
        });
    }
}
