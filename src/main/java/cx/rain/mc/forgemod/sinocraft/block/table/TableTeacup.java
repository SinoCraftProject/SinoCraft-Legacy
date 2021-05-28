package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.api.table.BaseTableElement;
import cx.rain.mc.forgemod.sinocraft.api.table.ScaleFunction;
import cx.rain.mc.forgemod.sinocraft.block.BlockTeacup;
import cx.rain.mc.forgemod.sinocraft.block.ModBlocks;
import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.item.ItemTeacup;
import cx.rain.mc.forgemod.sinocraft.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.World;

public class TableTeacup extends BaseTableElement {
    public static final float SCALE_XZ = 0.33f;
    public static final float SCALE_Y = 0.8f;
    private static final VoxelShape SHAPE = VoxelShapes.create(0.1875 * SCALE_XZ, 0, 0.1875 * SCALE_XZ, 0.8125 * SCALE_XZ, 0.4375 * SCALE_Y, 0.8125 * SCALE_XZ);

    private int teaCount = 0;
    private VoxelShape shape;

    public TableTeacup(double x, double y, double z) {
        super(x, y, z);
        shape = SHAPE.withOffset(x, y, z);
    }

    @Override
    public ItemStack makeItem() {
        return ItemTeacup.build(super.makeItem(), teaCount);
    }

    @Override
    public BlockState makeBlock() {
        return ModBlocks.TEACUP.get().getDefaultState().with(BlockTeacup.WITH_TEA, teaCount > 0);
    }

    @Override
    public VoxelShape getShape() {
        return shape;
    }

    @Override
    public void scale(ScaleFunction function) {
        function.scale(SCALE_XZ, SCALE_Y, SCALE_XZ);
    }

    public int getTea() {
        return teaCount;
    }

    public int addTea(int teaCount) {
        if (isFull()) return 0;
        int newCount = this.teaCount + teaCount;
        int capacity = getCapacity();
        if (newCount <= capacity) {
            this.teaCount = newCount;
            return teaCount;
        } else {
            int add = capacity - this.teaCount;
            this.teaCount = capacity;
            return add;
        }
    }

    public int takeTea(int teaCount) {
        if (this.teaCount >= teaCount) {
            this.teaCount -= teaCount;
            return teaCount;
        } else {
            int value = this.teaCount;
            this.teaCount = 0;
            return value;
        }
    }

    public boolean isFull() {
        return teaCount >= getCapacity();
    }

    public boolean isEmpty() {
        return teaCount <= 0;
    }

    public int getCapacity() {
        return 1000;
    }

    @Override
    public void onPlaced(TileEntityTeaTable table, ItemStack stack, ItemUseContext context, double x, double z) {
        super.onPlaced(table, stack, context, x, z);
        ItemTeacup item = ModItems.TEACUP.get();
        teaCount = item == stack.getItem() ? item.getTea(stack) : 0;
    }

    @Override
    public ActionResultType onActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack heldItem = player.getHeldItem(handIn);
        Item item = heldItem.getItem();
        if (item == ModItems.TEAPOT.get()) {
        }
        return super.onActivated(state, worldIn, pos, player, handIn, hit);
    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = super.serializeNBT();
        nbt.putInt("count", teaCount);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        super.deserializeNBT(nbt);
        teaCount = nbt.getInt("count");
        shape = SHAPE.withOffset(x, y ,z);
    }
}
