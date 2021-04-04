package cx.rain.mc.forgemod.sinocraft.block.base;

import cx.rain.mc.forgemod.sinocraft.utility.enumerate.PlantType;
import cx.rain.mc.forgemod.sinocraft.utility.property.StageProperty;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;

public class BlockPlant extends CropsBlock {
    private static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
    };

    private PlantType type;
    private RegistryObject<Item> seed;
    private StageProperty stage;
    protected final StateContainer<Block, BlockState> stateContainer;

    public BlockPlant(PlantType typeIn, RegistryObject<Item> seedRegistryIn, StageProperty stageIn) {
        super(Block.Properties.from(Blocks.CARROTS));

        type = typeIn;
        seed = seedRegistryIn;
        stage = stageIn;

        // AS: Replace StateContainer.
        StateContainer.Builder<Block, BlockState> builder = new StateContainer.Builder<>(this);
        fillStateContainer(builder, stageIn);
        stateContainer = builder.createStateContainer(Block::getDefaultState, BlockState::new);
        setDefaultState(stateContainer.getBaseState().with(getStage(), 0));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int age = state.get(getAgeProperty());
        int stage = age / ((this.stage.getMaxStage() + 1) / 4);
        return SHAPES[stage];
    }

    @Override
    public IItemProvider getSeedsItem() {
        return seed.get();
    }

    @Override
    public int getMaxAge() {
        return stage.getMaxStage();
    }

    // AS: Codes below here are used to use our stage state.
    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        // AS: Disable original age state, use new stage state instead.
        super.fillStateContainer(builder);
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder, StageProperty stageProperty) {
        fillStateContainer(builder);
        builder.add(stageProperty);
    }

    @Override
    protected int getAge(BlockState state) {
        return state.get(this.getStage());
    }

    @Override
    public BlockState withAge(int age) {
        return this.getDefaultState().with(this.getStage(), age);
    }

    public IntegerProperty getStage() {
        return stage;
    }

    @Override
    public StateContainer<Block, BlockState> getStateContainer() {
        return stateContainer;
    }
}