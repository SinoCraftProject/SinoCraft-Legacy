package cx.rain.mc.forgemod.sinocraft.block.base;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public interface IBlockActivatable {
    public ActionResultType onClientActivated(BlockState state, World worldIn, BlockPos pos,
                                              PlayerEntity player, Hand handIn, BlockRayTraceResult hit);
    public ActionResultType onServerActivated(BlockState state, World worldIn, BlockPos pos,
                                              PlayerEntity player, Hand handIn, BlockRayTraceResult hit);
}
