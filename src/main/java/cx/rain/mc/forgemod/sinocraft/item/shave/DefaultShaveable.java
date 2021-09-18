package cx.rain.mc.forgemod.sinocraft.item.shave;

import cx.rain.mc.forgemod.sinocraft.api.item.IShaveable;
import cx.rain.mc.forgemod.sinocraft.api.item.ShaveResult;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.world.World;
import net.minecraftforge.common.ToolType;

public class DefaultShaveable implements IShaveable {

    @Override
    public ShaveResult onShave(BlockState state, ItemUseContext context) {
        World world = context.getWorld();
        BlockState modifiedState = state.getBlock().getToolModifiedState(state, world, context.getPos(), context.getPlayer(), new ItemStack(Items.IRON_AXE), ToolType.AXE);
        return modifiedState == null ? ShaveResult.skip() : ShaveResult.shave(modifiedState, new ItemStack(ModItems.BARK.get(), world.rand.nextInt(2) + 1));
    }
}
