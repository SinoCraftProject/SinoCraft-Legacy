package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.utility.ProtectedHelper;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.nbt.CompoundNBT;

import net.minecraft.block.DispenserBlock;
import net.minecraft.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.dispenser.IBlockSource;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Direction;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.common.util.NonNullSupplier;
import net.minecraftforge.fml.RegistryObject;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ModSpawnEggItem extends SpawnEggItem {
    private static final List<ModSpawnEggItem> MOD_EGGS = new ArrayList<>();
    private final Lazy<? extends EntityType<?>> lazyType;

    public ModSpawnEggItem(final NonNullSupplier<? extends EntityType<?>> entityTypeSupplier, final int primaryColor, final int secondaryColor, final Properties properties) {
        super(EntityType.PIG, primaryColor, secondaryColor, properties);
        this.lazyType = Lazy.of(entityTypeSupplier::get);
        MOD_EGGS.add(this);
    }

    public ModSpawnEggItem(final RegistryObject<? extends EntityType<?>> entityTypeSupplier, final int primaryColor, final int secondaryColor, final Properties properties) {
        super(EntityType.PIG, primaryColor, secondaryColor, properties);
        this.lazyType = Lazy.of(entityTypeSupplier);
        MOD_EGGS.add(this);
    }

    public static void initEggs() {
        final Map<EntityType<?>, SpawnEggItem> EGGS = ProtectedHelper.getStaticField(SpawnEggItem.class, "field_195987_b");

        DefaultDispenseItemBehavior DDIB = new DefaultDispenseItemBehavior() {
            public ItemStack dispenseStack(IBlockSource source, ItemStack stack) {
                Direction direction = source.getBlockState().get(DispenserBlock.FACING);
                EntityType<?> type = ((SpawnEggItem) stack.getItem()).getType(stack.getTag());
                type.spawn(source.getWorld(), stack, null, source.getBlockPos().offset(direction), SpawnReason.DISPENSER, direction != Direction.UP, false);
                stack.shrink(1);
                return stack;
            }
        };

        for (final SpawnEggItem egg : MOD_EGGS) {
            EGGS.put(egg.getType(null), egg);
            DispenserBlock.registerDispenseBehavior(egg, DDIB);
        }
        MOD_EGGS.clear();
    }

    @Override
    public EntityType<?> getType(@Nullable final CompoundNBT nbt) {
        return lazyType.get();
    }
}
