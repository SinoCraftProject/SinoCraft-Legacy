package cx.rain.mc.forgemod.sinocraft.block.tree;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.registries.RegistryObject;

import java.util.Random;

public class BlockLeavesGrowable extends LeavesBlock implements BonemealableBlock {
    public static BooleanProperty MATURE = BooleanProperty.create("mature");

    protected final RegistryObject<? extends ItemLike> fruit;

    public BlockLeavesGrowable(Properties properties, RegistryObject<? extends ItemLike> fruitIn) {
        super(properties);

        fruit = fruitIn;

        registerDefaultState(getStateDefinition().any()
                .setValue(DISTANCE, 7)
                .setValue(PERSISTENT, false)
                .setValue(getMatureProperty(), false));
    }

    public static BooleanProperty getMatureProperty() {
        return MATURE;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(getMatureProperty());
    }


    public boolean isMature(BlockState state) {
        return state.getValue(getMatureProperty());
    }

    public void setMature(Level level, BlockPos pos, BlockState state, boolean mature) {
        level.setBlockAndUpdate(pos, state.setValue(getMatureProperty(), mature));
    }

    public boolean isPersistent(BlockState state) {
        return state.getValue(PERSISTENT);
    }

    @Override
    public boolean isRandomlyTicking(BlockState state) {
        return !isPersistent(state);
    }

    @Override
    public void randomTick(BlockState state, ServerLevel levelIn, BlockPos pos, Random rand) {
        // Todo: qyl: Remaster the logic of fruit grow. They grows too fast. 2022.1.19.
        super.randomTick(state, levelIn, pos, rand);

        if (isMature(state)) {
            return;
        }

        if (isPersistent(state)) {
            return;
        }

        if (net.minecraftforge.common.ForgeHooks.onCropsGrowPre(levelIn, pos, state, rand.nextInt(25) == 1)) {
            performBonemeal(levelIn, rand, pos, state);
            net.minecraftforge.common.ForgeHooks.onCropsGrowPost(levelIn, pos, state);
        }
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos,
                                 Player player, InteractionHand hand, BlockHitResult hit) {
        if (isMature(state)) {
            level.addFreshEntity(new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
                    new ItemStack(fruit.get())));
            setMature(level, pos, state, false);
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.FAIL;
    }

    @Override
    public boolean isValidBonemealTarget(BlockGetter level, BlockPos pos, BlockState state, boolean isClient) {
        return !isMature(state);
    }

    @Override
    public boolean isBonemealSuccess(Level level, Random random, BlockPos pos, BlockState state) {
        return isMature(state);
    }

    @Override
    public void performBonemeal(ServerLevel level, Random random, BlockPos pos, BlockState state) {
        setMature(level, pos, state, true);
    }
}
