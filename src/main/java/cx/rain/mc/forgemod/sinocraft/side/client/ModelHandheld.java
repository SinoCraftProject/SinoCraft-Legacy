package cx.rain.mc.forgemod.sinocraft.side.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.block.BlockState;
import net.minecraft.client.renderer.model.BakedQuad;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.Direction;
import net.minecraftforge.client.ForgeHooksClient;

import java.util.List;
import java.util.Random;

public class ModelHandheld implements IBakedModel {
    private IBakedModel inventory = null;
    private IBakedModel handheld = null;

    public ModelHandheld(IBakedModel inventoryIn, IBakedModel handheldIn) {
        inventory = inventoryIn;
        handheld = handheldIn;
    }

    @Override
    public List<BakedQuad> getQuads(BlockState state, Direction side, Random rand)
    {
        return inventory.getQuads(state, side, rand);
    }

    @Override
    public boolean isAmbientOcclusion()
    {
        return inventory.isAmbientOcclusion();
    }

    @Override
    public boolean isGui3d()
    {
        return inventory.isGui3d();
    }

    @Override
    public boolean func_230044_c_()
    {
        return inventory.func_230044_c_();
    }

    @Override
    public boolean isBuiltInRenderer()
    {
        return inventory.isBuiltInRenderer();
    }

    @Override
    public TextureAtlasSprite getParticleTexture()
    {
        return inventory.getParticleTexture();
    }

    @Override
    public ItemOverrideList getOverrides()
    {
        return inventory.getOverrides();
    }

    @Override
    public IBakedModel handlePerspective(ItemCameraTransforms.TransformType transformType, MatrixStack mat)
    {
        IBakedModel model = inventory;
        if (transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_LEFT_HAND
                || transformType == ItemCameraTransforms.TransformType.FIRST_PERSON_RIGHT_HAND
                || transformType == ItemCameraTransforms.TransformType.THIRD_PERSON_LEFT_HAND
                || transformType == ItemCameraTransforms.TransformType.THIRD_PERSON_RIGHT_HAND)
        {
            model = handheld;
        }
        return ForgeHooksClient.handlePerspective(model, transformType, mat);
    }
}
