package cx.rain.mc.forgemod.sinocraft.block.plant;

import cx.rain.mc.forgemod.sinocraft.api.block.plant.IPlantData;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.NotNull;

public class BlockPlant extends CropBlock {
    protected static final VoxelShape[] SHAPES = new VoxelShape[]{
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.box(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
    };

    protected IPlantData data = null;
    protected StateDefinition<Block, BlockState> stateDefinition = null;

    public BlockPlant(IPlantData dataIn) {
        super(BlockBehaviour.Properties.of(Material.PLANT));

        data = dataIn;

        // qyl: Replace StateContainer. 2022.1.20.
        StateDefinition.Builder<Block, BlockState> builder = new StateDefinition.Builder<>(this);
        addPropertyToStateDefinition(builder, getNewAgeProperty());
//        addDefaultPropertyToStateDefinition(builder);
        stateDefinition = builder.create(Block::defaultBlockState, BlockState::new);
        registerDefaultState(stateDefinition.any().setValue(getNewAgeProperty(), 0));
    }

    public IntegerProperty getNewAgeProperty() {
        return data.getProperty();
    }

    @Override
    public int getMaxAge() {
        return data.getProperty().getMaxStage();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        var age = state.getValue(getNewAgeProperty());
        var stage = age / ((getMaxAge() + 1) / 4);
        return SHAPES[stage];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return data.getSeed().get();
    }

    @Override
    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getNewAgeProperty(), age);
    }

    // qyl: Why I override this method? 2022/1/17.
    // qyl: For replacing the default stateDefinition. 2022/1/20.
    @Override
    @NotNull
    public StateDefinition<Block, BlockState> getStateDefinition() {
        return stateDefinition;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
//        builder.add(getAgeProperty());
        // qyl: Disable original age state, use new stage state instead. 2022.1.20.
        super.createBlockStateDefinition(builder);
    }

    // qyl: Codes below here are used to use our stage state. 2022.1.20.
    protected void addPropertyToStateDefinition(StateDefinition.Builder<Block, BlockState> builder, Property<?> property) {
        builder.add(property);
    }

    protected void addDefaultPropertyToStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
    }


    @Override
    protected int getBonemealAgeIncrease(Level world) {
        return data.growWithBonus(world.random);
    }

//    @Override
//    public void growCrops(Level world, BlockPos pos, BlockState state) {
//        super.growCrops(world, pos, state);
//    }
}
