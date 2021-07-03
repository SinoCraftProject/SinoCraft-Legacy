package cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityVat;
import cx.rain.mc.forgemod.sinocraft.utility.RenderHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.AtlasTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.util.math.vector.Vector4f;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;

@OnlyIn(Dist.CLIENT)
public class TileEntityVatRender extends TileEntityRenderer<TileEntityVat> {
    public TileEntityVatRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(TileEntityVat te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        float top = 0;
        if (te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).isPresent()) {
            if (te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0) != FluidStack.EMPTY && te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0).getAmount() != 0) {
                top = 3.5f;
                matrixStack.push();
                matrixStack.scale(0.75f, 1.0f, 0.75f);
                matrixStack.translate(0.18, (te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0).getAmount() / 1000.0) - 0.1, 0.18);
                Fluid fluid = te.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null).getFluidInTank(0).getFluid();
                TextureAtlasSprite sprite = Minecraft.getInstance().getAtlasSpriteGetter(AtlasTexture.LOCATION_BLOCKS_TEXTURE).
                        apply(fluid.getAttributes().getStillTexture());
                IVertexBuilder vertex = buffer.getBuffer(RenderType.getText(sprite.getAtlasTexture().getTextureLocation()));
                RenderHelper.addSquare(vertex, matrixStack,
                        new Vector3f(0.0f, 0.0f, 1.0f), new Vector3f(1.0f, 0.0f, 1.0f),
                        new Vector3f(1.0f, 0.0f, 0.0f), new Vector3f(0.0f, 0.0f, 0.0f),
                        new Vector4f(sprite.getMinU(), sprite.getMaxU(), sprite.getMinV(), sprite.getMaxV()),
                        RenderHelper.NumberToGLColor(fluid.getAttributes().getColor()));
                matrixStack.pop();
            }
        }
        if (te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null) != null) {
            ItemStack stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null).getStackInSlot(0);
            int lc = 0;
            if (stack != ItemStack.EMPTY) {
                lc = stack.getCount();
                for (int i = 0; i < Math.min(stack.getCount(), 16); i++) {
                    matrixStack.push();
                    matrixStack.scale(0.2f, 0.2f, 0.2f);
                    matrixStack.translate(i % 4 + 1, 1 + top, i / 4 + 1);
                    itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer,
                            combinedLightIn, combinedOverlayIn, itemRenderer.getItemModelWithOverrides(stack, te.getWorld(), null));
                    matrixStack.pop();
                }
            }
            stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null).getStackInSlot(1);
            if (stack != ItemStack.EMPTY) {
                for (int i = lc; i < Math.min(stack.getCount() + lc, 16); i++) {
                    matrixStack.push();
                    matrixStack.scale(0.2f, 0.2f, 0.2f);
                    matrixStack.translate(i % 4 + 1, 1 + top, i / 4 + 1);
                    itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer,
                            combinedLightIn, combinedOverlayIn, itemRenderer.getItemModelWithOverrides(stack, te.getWorld(), null));
                    matrixStack.pop();
                }
            }
        }
    }
}