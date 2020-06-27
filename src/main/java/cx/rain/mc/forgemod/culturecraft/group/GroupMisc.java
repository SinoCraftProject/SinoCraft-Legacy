package cx.rain.mc.forgemod.culturecraft.group;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryBlock;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupMisc extends ItemGroup {
    private static final String label = "misc";

    public GroupMisc() {
        super(CultureCraft.MODID + "." + label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegistryBlock.BLOCKS.get("plate"));
    }
}
