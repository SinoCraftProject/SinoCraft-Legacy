package cx.rain.mc.forgemod.sinocraft.client;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityPot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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

                    if (te instanceof TileEntityPot) {
                        TileEntityPot tileEntity = (TileEntityPot) te;

                        int x = event.getWindow().getScaledWidth();
                        int y = event.getWindow().getScaledHeight();

                        NonNullList<ItemStack> items = tileEntity.getInput();

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
