package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.block.BlockTeapot;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.item.ItemTeapot;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

public class TableTeapot extends BaseTableElement {

    private float leaves;
    private int water;
    private int tea;
    private VoxelShape shape0, shape1;

    public TableTeapot(double x, double y, double z) {
        super(x, y, z);
    }

    @Override
    public ItemStack makeItem() {
        return ItemTeapot.build(super.makeItem(), water, tea, leaves);
    }

    @Override
    public BlockState makeBlock() {
        // todo select teapot state
        return ModBlocks.TEAPOT.get().getDefaultState().with(BlockTeapot.FLUID, 0);
    }

    @Override
    public VoxelShape getShape() {
        return water > 0 ? shape0 : shape1;
    }

    public float getLeaves() {
        return leaves;
    }

    public void addLeaves(float leaves) {
        this.leaves += leaves;
    }

    public float takeLeaves(float leaves) {
        if (this.leaves >= leaves) {
            this.leaves -= leaves;
            return leaves;
        } else {
            float value = this.leaves;
            this.leaves = 0;
            return value;
        }
    }

    public int getWater() {
        return water;
    }

    public void addWater(int water) {
        this.water += water;
    }

    public int takeWater(int water) {
        if (this.water >= water) {
            this.water -= water;
            return water;
        } else {
            int value = this.water;
            this.water = 0;
            return value;
        }
    }

    public int getTea() {
        return tea;
    }

    public void addTea(int tea) {
        this.tea += tea;
    }

    public int takeTea(int tea) {
        if (this.tea >= tea) {
            this.tea -= tea;
            return tea;
        } else {
            int value = this.tea;
            this.tea = 0;
            return tea;
        }
    }

    @Override
    public void onPlaced(TileEntityTeaTable table, ItemStack stack, ItemUseContext context, double x, double z) {
        super.onPlaced(table, stack, context, x, z);
        shape0 = BlockTeapot.SHAPE0.withOffset(x, y, z);
        shape1 = BlockTeapot.SHAPE1.withOffset(x, y, z);
        ItemTeapot item = ModItems.TEAPOT.get();
        if (stack.getItem() == item) {
            leaves = item.getLeaves(stack);
            water = item.getWater(stack);
            tea = item.getTea(stack);
        } else {
            leaves = 0;
            water = 0;
            tea = 0;
        }
    }

    @Override
    public ActionResultType onActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return super.onActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        nbt.putFloat("leaves", leaves);
        nbt.putInt("water", water);
        nbt.putInt("tea", tea);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        leaves = nbt.getFloat("leaves");
        water = nbt.getInt("water");
        tea = nbt.getInt("tea");
        shape0 = BlockTeapot.SHAPE0.withOffset(x, y, z);
        shape1 = BlockTeapot.SHAPE1.withOffset(x, y, z);
    }
}
