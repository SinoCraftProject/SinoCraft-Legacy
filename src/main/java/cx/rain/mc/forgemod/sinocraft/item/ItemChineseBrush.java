package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.Groups;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class ItemChineseBrush extends Item {
    public ItemChineseBrush() {
        super(new Item.Properties().group(Groups.TOOLS).maxStackSize(1));
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
