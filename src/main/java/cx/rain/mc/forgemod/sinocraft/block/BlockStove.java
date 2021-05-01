package cx.rain.mc.forgemod.sinocraft.block;

import cx.rain.mc.forgemod.sinocraft.block.base.BlockHorizontal;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityStove;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeHooks;
import org.jetbrains.annotations.Nullable;

public class BlockStove extends BlockHorizontal {
    public BlockStove() {
        super(AbstractBlock.Properties.create(Material.GOURD, MaterialColor.GRAY));
    }

    @Override
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos,
                                             PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack stack = player.getHeldItem(handIn);
        if (isFuel(stack)) {
            TileEntity tile = worldIn.getTileEntity(pos);
            if (tile instanceof TileEntityStove) {
                TileEntityStove stove = (TileEntityStove) tile;
                stove.addBurnTime(getItemBurnTime(stack));
                return ActionResultType.SUCCESS;
            }
        }
        return ActionResultType.PASS;
    }

    public static boolean isFuel(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack) > 0;
    }

    public static int getItemBurnTime(ItemStack stack) {
        return ForgeHooks.getBurnTime(stack);
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
