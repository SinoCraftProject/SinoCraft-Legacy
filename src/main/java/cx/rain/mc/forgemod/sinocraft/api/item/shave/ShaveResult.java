package cx.rain.mc.forgemod.sinocraft.api.item.shave;

import com.google.common.collect.ImmutableList;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;

import java.util.Collection;
import java.util.List;

public class ShaveResult {

    private static final ShaveResult SKIP = new ShaveResult(Blocks.AIR.getDefaultState(), ImmutableList.of(), true);

    private final BlockState state;
    private final ImmutableList<ItemStack> dropItems;
    private final boolean skip;

    private ShaveResult(BlockState state, ImmutableList<ItemStack> dropItems, boolean skip) {
        this.state = state;
        this.dropItems = dropItems;
        this.skip = skip;
    }

    public boolean isSkip() {
        return skip;
    }

    public List<ItemStack> getDropItems() {
        return dropItems;
    }

    public BlockState getReplacedBlock() {
        return state;
    }

    public static ShaveResult shave(BlockState result, ItemStack... dropItems) {
        return new ShaveResult(result, ImmutableList.copyOf(dropItems), false);
    }

    public static ShaveResult shave(BlockState result, Collection<ItemStack> dropItems) {
        return new ShaveResult(result, ImmutableList.copyOf(dropItems), false);
    }

    public static ShaveResult shave(ItemStack... dropItems) {
        return new ShaveResult(Blocks.AIR.getDefaultState(), ImmutableList.copyOf(dropItems), false);
    }

    public static ShaveResult shave(Collection<ItemStack> dropItems) {
        return new ShaveResult(Blocks.AIR.getDefaultState(), ImmutableList.copyOf(dropItems), false);
    }

    public static ShaveResult skip() {
        return SKIP;
    }
}
