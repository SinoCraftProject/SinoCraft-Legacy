package cx.rain.mc.forgemod.sinocraft.api.item;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;

public interface IShaveable {
    ShaveResult onShave(BlockState state, ItemUseContext context);
}
