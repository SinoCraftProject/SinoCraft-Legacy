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

    protected IPlantData type = null;

    public BlockPlant(IPlantData typeIn) {
        super(BlockBehaviour.Properties.of(Material.PLANT));

        type = typeIn;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return type.getProperty();
    }

    @Override
    public int getMaxAge() {
        return type.getProperty().getMaxStage();
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter world, BlockPos pos, CollisionContext context) {
        var age = state.getValue(getAgeProperty());
        var stage = age / ((getMaxAge() + 1) / 4);
        return SHAPES[stage];
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return type.getSeed().get();
    }

    @Override
    public BlockState getStateForAge(int age) {
        return this.defaultBlockState().setValue(this.getAgeProperty(), age);
    }

    // qyl: Why I override this method? 2022/1/17.
    @Override
    @NotNull
    public StateDefinition<Block, BlockState> getStateDefinition() {
        return stateDefinition;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(getAgeProperty());
    }

    @Override
    protected int getBonemealAgeIncrease(Level world) {
        return type.growWithBonus(world.random);
    }

    @Override
    public void growCrops(Level world, BlockPos pos, BlockState state) {
        super.growCrops(world, pos, state);
    }
}
