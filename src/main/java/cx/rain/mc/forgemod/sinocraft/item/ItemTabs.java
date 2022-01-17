package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ItemTabs extends CreativeModeTab {
    public static final CreativeModeTab BLOCKS = new ItemTabs("blocks", () -> ModBlocks.WHITE_MARBLE);
    public static final CreativeModeTab AGRICULTURE = new ItemTabs("agriculture_and_foods", () -> ModItems.CHILI_PEPPER_SEED);
    public static final CreativeModeTab MISC = new ItemTabs("tools_misc", () -> ModItems.KNIFE_IRON);

    private ItemStack icon = ItemStack.EMPTY;
    private final Supplier<RegistryObject<? extends ItemLike>> iconSupplier;

    public ItemTabs(String label, Supplier<RegistryObject<? extends ItemLike>> icon) {
        super(SinoCraft.MODID + "." + label);
        iconSupplier = icon;
    }

    @Override
    public ItemStack makeIcon() {
        if (icon == ItemStack.EMPTY) {
            icon = new ItemStack(iconSupplier.get().get());
        }
        return icon;
    }
}
