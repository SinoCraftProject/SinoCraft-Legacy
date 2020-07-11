package cx.rain.mc.forgemod.culturecraft.group;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.block.Blocks;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupDecorate extends ItemGroup {
    private static final String label = "decorate";

    public GroupDecorate() {
        super(CultureCraft.MODID + "." + label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(Blocks.SAPLING_PEACH.get());
    }
}
