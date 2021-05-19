package cx.rain.mc.forgemod.sinocraft.item.shave;

import com.google.common.collect.ImmutableList;
import cx.rain.mc.forgemod.sinocraft.api.item.IShaveable;
import cx.rain.mc.forgemod.sinocraft.api.item.ShaveResult;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.tags.ITag;

import java.util.function.Function;

public class TagShave implements IShaveable {

    private final ITag<Block> tag;
    private final Function<BlockState, ItemStack> dropItem;
    private final Function<BlockState, BlockState> replacedBlock;

    public TagShave(ITag<Block> tag, Function<BlockState, BlockState> replacedBlock, Function<BlockState, ItemStack> dropItem) {
        this.tag = tag;
        this.dropItem = dropItem;
        this.replacedBlock = replacedBlock;
    }

    @Override
    public ShaveResult onShave(BlockState state, ItemUseContext context) {
        if (tag.contains(state.getBlock())) {
            return ShaveResult.shave(replacedBlock.apply(state), ImmutableList.of(dropItem.apply(state)));
        }
        return ShaveResult.skip();
    }
}
