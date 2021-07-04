package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontalActivatable;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityBellows;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

public class BlockBellows extends BlockHorizontalActivatable {
    public static IntegerProperty STATE = IntegerProperty.create("state", 0, 7);

    public BlockBellows() {
        super(Block.Properties
                .create(Material.WOOD)
                .sound(SoundType.WOOD)
                .notSolid()
        );
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(STATE);
    }

    @Override
    public ActionResultType onClientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        worldIn.playSound(player, pos, SoundEvents.BLOCK_LEVER_CLICK, SoundCategory.BLOCKS, 1.0f, 1.0f);
        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResultType onServerActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(STATE) != 7)
            worldIn.setBlockState(pos, state.with(STATE, state.get(STATE) + 1));
        else{
            worldIn.setBlockState(pos, state.with(STATE, 0));
            TileEntity te = worldIn.getTileEntity(pos);
            if (te instanceof TileEntityBellows) {
                ((TileEntityBellows) te).addWe();
            }
        }
        return ActionResultType.SUCCESS;
    }
}
