package cx.rain.mc.forgemod.culturecraft.block.base;

import cx.rain.mc.forgemod.culturecraft.enumerate.PlantType;
import net.minecraft.block.*;
import net.minecraft.block.Blocks;
import net.minecraft.item.Item;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.fml.RegistryObject;

public class BlockPlant extends CropsBlock {
    private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
            Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
    };

    private PlantType type = null;
    private RegistryObject<Item> seed = null;

    public BlockPlant(PlantType typeIn, RegistryObject<Item> seedIn) {
        super(Block.Properties.from(Blocks.CARROTS));
        setDefaultState(getStateContainer().getBaseState().with(getAgeProperty(), 0));

        type = typeIn;
        seed = seedIn;
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        int age = state.get(getAgeProperty());
        int stage = age / 2;
        return SHAPE_BY_AGE[stage];
    }

    @Override
    public IItemProvider getSeedsItem() {
        return seed.get();
    }
}