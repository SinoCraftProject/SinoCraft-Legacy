package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.utility.ItemTagHelper;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.util.Constants;

import java.util.Iterator;
import java.util.List;

public class TileEntityTeaTable extends TileEntityUpdatableBase {

    // todo 有材质后调整大小
    private static final double TEACUP_WIDTH = 4.0 / 16.0;
    private static final double TEACUP_HEIGHT = 4.0 / 16.0;
    private static final double TEAPOT_WIDTH = 4.0 / 16.0;
    private static final double TEAPOT_HEIGHT = 4.0 / 16.0;

    private final List<PlacedTeacup> teacups = NonNullList.create();
    private final List<PlacedTeapot> teapots = NonNullList.create();

    public TileEntityTeaTable() {
        super(ModTileEntities.TEA_TABLE.get());
    }

    public boolean putTeacup(double x, double z, ItemStack teacup) {
        double x1 = x + TEACUP_WIDTH;
        double z1 = z + TEACUP_HEIGHT;
        for (PlacedTeacup cup : teacups) {
            if (isIn(cup.x0, cup.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(cup.x1, cup.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(cup.x0, cup.z1, x, z, x1, z1)) {
                return false;
            }
            if (isIn(cup.x1, cup.z1, x, z, x1, z1)) {
                return false;
            }
        }
        for (PlacedTeapot pot : teapots) {
            if (isIn(pot.x0, pot.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(pot.x1, pot.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(pot.x0, pot.z1, x, z, x1, z1)) {
                return false;
            }
            if (isIn(pot.x1, pot.z1, x, z, x1, z1)) {
                return false;
            }
        }
        boolean hasTea = ItemTagHelper.getOrDefault(teacup, "hasTea", false);
        ItemStack copy = teacup.copy();
        copy.setCount(1);
        teacups.add(new PlacedTeacup(x, z, x1, z1, hasTea, copy));
        return true;
    }

    public boolean putTeapot(double x, double z, ItemStack teapot) {
        double x1 = x + TEAPOT_WIDTH;
        double z1 = z + TEAPOT_HEIGHT;
        for (PlacedTeacup cup : teacups) {
            if (isIn(cup.x0, cup.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(cup.x1, cup.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(cup.x0, cup.z1, x, z, x1, z1)) {
                return false;
            }
            if (isIn(cup.x1, cup.z1, x, z, x1, z1)) {
                return false;
            }
        }
        for (PlacedTeapot pot : teapots) {
            if (isIn(pot.x0, pot.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(pot.x1, pot.z0, x, z, x1, z1)) {
                return false;
            }
            if (isIn(pot.x0, pot.z1, x, z, x1, z1)) {
                return false;
            }
            if (isIn(pot.x1, pot.z1, x, z, x1, z1)) {
                return false;
            }
        }
        byte contains = ItemTagHelper.getOrDefault(teapot, "teaContains", (byte) 0);
        boolean hasLeaf = (contains & 0b0001) == 0b0001;
        boolean hasWater = (contains & 0b0010) == 0b0010;
        boolean hasTea = (contains & 0b0100) == 0b0100;
        int progress = ItemTagHelper.getOrDefault(teapot, "teaProcess", 0);
        ItemStack copy = teapot.copy();
        copy.setCount(1);
        teapots.add(new PlacedTeapot(x, z, x1, z1, hasLeaf, hasWater, hasTea, progress, copy));
        if (world != null && !world.isRemote) {
            markDirty();
        }
        return true;
    }

    public ItemStack take(double x, double z, boolean simulate) {
        Iterator<PlacedTeacup> teacupItr = teacups.iterator();
        while (teacupItr.hasNext()) {
            PlacedTeacup teacup = teacupItr.next();
            if (isIn(x, z, teacup.x0, teacup.z0, teacup.x1, teacup.z1)) {
                ItemStack cup = teacup.stack.copy();
                cup.getOrCreateTag().putBoolean("hasTea", teacup.hasTea);
                if (!simulate) {
                    teacupItr.remove();
                    markDirty();
                }
                return cup;
            }
        }
        Iterator<PlacedTeapot> teapotItr = teapots.iterator();
        while (teapotItr.hasNext()) {
            PlacedTeapot teapot = teapotItr.next();
            if (isIn(x, z, teapot.x0, teapot.z0, teapot.x1, teapot.z1)) {
                ItemStack pot = teapot.stack.copy();
                CompoundNBT tag = pot.getOrCreateTag();
                byte b = 0b0000;
                if (teapot.hasTeaLeaf) {
                    b |= 0b0001;
                }
                if (teapot.hasWater) {
                    b |= 0b0010;
                }
                if (teapot.hasTea) {
                    b |= 0b0100;
                }
                tag.putByte("teaContains", b);
                if (teapot.process > 0) {
                    tag.putInt("teaProcess", teapot.process);
                }
                if (!simulate) {
                    teapotItr.remove();
                    markDirty();
                }
                return pot;
            }
        }
        return ItemStack.EMPTY;
    }

    private boolean isIn(double x, double z, double x0, double z0, double x1, double z1) {
        return x >= x0 && x <= x1 && z >= z0 && z <= z1;
    }

    @Override
    public void tick() {
        if (world != null && !world.isRemote && teacups.isEmpty() && teapots.isEmpty()) {
            world.setBlockState(pos, Blocks.AIR.getDefaultState());
        }
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        teacups.clear();
        teapots.clear();
        ListNBT cups = nbt.getList("cups", Constants.NBT.TAG_COMPOUND);
        ListNBT pots = nbt.getList("pots", Constants.NBT.TAG_COMPOUND);
    }

    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound = super.write(compound);
        ListNBT cups = new ListNBT();
        ListNBT pots = new ListNBT();
        compound.put("cups", cups);
        compound.put("pots", pots);
        return compound;
    }

    public static class PlacedTeacup {
        public double x0, z0, x1, z1;
        public boolean hasTea;
        final public ItemStack stack;

        public PlacedTeacup(double x0, double z0, double x1, double z1, boolean hasTea, ItemStack stack) {
            this.x0 = x0;
            this.z0 = z0;
            this.x1 = x1;
            this.z1 = z1;
            this.hasTea = hasTea;
            this.stack = stack;
        }
    }

    public static class PlacedTeapot {
        public double x0, z0, x1, z1;
        public boolean hasTeaLeaf;
        public boolean hasWater;
        public boolean hasTea;
        public int process;
        public ItemStack stack;

        public PlacedTeapot(double x0, double z0, double x1, double z1, boolean hasTeaLeaf, boolean hasWater, boolean hasTea, int process, ItemStack stack) {
            this.x0 = x0;
            this.z0 = z0;
            this.x1 = x1;
            this.z1 = z1;
            this.hasTeaLeaf = hasTeaLeaf;
            this.hasWater = hasWater;
            this.hasTea = hasTea;
            this.process = process;
            this.stack = stack;
        }
    }
}
