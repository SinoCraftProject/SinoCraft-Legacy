package cx.rain.mc.forgemod.sinocraft.api.item;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.IItemTier;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.TieredItem;
import net.minecraft.tags.ITag;

import javax.annotation.Nullable;
import java.util.Collection;
import java.util.function.Function;

public interface IKnife {

    /**
     * Get knife item by tier.
     */
    @Nullable
    TieredItem getKnife(IItemTier tier);

    /**
     * Create a shave recipe instance by block.
     * @param block block
     * @param replacedBlock the new block replaced origin block.
     * @param dropItems the shave item to drop.
     */
    IShaveable newShaveable(Block block, BlockState replacedBlock, Collection<ItemStack> dropItems);

    /**
     * Create a shave recipe instance by block.
     * @param block block
     * @param replacedBlock the new block replaced origin block.
     * @param dropItems the shave item to drop.
     */
    default IShaveable newShaveable(Block block, BlockState replacedBlock, ItemStack... dropItems) {
        return newShaveable(block, replacedBlock, ImmutableList.copyOf(dropItems));
    }

    /**
     * Create a shave recipe instance by block tag.
     * @param tag block tag
     * @param replacedBlock the new block replaced origin block.
     * @param dropItems the shave item to drop.
     */
    IShaveable newShaveable(ITag<Block> tag, Function<BlockState, BlockState> replacedBlock, Function<BlockState, ItemStack> dropItems);

    /**
     * Register shave recipe to knife.
     *
     * If add to all knives, the knives parameter can use empty.
     */
    void addShaveable(IShaveable shaveable, Item... knives);
}
