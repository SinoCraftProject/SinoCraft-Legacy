package cx.rain.mc.forgemod.sinocraft.api.item;

import com.google.common.collect.ImmutableList;
import cx.rain.mc.forgemod.sinocraft.api.item.shave.IShaveable;
import cx.rain.mc.forgemod.sinocraft.api.utility.Lazy;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.ResourceLocation;

import java.util.Collection;
import java.util.function.Function;

public interface IKnife extends IItemProvider {

    Lazy<IKnife> INSTANCE = new Lazy<>();

    static IKnife getInstance() {
        return INSTANCE.get();
    }

    void addShaveable(IShaveable shave);

    void addShaveable(Block block, BlockState replacedBlock, Collection<ItemStack> dropItems);

    default void addShaveable(Block block, BlockState replacedBlock, ItemStack... dropItems) {
        addShaveable(block, replacedBlock, ImmutableList.copyOf(dropItems));
    }

    void addShaveable(ResourceLocation blockTag, Function<BlockState, BlockState> replacedBlock, Collection<ItemStack> dropItems);

    default void addShaveable(ResourceLocation blockTag, Function<BlockState, BlockState> replacedBlock, ItemStack... dropItems) {
        addShaveable(blockTag, replacedBlock, ImmutableList.copyOf(dropItems));
    }
}
