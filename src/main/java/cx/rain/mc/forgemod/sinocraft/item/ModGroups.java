package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraftforge.fml.RegistryObject;

import java.util.function.Supplier;

public class ModGroups extends ItemGroup {

    public static final ModGroups BLOCKS = new ModGroups("blocks", () -> ModBlocks.WHITE_MARBLE);
    public static final ModGroups AGRICULTURE = new ModGroups("agriculture_foods", () ->  ModItems.CHILI_PEPPER_SEED);
    public static final ModGroups MISC = new ModGroups("tools_misc", () -> ModItems.KNIFE_IRON);

    private ItemStack icon = ItemStack.EMPTY;
    private final Supplier<RegistryObject<? extends IItemProvider>> iconSupplier;

    public ModGroups(String label, Supplier<RegistryObject<? extends IItemProvider>> icon) {
        super(SinoCraft.MODID + "." + label);
        iconSupplier = icon;
    }

    @Override
    public ItemStack createIcon() {
        if (icon == ItemStack.EMPTY) {
            icon = new ItemStack(iconSupplier.get().get());
        }
        return icon;
    }
}
