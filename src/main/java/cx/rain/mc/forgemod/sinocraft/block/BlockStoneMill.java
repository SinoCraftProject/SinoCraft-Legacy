package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontal;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStoneMill;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockStoneMill extends BlockHorizontal {
    public static IntegerProperty ROTATE = IntegerProperty.create("rotate", 1, 16);

    public BlockStoneMill() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD)
                .notSolid());
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityStoneMill();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(ROTATE);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof TileEntityStoneMill) {
            TileEntityStoneMill mill = (TileEntityStoneMill) te;
            IItemHandler handler = mill.getItemHandler();
            if (!player.getHeldItem(handIn).isEmpty()) {
                ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                if (handler.insertItem(0, stack, false).isEmpty()) {
                    if (!worldIn.isRemote) {
                        worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        player.getHeldItem(handIn).shrink(1);
                    }
                    return ActionResultType.SUCCESS;
                } else {
                    return ActionResultType.FAIL;
                }
            }
            if (!handler.getStackInSlot(0).isEmpty()) {
                if (!worldIn.isRemote) {
                    if (player.isSneaking()) {
                        worldIn.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        player.setHeldItem(handIn, handler.extractItem(0, 1, false));
                    } else {
                        worldIn.setBlockState(pos, state.with(ROTATE, Math.max((state.get(ROTATE) + 1) % 16, 1)));
                        mill.rotate();
                    }
                }
                return ActionResultType.SUCCESS;
            }
            return ActionResultType.FAIL;
        }
        return ActionResultType.PASS;
    }
}