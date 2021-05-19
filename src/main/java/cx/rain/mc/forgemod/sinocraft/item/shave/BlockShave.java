package cx.rain.mc.forgemod.sinocraft.item.shave;

import com.google.common.collect.ImmutableList;
import cx.rain.mc.forgemod.sinocraft.api.item.shave.IShaveable;
import cx.rain.mc.forgemod.sinocraft.api.item.shave.ShaveResult;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;

import java.util.Collection;

public class BlockShave implements IShaveable {

    private final ImmutableList<ItemStack> stacks;
    private final Block block;
    private final BlockState replacedBlock;

    public BlockShave(Block block, BlockState replacedBlock, Collection<ItemStack> stacks) {
        this.stacks = ImmutableList.copyOf(stacks);
        this.block = block;
        this.replacedBlock = replacedBlock;
    }

    @Override
    public ShaveResult onShave(BlockState state, ItemUseContext context) {
        if (block == state.getBlock()) {
            return ShaveResult.shave(replacedBlock, stacks);
        }
        return ShaveResult.skip();
    }
}
