package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontal;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStove;
import cx.rain.mc.forgemod.sinocraft.utility.FuelHelper;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class BlockStove extends BlockHorizontal {
    public static final BooleanProperty BURNING = BooleanProperty.create("burning");

    public BlockStove() {
        super(AbstractBlock.Properties
                .create(Material.GOURD, MaterialColor.GRAY)
                .setRequiresTool()
                .hardnessAndResistance(3.5F));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(BURNING);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return super.getStateForPlacement(context).with(BURNING, false);
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        if (FuelHelper.isFuel(stack)) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityStove) {
                TileEntityStove stove = (TileEntityStove) tile;
                stove.addBurnTime(FuelHelper.getItemBurnTime(stack));
                stack.shrink(1);
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    @Override
    public int getLightValue(BlockState state, IBlockReader world, BlockPos pos) {
        if (state.get(BURNING)) {
            return 14;
        }
        return super.getLightValue(state, world, pos);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return new TileEntityStove();
    }
}
