package cx.rain.mc.forgemod.sinocraft.client;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.platform.GlStateManager;
import com.mojang.blaze3d.systems.RenderSystem;
import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityIronPot;
import net.minecraft.client.MainWindow;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.model.ItemCameraTransforms;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author NmmOC7
 */
@Mod.EventBusSubscriber(modid = SinoCraft.MODID, value = Dist.CLIENT)
@OnlyIn(Dist.CLIENT)
public class ClientEventHandler {
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

                    if (te instanceof TileEntityIronPot) {
                        TileEntityIronPot tileEntity = (TileEntityIronPot) te;

                        int x = event.getWindow().getScaledWidth();
                        int y = event.getWindow().getScaledHeight();

                        for (int i = 0; i < 6; i++) {
                            mc.getItemRenderer().renderItemAndEffectIntoGUI(tileEntity.getStackInSlot(i), (x / 2 - 60) + 20 * i, y / 2 + 30);
                        }
                    }
                }
            }
        }
    }

    public static RayTraceResult rayTrace(Entity entity, double playerReach, float partialTicks) {
        Vector3d eyePosition = entity.getEyePosition(partialTicks);
        Vector3d lookVector = entity.getLook(partialTicks);
        Vector3d traceEnd = eyePosition.add(lookVector.x * playerReach, lookVector.y * playerReach, lookVector.z * playerReach);

        RayTraceContext context = new RayTraceContext(eyePosition, traceEnd, RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity);
        return entity.getEntityWorld().rayTraceBlocks(context);
    }
}
