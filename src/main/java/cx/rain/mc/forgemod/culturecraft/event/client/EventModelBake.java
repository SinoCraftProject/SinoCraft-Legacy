package cx.rain.mc.forgemod.culturecraft.event.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ForgeHooksClient;
import net.minecraftforge.client.event.ModelBakeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;
import java.util.Map;
import java.util.Random;

@Mod.EventBusSubscriber(modid = CultureCraft.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EventModelBake {
    @SubscribeEvent
    public static void onModelBake(ModelBakeEvent event) {
        Map<ResourceLocation, IBakedModel> map = event.getModelRegistry();

        ResourceLocation sevenStarsPreciousBlade =
                RegistryItem.ITEMS.get("seven_stars_precious_blade").getRegistryName();
        ModelResourceLocation sevenStarsPreciousBladeInventory = new ModelResourceLocation(sevenStarsPreciousBlade,
                "inventory");
        ModelResourceLocation sevenStarsPreciousBladeHandheld =
                new ModelResourceLocation(sevenStarsPreciousBlade + "_handheld", "inventory");

        IBakedModel model = map.get(sevenStarsPreciousBladeInventory);
        IBakedModel modelHand = map.get(sevenStarsPreciousBladeHandheld);
        IBakedModel modelWrapper = new IBakedModel()
        {
            @Override
            public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand)
            {
                return model.getQuads(state, side, rand);
            }

            @Override
            public boolean isAmbientOcclusion()
            {
                return model.isAmbientOcclusion();
            }

            @Override
            public boolean isGui3d()
            {
                return model.isGui3d();
            }

            @Override
            public boolean func_230044_c_()
            {
                return model.func_230044_c_();
            }

            @Override
            public boolean isBuiltInRenderer()
            {
                return model.isBuiltInRenderer();
            }

            @Override
            public TextureAtlasSprite getParticleTexture()
            {
                return model.getParticleTexture();
            }

            @Override
            public ItemOverrideList getOverrides()
            {
                return model.getOverrides();
            }

            @Override
            public IBakedModel handlePerspective(ItemCameraTransforms.TransformType transformType, MatrixStack mat)
            {
                IBakedModel modelToUse = model;
                if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND
                        || transformType == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND || transformType == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
                {
                    modelToUse = modelHand;
                }
                return ForgeHooksClient.handlePerspective(modelToUse, transformType, mat);
            }
        };
        map.put(sevenStarsPreciousBladeInventory, modelWrapper);
    }
}
