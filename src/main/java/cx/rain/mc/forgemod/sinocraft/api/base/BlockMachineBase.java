package cx.rain.mc.forgemod.sinocraft.api.base;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IMachine;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.material.Material;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

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

    public BlockMachineBase(Material blockMaterialIn, MaterialColor blockMaterialColorIn) {
        super(Properties.create(blockMaterialIn, blockMaterialColorIn));
        this.setDefaultState(this.getDefaultState()
                .with(FACING, Direction.NORTH)
                .with(STATE, IMachine.MachineState.CLOSE));
    }

    public BlockMachineBase(Material blockMaterialIn) {
        this(blockMaterialIn, blockMaterialIn.getColor());
    }

    @Override
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        if (!worldIn.isRemote) {
            BlockState iblockstate = worldIn.getBlockState(pos.north());
            BlockState iblockstate1 = worldIn.getBlockState(pos.south());
            BlockState iblockstate2 = worldIn.getBlockState(pos.west());
            BlockState iblockstate3 = worldIn.getBlockState(pos.east());
            Direction enumfacing = state.get(FACING);

            if (enumfacing == Direction.NORTH && iblockstate.isSolid() && !iblockstate1.isSolid()) {
                enumfacing = Direction.SOUTH;
            } else if (enumfacing == Direction.SOUTH && iblockstate1.isSolid() && !iblockstate.isSolid()) {
                enumfacing = Direction.NORTH;
            } else if (enumfacing == Direction.WEST && iblockstate2.isSolid() && !iblockstate3.isSolid()) {
                enumfacing = Direction.EAST;
            } else if (enumfacing == Direction.EAST && iblockstate3.isSolid() && !iblockstate2.isSolid()) {
                enumfacing = Direction.WEST;
            }

            worldIn.setBlockState(pos, state.with(FACING, enumfacing), 2);
        }

        super.onBlockAdded(state, worldIn, pos, oldState, isMoving);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    /*
    @Override
    public BlockState getActualState(BlockState state, IBlockAccess worldIn, BlockPos pos) {
        TileEntity te = worldIn.getTileEntity(pos);
        if (te instanceof IMachine) {
            state.with(STATE, ((IMachine) te).getWorkingState());
        }
        return state;
    }

    @Override
    public StateContainer createBlockState() {
        return new StateContainer(this, FACING, STATE);
    }

    @Override
    public BlockState getStateFromMeta(int meta) {
        Direction facing = Direction.getHorizontal(meta);
        return this.getDefaultState().with(FACING, facing);
    }

    @Override
    public int getMetaFromState(IBlockState state) {
        int facing = state.getValue(FACING).getHorizontalIndex();
        return facing;
    }

    public static void transformMachineState(IMachine.MachineState state, World worldIn, BlockPos pos) {
        TileEntity e = worldIn.getTileEntity(pos);
        worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(STATE, state), 3);
        worldIn.setBlockState(pos, worldIn.getBlockState(pos).with(STATE, state), 3);
        worldIn.setTileEntity(pos, e);
    }
     */
}
