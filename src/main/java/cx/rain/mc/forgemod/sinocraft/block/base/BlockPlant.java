package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.api.block.IPlantType;
import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class BlockPlant extends CropsBlock {
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
    };

    private static final ThreadLocal<IPlantType> BLOCK_TYPE = new ThreadLocal<>();

    private final IPlantType plantType;

    public static BlockPlant create(PlantType type) {
        BLOCK_TYPE.set(type);
        return type.getMaxHeight() > 1 ? new BlockPlantMulti() : new BlockPlant();
    }

    protected BlockPlant() {
        super(Block.Properties.from(Blocks.CARROTS));
        this.plantType = BLOCK_TYPE.get();
        BLOCK_TYPE.remove();
    }

    public IntegerProperty getAgeProperty() {
        return getType().getProperty();
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int age = state.get(getAgeProperty());
        int stage = age / ((getType().getProperty().getMaxStage() + 1) / 4);
        return SHAPES[stage];
    }

    @Override
    public IItemProvider getSeedsItem() {
        return getType().getSeed().get();
    }

    @Override
    public int getMaxAge() {
        return getType().getProperty().getMaxStage();
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    protected int getAge(BlockState state) {
        return state.get(this.getAgeProperty());
    }

    @Override
    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getAgeProperty(), age);
    }

    @Override
    public StateContainer<Block, BlockState> getStateContainer() {
        return stateContainer;
    }

    @Override
    protected int getBonemealAgeIncrease(World worldIn) {
        return getType().randomGrowAge(worldIn.rand);
    }

    @Override
    public void grow(World worldIn, BlockPos pos, BlockState state) {
        grow(worldIn, pos, state, getBonemealAgeIncrease(worldIn));
    }

    @Override
    public void randomTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random random) {
        if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
        if (worldIn.getLightSubtracted(pos, 0) >= 9) {
            int i = this.getAge(state);
            if (i < this.getMaxAge()) {
                float f = getGrowthChance(this, worldIn, pos);
                if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(worldIn, pos, state, random.nextInt((int)(25.0F / f) + 1) == 0)) {
                    grow(worldIn, pos, state, 1);
                    net.minecraftforge.common.ForgeHooks.onCropsGrowPost(worldIn, pos, state);
                }
            }
        }
    }

    public void grow(World worldIn, BlockPos pos, BlockState state, int age) {
        int current = getAge(state);
        int maxAge = getMaxAge();
        if (current == maxAge) return;
        BlockState newState = withAge(Math.min(maxAge, current + age));
        worldIn.setBlockState(pos, newState, 2);
    }

    public IPlantType getType() {
        if (plantType == null) {
            return BLOCK_TYPE.get();
        }
        return plantType;
    }
}