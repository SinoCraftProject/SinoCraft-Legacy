package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.client.renderer.tileentity.item.XuanPaperItemStackTileEntityRenderer;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import cx.rain.mc.forgemod.sinocraft.gui.GuiXuanPaper;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.fml.DistExecutor;

public class XuanPaper extends Item {
    public XuanPaper() {
        super(new Properties()
                .group(ModGroups.MISC)
                .maxStackSize(1)
                .setISTER(() -> XuanPaperItemStackTileEntityRenderer::new)
        );
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        if (worldIn.isRemote) {
            DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> {
                Minecraft.getInstance().displayGuiScreen(GuiXuanPaper.create(playerIn.getHeldItem(handIn).getOrCreateTag().getByteArray("pixels")));
            });
        }
        return ActionResult.resultSuccess(playerIn.getHeldItem(handIn));
    }
}
