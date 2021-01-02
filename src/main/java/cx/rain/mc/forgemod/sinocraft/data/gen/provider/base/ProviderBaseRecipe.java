package cx.rain.mc.forgemod.sinocraft.data.gen.provider.base;

import net.minecraft.advancements.ICriterionInstance;
import net.minecraft.advancements.criterion.*;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.RecipeProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.util.IItemProvider;

public abstract class ProviderBaseRecipe extends RecipeProvider {
    public ProviderBaseRecipe(DataGenerator generatorIn) {
        super(generatorIn);
    }

    protected ICriterionInstance triggerItem(IItemProvider item) {
        return InventoryChangeTrigger.Instance.forItems(getPredicateFromItem(item));
    }

    protected ICriterionInstance triggerItem(ITag<Item> tag) {
        return InventoryChangeTrigger.Instance.forItems(getPredicateFromTag(tag));
    }

    protected ItemPredicate getPredicateFromItem(IItemProvider item) {
        return new ItemPredicate(null, item.asItem(), MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED,
                EnchantmentPredicate.enchantments, EnchantmentPredicate.enchantments, null, NBTPredicate.ANY);
    }

    protected ItemPredicate getPredicateFromTag(ITag<Item> tag) {
        return new ItemPredicate(tag, null, MinMaxBounds.IntBound.UNBOUNDED, MinMaxBounds.IntBound.UNBOUNDED,
                EnchantmentPredicate.enchantments, EnchantmentPredicate.enchantments, null, NBTPredicate.ANY);
    }
}
