package cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.item;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.utility.RenderHelper;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec3;
import cx.rain.mc.forgemod.sinocraft.utility.math.Vec4;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.ItemStackTileEntityRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class XuanPaperItemStackTileEntityRenderer extends ItemStackTileEntityRenderer {
    @Override
    public void func_239207_a_(ItemStack stack, ItemCameraTransforms.TransformType p_239207_2_, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLight, int combinedOverlay) {
        if (stack.getTag() == null)
            return;
        IVertexBuilder builder = buffer.getBuffer(
                RenderType.makeType(
                        "text",
                        DefaultVertexFormats.POSITION_COLOR_TEX_LIGHTMAP,
                        7, 256, false,
                        true,
                        RenderType.State.getBuilder().build(false)));
        byte[] pixels = stack.getTag().getByteArray("pixels");

        matrixStack.push();

        if (p_239207_2_ == ItemCameraTransforms.TransformType.FIXED) {
            matrixStack.rotate(Vector3f.YP.rotationDegrees(180));
            matrixStack.translate(-1.5, -0.5, -0.5);
            matrixStack.scale(0.0625f, 0.0625f, 0.0625f);
            matrixStack.translate(0.0D, 0.0D, 0.01D);
        } else {
            matrixStack.scale(0.03125f, 0.03125f, 1.0f);
        }

        for (int i = 0 ; i < 32 ; i ++) {
            for (int j = 0 ; j < 32 ; j ++) {
                float color = 0.0625f * (16 - pixels[i * 32 + j]);
                matrixStack.push();
                matrixStack.translate(i, 31 - j, 0);
                RenderHelper.addSquare(builder, matrixStack,
                        new Vec3<>(0.0f,0.0f,0.0f), new Vec3<>(1.0f,0.0f,0.0f),
                        new Vec3<>(1.0f,1.0f,0.0f), new Vec3<>(0.0f,1.0f,0.0f),
                        null, new Vec4<>(color, color, color, 1.0f));
                matrixStack.pop();
            }
        }
        matrixStack.pop();

    }
}
