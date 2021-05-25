package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.utility.NBTHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class PlacedTeapot extends PlacedTableElement {

    private float leaves;
    private int water;
    private int tea;

    public PlacedTeapot(double x, double y) {
        super(x, y);
    }

    @Override
    public void load(ItemStack stack) {
        super.load(stack);
        leaves = NBTHelper.getOrDefault(stack, "potLeaves", 0f);
        water = NBTHelper.getOrDefault(stack, "potWater", 0);
        tea = NBTHelper.getOrDefault(stack, "potTea", 0);
    }

    @Override
    public ItemStack make() {
        ItemStack take = super.make();
        CompoundNBT tag = take.getOrCreateTag();
        tag.putFloat("potLeaves", leaves);
        tag.putInt("potWater", water);
        tag.putInt("potTea", tea);
        return take;
    }

    @Override
    double getLenX() {
        return 4.0 / 16.0;
    }

    @Override
    double getLenY() {
        return 4.0 / 16.0;
    }

    @Override
    double getLenZ() {
        return 4.0 / 16.0;
    }

    public float getLeaves() {
        return leaves;
    }

    public void setLeaves(float leaves) {
        this.leaves = leaves;
    }

    public int getWater() {
        return water;
    }

    public void setWater(int water) {
        this.water = water;
    }

    public int getTea() {
        return tea;
    }

    public void setTea(int tea) {
        this.tea = tea;
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
    }
}
