package cx.rain.mc.forgemod.sinocraft.group;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupDecorate extends ItemGroup {
    private static final String label = "decorate";

    public GroupDecorate() {
        super(SinoCraft.MODID + "." + label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.SAPLING_PEACH.get());
    }
}
