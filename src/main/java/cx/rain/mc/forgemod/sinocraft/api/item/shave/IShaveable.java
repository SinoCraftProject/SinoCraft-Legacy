package cx.rain.mc.forgemod.sinocraft.api.item.shave;

import net.minecraft.block.BlockState;
import net.minecraft.item.ItemUseContext;

public interface IShaveable {
    ShaveResult onShave(BlockState state, ItemUseContext context);
}
