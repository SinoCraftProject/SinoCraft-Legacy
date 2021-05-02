package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.group.ModGroups;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResultType;

/**
 * @author NmmOC7
 */
public class ItemBlockIronPot extends BlockItem {
    public ItemBlockIronPot() {
        super(ModBlocks.BLOCK_IRON_POT.get(), (new Properties()).group(ModGroups.BLOCKS));
    }

    @Override
    public ActionResultType tryPlace(BlockItemUseContext context) {
        if (!context.getWorld().getBlockState(context.getPos().add(0, -1, 0)).getBlock().equals(ModBlocks.STOVE.get())) {
            return ActionResultType.FAIL;
        }

        return super.tryPlace(context);
    }
}
