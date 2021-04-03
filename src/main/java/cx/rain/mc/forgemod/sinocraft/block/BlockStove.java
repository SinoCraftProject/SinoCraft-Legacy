package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import cx.rain.mc.forgemod.sinocraft.api.base.BlockMachineBase;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IMachine;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStoneMill;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStove;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.vector.*;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

public class BlockStove extends BlockMachineBase {
    public static IntegerProperty LEVEL = IntegerProperty.create("level",1,4);

    public BlockStove(int level) {
        super(Properties.create(Material.GOURD, MaterialColor.GRAY).notSolid());
        this.setDefaultState(this.getDefaultState().with(LEVEL, level));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LEVEL);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        SinoCraft.getLogger().info(state.get(LEVEL));
        return new TileEntityStove(state.get(LEVEL));
    }

    @Override
    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityStove) {
            TileEntityStove tileEntity = (TileEntityStove) worldIn.getTileEntity(pos);
            IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, state.get(FACING)).orElse(null);
            if (handler != null) {
                if(!player.getHeldItem(handIn).isEmpty()){
                    ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                    ItemStack backup = stack;
                    backup=handler.insertItem(0,backup,true);
                    if(backup!=stack){
                        worldIn.playSound(player,pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResultType.SUCCESS;
                    }
                    else {
                        return ActionResultType.FAIL;
                    }
                }
                else {
                    return ActionResultType.FAIL;
                }
            }
            else {
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.PASS;
    }

    public static Direction RotateDirection(Direction rotation, Direction direction) {
        if (rotation == Direction.WEST) {
            return direction.rotateY();
        }
        if (rotation == Direction.EAST) {
            return direction.rotateYCCW();
        }
        if (rotation == Direction.NORTH) {
            return direction;
        }
        if (rotation == Direction.SOUTH) {
            return direction.rotateY().rotateY();
        }
        return direction;
    }

    @Override
    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.getTileEntity(pos) instanceof TileEntityStove) {
            TileEntityStove tileEntity = (TileEntityStove) worldIn.getTileEntity(pos);
            IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, RotateDirection(state.get(FACING), hit.getFace())).orElse(null);
            if (handler != null) {
                if(!player.getHeldItem(handIn).isEmpty()){
                    ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                    ItemStack backup = stack;
                    backup=handler.insertItem(0,backup,false);
                    if(backup!=stack){
                        player.getHeldItem(handIn).shrink(1);
                        return ActionResultType.SUCCESS;
                    }
                    else {
                        return ActionResultType.FAIL;
                    }
                }
                else {
                    return ActionResultType.FAIL;
                }
            }
            else {
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.PASS;
    }
}
