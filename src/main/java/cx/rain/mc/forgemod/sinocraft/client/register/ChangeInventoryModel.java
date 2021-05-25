package cx.rain.mc.forgemod.sinocraft.client.register;

import cx.rain.mc.forgemod.sinocraft.client.XuanPaperBakedModel;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Map;
import java.util.function.Function;

@OnlyIn(Dist.CLIENT)
@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ChangeInventoryModel {
    public static void replaceModel(Map<ResourceLocation, IBakedModel> modelRegistry, ResourceLocation id, Function<IBakedModel, IBakedModel> model) {
        ModelResourceLocation location = new ModelResourceLocation(id, "inventory");
        IBakedModel exist_model = modelRegistry.get(location);
        if (exist_model == null)
            throw new IllegalStateException(location + " has no existing model");
        modelRegistry.put(location, model.apply(exist_model));
    }

    @SubscribeEvent
    public static void onModelBaked(ModelBakeEvent event) {
        replaceModel(event.getModelRegistry(), ModItems.XUAN_PAPER.getId(), XuanPaperBakedModel::new);
    }
}
