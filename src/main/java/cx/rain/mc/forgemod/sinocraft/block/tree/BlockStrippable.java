package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BlockStrippable extends Block {
    private final Supplier<Block> target;

    public BlockStrippable(BlockBehaviour.Properties properties, Supplier<Block> targetIn) {
        super(properties);
        target = targetIn;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, Level level, BlockPos pos, Player player,
                                           ItemStack stack, ToolAction toolType) {
        if (toolType.equals(ToolActions.AXE_STRIP)) {
            return target.get().defaultBlockState().setValue(BlockStateProperties.AXIS,
                    state.getValue(BlockStateProperties.AXIS));
        }
        return super.getToolModifiedState(state, level, pos, player, stack, toolType);
    }
}
