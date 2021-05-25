package cx.rain.mc.forgemod.sinocraft.block.table;

import cx.rain.mc.forgemod.sinocraft.utility.NBTHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

public class PlacedTeacup extends PlacedTableElement {

    private int teaCount = 0;

    public PlacedTeacup(double x, double y) {
        super(x, y);
    }

    @Override
    public void load(ItemStack stack) {
        super.load(stack);
        teaCount = NBTHelper.getOrDefault(stack, "teaCount", 0);
    }

    @Override
    public ItemStack make() {
        ItemStack take = super.make();
        take.getOrCreateTag().putInt("teaCount", teaCount);
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

    public int getTeaCount() {
        return teaCount;
    }

    public void setTeaCount(int teaCount) {
        this.teaCount = teaCount;
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
    }
}
