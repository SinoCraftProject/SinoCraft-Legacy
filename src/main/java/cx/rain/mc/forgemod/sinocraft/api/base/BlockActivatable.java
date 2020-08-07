package cx.rain.mc.forgemod.sinocraft.api.base;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

@SuppressWarnings("deprecation")
public abstract class BlockActivatable extends Block {
    public BlockActivatable(net.minecraft.block.Block.Properties properties) {
        super(properties);
    }

    public abstract ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit);
    public abstract ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit);

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isRemote) {
            return clientActivated(state,worldIn,pos,player,handIn,hit);
        }
        else {
            return serverActivated(state,worldIn,pos,player,handIn,hit);
        }
    }
}
