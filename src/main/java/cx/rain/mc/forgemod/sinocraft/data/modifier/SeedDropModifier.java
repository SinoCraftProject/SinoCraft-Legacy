package cx.rain.mc.forgemod.sinocraft.data.modifier;

import net.minecraft.item.ItemStack;
import net.minecraft.loot.LootContext;
import net.minecraft.loot.conditions.ILootCondition;
import net.minecraftforge.common.loot.LootModifier;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SeedDropModifier extends LootModifier {
    public SeedDropModifier(ILootCondition[] conditionsIn, ItemStack stack) {
        super(conditionsIn);
        this.stack = stack;
    }

    public ItemStack stack;

    @NotNull
    @Override
    protected List<ItemStack> doApply(List<ItemStack> generatedLoot, LootContext context) {
        generatedLoot.add(stack);
        return generatedLoot;
    }

    public ILootCondition[] getConditions() {
        return conditions;
    }
}
