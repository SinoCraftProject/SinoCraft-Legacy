package cx.rain.mc.forgemod.sinocraft.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public abstract class BlockHorizontalActivatable extends BlockHorizontal implements IBlockActivatable {
    public BlockHorizontalActivatable(Properties properties) {
        super(properties);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return onClientActivated(state, worldIn, pos, player, handIn, hit);
        } else {
            return onServerActivated(state, worldIn, pos, player, handIn, hit);
        }
    }
}
