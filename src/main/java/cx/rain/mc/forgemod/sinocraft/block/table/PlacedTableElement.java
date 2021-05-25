package cx.rain.mc.forgemod.sinocraft.block.table;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraftforge.common.util.INBTSerializable;

import java.awt.geom.Rectangle2D;

public abstract class PlacedTableElement implements INBTSerializable<CompoundNBT> {

    protected ItemStack stack;
    protected AxisAlignedBB range;
    protected Rectangle2D.Double rect;

    public PlacedTableElement(double x, double z) {
        range = new AxisAlignedBB(x, 0, z, x + getLenX(), getLenY(), z + getLenZ());
        rect = new Rectangle2D.Double(x, z, getLenX(), getLenZ());
    }

    public void load(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setCount(1);
        this.stack = copy;
    }

    public ItemStack make() {
        return this.stack.copy();
    }

    public boolean isDisjoint(PlacedTableElement element) {
        return rect.createIntersection(element.rect).isEmpty();
    }

    public boolean contains(double x, double z) {
        return range.contains(x, 0, z);
    }

    public AxisAlignedBB getRange() {
        return range;
    }

    public abstract double getLenX();

    public abstract double getLenY();

    public abstract double getLenZ();

    @Override
    public CompoundNBT serializeNBT() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.put("stack", stack.serializeNBT());
        nbt.putFloat("x", (float) range.minX);
        nbt.putFloat("y", (float) range.minY);
        nbt.putFloat("z", (float) range.minZ);
        return nbt;
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        this.stack = ItemStack.read(nbt.getCompound("stack"));
        float minX = nbt.getFloat("x");
        float minY = nbt.getFloat("y");
        float minZ = nbt.getFloat("z");
        this.range = new AxisAlignedBB(minX, minY, minZ, minX + getLenX(), minY + getLenY(), minZ + getLenZ());
        this.rect = new Rectangle2D.Double(minX, minZ, getLenX(), getLenZ());
    }

    public static ItemStack deserializeItem(CompoundNBT nbt) {
        return ItemStack.read(nbt.getCompound("stack"));
    }
}
