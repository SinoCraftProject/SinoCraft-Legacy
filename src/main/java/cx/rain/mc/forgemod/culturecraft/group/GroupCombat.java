package cx.rain.mc.forgemod.culturecraft.group;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import cx.rain.mc.forgemod.culturecraft.registry.RegistryItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class GroupCombat extends ItemGroup {
    private static final String label = "combat";

    public GroupCombat() {
        super(CultureCraft.MODID + "." + label);
    }

    @Override
    public ItemStack createIcon() {
        return new ItemStack(RegistryItem.ITEMS.get("seven_stars_precious_blade"));
    }
}
