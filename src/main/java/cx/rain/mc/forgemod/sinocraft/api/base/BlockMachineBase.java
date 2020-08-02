package cx.rain.mc.forgemod.sinocraft.api.base;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.inventory.InventoryHelper;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nullable;

/**
 * MachineBlockBase class
 * All machine from CultureCraft or CultureCraft extend must extends it
 *
 * @author Infinity_rain
 */
public abstract class BlockMachineBase extends Block {
    public static final DirectionProperty FACING =
            DirectionProperty.create("facing", Direction.Plane.HORIZONTAL);
    public static final EnumProperty<IMachine.MachineState> STATE =
            EnumProperty.create("state", IMachine.MachineState.class);

    public BlockMachineBase(Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(STATE, IMachine.MachineState.CLOSE));
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING,STATE);
    }

    @Override
    public void onReplaced(BlockState state, World worldIn, BlockPos pos, BlockState newState, boolean isMoving) {
        if (state.getBlock() != newState.getBlock()) {
            TileEntity tileentity = worldIn.getTileEntity(pos);
            NonNullList<ItemStack> stacks = NonNullList.create();
            if(tileentity instanceof IMachine){
                stacks = ((IMachine) tileentity).getDropsItem(stacks);
            }

            for(ItemStack stack : stacks){
                InventoryHelper.spawnItemStack(worldIn,pos.getX(),pos.getY(),pos.getZ(),stack);
            }

            super.onReplaced(state, worldIn, pos, newState, isMoving);
        }
    }
}
