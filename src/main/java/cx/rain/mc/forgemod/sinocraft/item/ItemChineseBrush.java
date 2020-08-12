package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.Groups;
import cx.rain.mc.forgemod.sinocraft.gui.GuiDrawWithChineseBrush;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class ItemChineseBrush extends Item {
    public ItemChineseBrush() {
        super(new Item.Properties().group(Groups.TOOLS).maxStackSize(1));
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, PlayerEntity player, Hand hand) {
        if (world.isRemote)
            DistExecutor.runWhenOn(Dist.CLIENT, () -> () -> Minecraft.getInstance().displayGuiScreen(GuiDrawWithChineseBrush.create(player.getHeldItem(hand))));
        return ActionResult.resultSuccess(player.getHeldItem(hand));
    }
}
