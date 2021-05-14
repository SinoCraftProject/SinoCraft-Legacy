package cx.rain.mc.forgemod.sinocraft.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import cx.rain.mc.forgemod.sinocraft.item.ItemXuanPaper;
import net.minecraft.block.BlockState;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Atlases;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.model.IBakedModel;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.model.ModelManager;
import net.minecraft.client.renderer.model.ModelResourceLocation;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.ItemFrameEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.FilledMapItem;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.world.storage.MapData;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderItemInFrameEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

/**
 * @author NmmOC7
 */
@Mod.EventBusSubscriber(modid = SinoCraft.MODID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {

    private static final ModelResourceLocation LOCATION_MODEL_MAP = new ModelResourceLocation("item_frame", "map=true");

    @SubscribeEvent
    public static void onRenderGameOverlay(RenderGameOverlayEvent.Post event) {
        if (event.getType() == RenderGameOverlayEvent.ElementType.ALL) {
            Minecraft mc = Minecraft.getInstance();
            Entity entity = mc.getRenderViewEntity();

            if (entity instanceof PlayerEntity) {
                RayTraceResult result = rayTrace(entity, mc.playerController.getBlockReachDistance(), 0);

                if (result.getType() == RayTraceResult.Type.BLOCK) {
                    BlockRayTraceResult block = (BlockRayTraceResult) result;
                    TileEntity te = entity.world.getTileEntity(block.getPos());

                    if (te instanceof TileEntityPot) {
                        TileEntityPot tileEntity = (TileEntityPot) te;

                        int x = event.getWindow().getScaledWidth();
                        int y = event.getWindow().getScaledHeight();

                        List<ItemStack> items = tileEntity.getInput();

                        for (int i = 0; i < 6; i++) {
                            int stackX = x / 2 - 92 + 23 * i;
                            int stackY = y / 2 + 30;

                            renderItem(items.get(i), stackX, stackY);
                        }

                        renderItem(tileEntity.getOutput(), x / 2 - 69, y / 2 + 30);
                    }
                }
            }
        }
    }

    @SubscribeEvent
    public static void onRenderInFrame(RenderItemInFrameEvent event) {
        ItemStack item = event.getItem();
        ItemFrameEntity frame = event.getEntityItemFrame();
        if (!item.isEmpty() && item.getItem() instanceof ItemXuanPaper) {
            // 重复渲染一次框架，否则需要 coremod 修改 net.minecraft.client.renderer.entity.ItemFrameRenderer.render
            boolean invisible = frame.isInvisible();
            Minecraft mc = Minecraft.getInstance();
            MatrixStack matrixStack = event.getMatrix();
            IRenderTypeBuffer buffers = event.getBuffers();
            int light = event.getLight();
            if (!invisible) {
                matrixStack.rotate(Vector3f.ZP.rotationDegrees(-45 * frame.getRotation()));
                matrixStack.translate(0, 0, -0.4375);
                BlockRendererDispatcher dispatcher = mc.getBlockRendererDispatcher();
                IBakedModel model = dispatcher.getBlockModelShapes().getModelManager().getModel(LOCATION_MODEL_MAP);
                IVertexBuilder buffer = buffers.getBuffer(Atlases.getSolidBlockType());
                matrixStack.push();
                matrixStack.translate(-0.5D, -0.5D, -0.5D);
                dispatcher.getBlockModelRenderer().renderModelBrightnessColor(matrixStack.getLast(), buffer, null, model, 1.0F, 1.0F, 1.0F, light, OverlayTexture.NO_OVERLAY);
                matrixStack.pop();
                matrixStack.translate(0.0D, 0.0D, 0.4375D);
            }
            matrixStack.rotate(Vector3f.ZP.rotationDegrees(frame.getRotation() % 4 * 90));
        }
    }

    public static void renderItem(ItemStack stack, int x, int y) {
        Minecraft.getInstance().getItemRenderer().renderItemAndEffectIntoGUI(stack, x, y);
    }

    public static RayTraceResult rayTrace(Entity entity, double playerReach, float partialTicks) {
        Vector3d eyePosition = entity.getEyePosition(partialTicks);
        Vector3d lookVector = entity.getLook(partialTicks);
        Vector3d traceEnd = eyePosition.add(lookVector.x * playerReach, lookVector.y * playerReach, lookVector.z * playerReach);

        RayTraceContext context = new RayTraceContext(eyePosition, traceEnd, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity);
        return entity.getEntityWorld().rayTraceBlocks(context);
    }
}
