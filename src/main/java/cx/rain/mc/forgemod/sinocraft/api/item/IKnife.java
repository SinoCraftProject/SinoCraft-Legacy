package cx.rain.mc.forgemod.sinocraft.api.item;

import com.google.common.collect.ImmutableList;
import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
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

    Lazy<IKnife> INSTANCE = new Lazy<>();

    @Nullable
    TieredItem getKnife(IItemTier tier);

    IShaveable newShaveable(Block block, BlockState replacedBlock, Collection<ItemStack> dropItems);

    default IShaveable newShaveable(Block block, BlockState replacedBlock, ItemStack... dropItems) {
        return newShaveable(block, replacedBlock, ImmutableList.copyOf(dropItems));
    }

    IShaveable newShaveable(ITag<Block> tag, Function<BlockState, BlockState> replacedBlock, Function<BlockState, ItemStack> dropItems);

    void addShaveable(IShaveable shaveable, Item... knives);
}
