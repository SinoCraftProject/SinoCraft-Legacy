package cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import java.util.Random;

public class TileEntityPotRender extends TileEntityRenderer<TileEntityPot> {
    public TileEntityPotRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @Override
    public void render(TileEntityPot te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        Random random = new Random(233);
        matrixStack.push();
        matrixStack.scale(0.5f, 0.5f, 0.5f);
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        if(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).isPresent()){
            IItemHandler itemHandler = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
            for (int i = 0 ; i < itemHandler.getSlots() ; i ++) {
                ItemStack stack = itemHandler.getStackInSlot(i);
                matrixStack.push();
                matrixStack.translate(0.5f, 0, 0.5f);
                matrixStack.translate(random.nextFloat(),  (i + 1) / 10.0f, random.nextFloat());
                matrixStack.rotate(Vector3f.XP.rotationDegrees(90.0f));
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(random.nextFloat() * 360.f));
                itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED, true, matrixStack, buffer,
                        combinedLightIn, combinedOverlayIn, itemRenderer.getItemModelWithOverrides(stack, te.getWorld(), null));
                matrixStack.pop();
            }
        }
        matrixStack.pop();
    }
}
