package cx.rain.mc.forgemod.sinocraft.block.tree;

import cx.rain.mc.forgemod.sinocraft.api.block.tree.TreeData;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;
import org.jetbrains.annotations.Nullable;

import java.util.function.Supplier;

public class BlockLogStrippable extends BlockLog {

    private final Supplier<BlockLog> strippedLog;

    public BlockLogStrippable(TreeData type, Supplier<BlockLog> strippedLog) {
        super(type);
        this.strippedLog = strippedLog;
    }

    @Nullable
    @Override
    public BlockState getToolModifiedState(BlockState state, World world, BlockPos pos, PlayerEntity player, ItemStack stack, ToolType toolType) {
        if (toolType == ToolType.AXE) {
            return strippedLog.get().getDefaultState().with(AXIS, state.get(AXIS));
        }
        return super.getToolModifiedState(state, world, pos, player, stack, toolType);
    }
}
