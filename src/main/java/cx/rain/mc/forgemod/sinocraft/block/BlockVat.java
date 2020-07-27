package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.api.base.BlockMachineBase;
import cx.rain.mc.forgemod.sinocraft.tileentity.TileEntityVat;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.fluids.FluidUtil;
import net.minecraftforge.fluids.capability.CapabilityFluidHandler;
import net.minecraftforge.fluids.capability.IFluidHandler;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

import javax.annotation.Nullable;

@SuppressWarnings("deprecation")
public class BlockVat extends BlockMachineBase {
    public BlockVat() {
        super(Block.Properties.create(Material.WOOD)
                .hardnessAndResistance(3.0F)
                .sound(SoundType.WOOD));
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityVat();
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if(worldIn.isRemote&&handIn!=Hand.MAIN_HAND){
            return ActionResultType.SUCCESS;
        }
        if(worldIn.getTileEntity(pos) instanceof TileEntityVat){
            TileEntityVat tileEntity = (TileEntityVat) worldIn.getTileEntity(pos);
            if(FluidUtil.getFluidHandler(player.getHeldItem(handIn)).orElse(null)!=null){
                IFluidHandler handler = tileEntity.getCapability(CapabilityFluidHandler.FLUID_HANDLER_CAPABILITY).orElse(null);
                boolean b=FluidUtil.interactWithFluidHandler(player,handIn,handler);
                return b?ActionResultType.SUCCESS:ActionResultType.FAIL;
            }
            else{
                IItemHandler handler = tileEntity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
                if(handler!=null){
                    if(!player.getHeldItem(handIn).isEmpty()){
                        ItemStack stack = new ItemStack(player.getHeldItem(handIn).getItem());
                        ItemStack backup = stack;
                        backup=handler.insertItem(0,backup,false);
                        if(backup!=player.getHeldItem(handIn)){
                            player.getHeldItem(handIn).shrink(1);
                            return ActionResultType.SUCCESS;
                        }
                        else {
                            return ActionResultType.FAIL;
                        }
                    }
                    else{
                        if(!handler.getStackInSlot(0).isEmpty()){
                            ItemStack stack = new ItemStack(handler.getStackInSlot(0).getItem());
                            player.setHeldItem(handIn,stack);
                            handler.getStackInSlot(0).shrink(1);
                            return ActionResultType.SUCCESS;
                        }
                        else{
                            return ActionResultType.FAIL;
                        }
                    }
                }
                else{
                    return ActionResultType.FAIL;
                }
            }
        }
        else{
            return ActionResultType.PASS;
        }
    }
}
