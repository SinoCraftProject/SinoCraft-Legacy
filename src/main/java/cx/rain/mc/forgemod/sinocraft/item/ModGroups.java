package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModGroups {
    public static final ModGroup BLOCKS = new ModGroup("blocks", ModBlocks.WHITE_MARBLE);
    public static final ModGroup AGRICULTURE = new ModGroup("agriculture_foods", ModItems.CHILI_PEPPER_SEED);
    public static final ModGroup MISC = new ModGroup("tools_misc", ModItems.KNIFE_IRON);

    public static class ModGroup extends ItemGroup {

        private ItemStack icon = ItemStack.EMPTY;
        private final RegistryObject<? extends IItemProvider> iconSupplier;

        public ModGroup(String label, RegistryObject<? extends IItemProvider> icon) {
            super(SinoCraft.MODID + "." + label);
            iconSupplier = icon;
        }

        @Override
        public ItemStack createIcon() {
            if (icon == ItemStack.EMPTY) {
                icon = new ItemStack(iconSupplier.get());
            }
            return icon;
        }
    }
}
