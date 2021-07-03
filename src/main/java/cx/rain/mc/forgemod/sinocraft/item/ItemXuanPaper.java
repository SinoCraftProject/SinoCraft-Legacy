package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.item.Item;

public class ItemXuanPaper extends Item {
    public ItemXuanPaper() {
        super(new Properties()
                .group(ModGroups.MISC)
                .maxStackSize(1)
                .setISTER(() -> cx.rain.mc.forgemod.sinocraft.client.renderer.item.XuanPaperItemStackRenderer::new)
        );
    }
}
