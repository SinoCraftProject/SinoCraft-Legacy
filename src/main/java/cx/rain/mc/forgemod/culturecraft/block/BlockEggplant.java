package cx.rain.mc.forgemod.culturecraft.block;

import cx.rain.mc.forgemod.culturecraft.api.enumerate.EggplantType;
import cx.rain.mc.forgemod.culturecraft.item.Items;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropsBlock;
import net.minecraft.util.IItemProvider;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;

public class BlockEggplant extends CropsBlock {
        private static final VoxelShape[] SHAPE_BY_AGE = new VoxelShape[]{
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 2.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 5.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 7.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 10.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 14.0D, 16.0D),
                Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
        };
        private EggplantType eggplantType = null;

        protected BlockEggplant(EggplantType type) {
                super(Block.Properties.from(Blocks.CARROTS));
                eggplantType = type;
                setDefaultState(getStateContainer().getBaseState().with(getAgeProperty(), 0));
        }

        @Override
        public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
                int age = state.get(getAgeProperty());
                return SHAPE_BY_AGE[age];
        }

        @Override
        public IItemProvider getSeedsItem() {
                switch (eggplantType) {
                        case EGGPLANT:
                                return Items.EGGPLANT_SEED.get();
                }
                return null;
        }
}
