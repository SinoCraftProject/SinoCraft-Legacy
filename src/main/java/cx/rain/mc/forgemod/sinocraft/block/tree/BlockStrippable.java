package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraftforge.common.ToolAction;
import net.minecraftforge.common.ToolActions;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;

// Todo: qyl: Maybe there have some way to reuse this class? Not delete for now. 2022.1.19.
public class BlockStrippable extends Block {
    static {
        AxeItem.STRIPPABLES = new HashMap<>(AxeItem.STRIPPABLES);
    }

    protected final RegistryObject<Block> target;

    public BlockStrippable(BlockBehaviour.Properties properties, RegistryObject<Block> targetIn) {
        super(properties);
        target = targetIn;

        AxeItem.STRIPPABLES.put(this, targetIn.get());
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
