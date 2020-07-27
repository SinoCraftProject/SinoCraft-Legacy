package cx.rain.mc.forgemod.sinocraft.client.renderer.ter;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityVat;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluids;
import net.minecraft.inventory.container.PlayerContainer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;

public class TileEntityVatRender extends TileEntityRenderer<TileEntityVat> {
    public TileEntityVatRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    public void addSquare(Vec3d pos1,Vec3d pos2,Vec3d pos3,Vec3d pos4){

    }

    @Override
    public void render(TileEntityVat te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        BlockRendererDispatcher blockRenderer = Minecraft.getInstance().getBlockRendererDispatcher();
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        matrixStack.push();
        ResourceLocation resourceLocation = Fluids.WATER.getAttributes().getStillTexture();
        TextureAtlasSprite textureAtlasSprite = Minecraft.getInstance().getAtlasSpriteGetter(PlayerContainer.LOCATION_BLOCKS_TEXTURE).apply(resourceLocation);
        IVertexBuilder vertex = buffer.getBuffer(RenderType.getText(textureAtlasSprite.getAtlasTexture().getTextureLocation()));
        matrixStack.pop();
    }
}
