package cx.rain.mc.forgemod.sinocraft.event;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import cx.rain.mc.forgemod.sinocraft.utility.MathHelper;
import net.minecraft.item.BucketItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = SinoCraft.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class OnRightClick {
    @SubscribeEvent
    public static void onRightClick(PlayerInteractEvent.RightClickItem event) {
        if (event.getPlayer().getHeldItem(event.getHand()).getItem().getRegistryName().equals(Items.BOWL.getRegistryName())) {
            RayTraceResult hit = MathHelper.rayTrace(event.getWorld(), event.getPlayer(), RayTraceContext.FluidMode.SOURCE_ONLY);
            if (hit.getType() == RayTraceResult.Type.BLOCK) {
                if (event.getWorld().getFluidState(((BlockRayTraceResult)hit).getPos()).isTagged(FluidTags.WATER)) {
                    event.getPlayer().getHeldItem(event.getHand()).shrink(1);
                    event.getPlayer().inventory.addItemStackToInventory(new ItemStack(ModItems.BOWL_WITH_WATER.get()));
                    event.getWorld().playSound(event.getPlayer(), event.getPlayer().getPosX(), event.getPlayer().getPosY(), event.getPlayer().getPosZ(),
                            SoundEvents.ITEM_BUCKET_FILL, SoundCategory.NEUTRAL, 1.0F, 1.0F);
                }
            }
        }
    }
}
