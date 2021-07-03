package cx.rain.mc.forgemod.sinocraft.api.table;

import cx.rain.mc.forgemod.sinocraft.block.tileentity.TileEntityTeaTable;
import cx.rain.mc.forgemod.sinocraft.utility.MathHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUseContext;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.common.util.INBTSerializable;

/**
 * An element placed on a table.
 */
public abstract class BaseTableElement implements INBTSerializable<CompoundNBT> {

    protected ItemStack stack;
    protected double x, y, z;

    public BaseTableElement(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /**
     * Create a new ItemStack to drop.
     */
    public ItemStack makeItem() {
        ItemStack stack = this.stack.copy();
        stack.setCount(1);
        return stack;
    }

    /**
     * Create a new BlockState to place or render.
     */
    public BlockState makeBlock() {
        return Blocks.AIR.getDefaultState();
    }

    /**
     * Check if this has any intersection or coincidence with this other element.
     */
    public boolean isDisjoint(BaseTableElement element) {
        AxisAlignedBB thisBox = getShape().getBoundingBox();
        AxisAlignedBB otherBox = element.getShape().getBoundingBox();
        return !thisBox.intersects(otherBox) && !MathHelper.contains(thisBox, otherBox) && !MathHelper.contains(otherBox, thisBox);
    }

    /**
     * Return true if the element contains position.
     */
    public boolean contains(double x, double y, double z) {
        AxisAlignedBB aabb = getShape().getBoundingBox();
        // allow edges
        return x - aabb.minX >= -1e-5
            && x - aabb.maxX <=  1e-5
            && y - aabb.minY >= -1e-5
            && y - aabb.maxY <=  1e-5
            && z - aabb.minZ >= -1e-5
            && z - aabb.maxZ <=  1e-5;
    }

    /**
     * Return the shape of the element on table.
     */
    public abstract VoxelShape getShape();

    /**
     * Zoom when drawing.
     */
    public void scale(ScaleFunction function) {

    }

    /**
     * Translating when drawing.
     */
    public void translate(TranslateFunction function) {
        function.translate(x, y, z);
    }

    /**
     * Check if any element is conflict with this element when this element placed on the table.
     */
    public boolean isConflicted(BaseTableElement other) {
        return !isDisjoint(other);
    }

    /**
     * Called when the element placed on the table.
     */
    public void onPlaced(TileEntityTeaTable table, ItemStack stack, ItemUseContext context, double x, double z) {
        this.stack = stack.copy();
        this.stack.setCount(1);
    }

    /**
     * Called when other item click the element.
     */
    public ActionResultType onActivated(BlockState state, TileEntityTeaTable table, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        return ActionResultType.FAIL;
    }

    /**
     * Called when the element placed on the table every tick.
     *
     * @return False if the element will be removed.
     */
    public boolean tick(TileEntityTeaTable table) {
        return true;
    }

    /**
     * Get the stack in the element.
     */
    public ItemStack getStack() {
        return stack;
    }

    /**
     * Called when player take the element as an item
     */
    public void onTakeItem(PlayerEntity player, ItemStack stack) {

    }

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("stack", stack.serializeNBT());
        nbt.putFloat("x", (float) x);
        nbt.putFloat("y", (float) y);
        nbt.putFloat("z", (float) z);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.stack = ItemStack.read(nbt.getCompound("stack"));
        this.x = nbt.getFloat("x");
        this.y = nbt.getFloat("y");
        this.z = nbt.getFloat("z");
    }
}
