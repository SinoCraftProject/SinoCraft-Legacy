package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import cx.rain.mc.forgemod.sinocraft.gui.container.ContainerChineseBrushProvider;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

public class ItemChineseBrush extends Item {
    public ItemChineseBrush() {
        super(new Item.Properties().group(ModGroups.TOOLS).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (! world.isRemote) {
            NetworkHooks.openGui((ServerPlayerEntity) player, new ContainerChineseBrushProvider(), (PacketBuffer packerBuffer) -> {
                packerBuffer.writeItemStack(player.getHeldItem(hand));
            });

        }
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }
}
