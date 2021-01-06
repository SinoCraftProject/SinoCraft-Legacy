package cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity;

import com.mojang.blaze3d.matrix.MatrixStack;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStoneMill;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.tileentity.TileEntityRenderer;
import net.minecraft.client.renderer.tileentity.TileEntityRendererDispatcher;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.CapabilityItemHandler;

public class TileEntityStoneMillRender  extends TileEntityRenderer<TileEntityStoneMill> {
    public TileEntityStoneMillRender(TileEntityRendererDispatcher dispatcher) {
        super(dispatcher);
    }

    @SuppressWarnings("deprecation")
    @Override
    public void render(TileEntityStoneMill te, float partialTicks, MatrixStack matrixStack, IRenderTypeBuffer buffer, int combinedLightIn, int combinedOverlayIn) {
        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
        if(te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null)!=null){
            ItemStack stack = te.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null).getStackInSlot(0);
            if(stack!=ItemStack.EMPTY){
                matrixStack.push();
                matrixStack.scale(0.7f, 0.7f, 0.7f);
                matrixStack.translate(0.5,1.4, 0.5);
                itemRenderer.renderItem(stack, ItemCameraTransforms.TransformType.FIXED,true,matrixStack,buffer,
                        combinedLightIn,combinedOverlayIn,itemRenderer.getItemModelWithOverrides(stack,te.getWorld(),null));
                matrixStack.pop();
            }
        }
    }
}
