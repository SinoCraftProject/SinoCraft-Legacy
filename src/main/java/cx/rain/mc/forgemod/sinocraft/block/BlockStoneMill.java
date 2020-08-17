package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.base.BlockMachineBase;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityStoneMill;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityVat;
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
import net.minecraftforge.fluids.FluidActionResult;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockStoneMill extends BlockMachineBase {
    public static IntegerProperty ROTATE = IntegerProperty.create("rotate",1,16);

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
    public ActionResultType clientActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.getTileEntity(pos) instanceof TileEntityStoneMill){
            TileEntityStoneMill tileEntity = (TileEntityStoneMill) worldIn.getTileEntity(pos);
            IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
            if(handler!=null){
                if(!player.getHeldItem(handIn).isEmpty()){
                    ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                    ItemStack backup = stack;
                    backup=handler.insertItem(0,backup,true);
                    if(backup!=stack){
                        worldIn.playSound(player,pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        return ActionResultType.SUCCESS;
                    }
                    else {
                        return ActionResultType.FAIL;
                    }
                }
                else{
                    if(!handler.getStackInSlot(0).isEmpty()){
                        if (player.isSneaking()) {
                            worldIn.playSound(player,pos, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0f, 1.0f);
                        }
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.FAIL;
                }
            }
            else{
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.SUCCESS;
    }

    @Override
    public ActionResultType serverActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.getTileEntity(pos) instanceof TileEntityStoneMill){
            TileEntityStoneMill tileEntity = (TileEntityStoneMill) worldIn.getTileEntity(pos);
            IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
            if(handler!=null){
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
                else{
                    if(!handler.getStackInSlot(0).isEmpty()){
                        if (player.isSneaking()) {
                            ItemStack stack = new ItemStack(handler.getStackInSlot(0).getItem());
                            player.setHeldItem(handIn,stack);
                            handler.extractItem(0,1,false);
                        }
                        else {
                            worldIn.setBlockState(pos,state.with(ROTATE,Math.max((state.get(ROTATE) + 1) % 16, 1)));
                            tileEntity.onRotate();
                        }
                        return ActionResultType.SUCCESS;
                    }
                    return ActionResultType.FAIL;
                }
            }
            else{
                return ActionResultType.FAIL;
            }
        }
        return ActionResultType.SUCCESS;
    }
}