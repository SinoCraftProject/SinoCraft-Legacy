package cx.rain.mc.forgemod.sinocraft.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class CapabilityTeapot {

    @CapabilityInject(CapTeapot.class)
    public static Capability<CapTeapot> CAPABILITY;
    public static final Storage STORAGE = new Storage();
    public static final CapTeapot DUMMY = new CapTeapot() {

        @Override
        public float getLeaves() {
            return 0;
        }

        @Override
        public int getTea() {
            return 0;
        }

        @Override
        public int getWater() {
            return 0;
        }

        @Override
        public CompoundNBT serializeNBT() {
            return new CompoundNBT();
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {

        }

        @Override
        public boolean isValid() {
            return false;
        }
    };

    public static class CapTeapot implements ICapabilitySerializable<CompoundNBT> {

        private float leaves;
        private final float leafCapacity = 10f;
        private int water, tea;
        private boolean isPouring = false;
        private final int fluidCapacity = 2000;
        private boolean isEmpty = true;

        public float getLeaves() {
            return leaves;
        }

        public float addLeaves(float leaves) {
            float newLeaves = Math.min(this.leaves + leaves, leafCapacity);
            float add = newLeaves - this.leaves;
            this.leaves = newLeaves;
            this.isEmpty = false;
            return add;
        }

        public float takeLeaves(float leaves) {
            float newLeaves = Math.max(this.leaves - leaves, 0);
            float take = this.leaves - newLeaves;
            this.leaves = newLeaves;
            this.isEmpty = false;
            return take;
        }

        public void setLeaves(float leaves) {
            this.leaves = Math.min(leafCapacity, leaves);
            this.isEmpty = false;
        }

        public int getWater() {
            return water;
        }

        public int addWater(int water) {
            int capacity = fluidCapacity - tea;
            int newWater = Math.min(capacity, this.water + water);
            int add = newWater - this.water;
            this.water = newWater;
            this.isEmpty = false;
            return add;
        }

        public int takeWater(int water) {
            int newWater = Math.max(0, this.water - water);
            int take = this.water - newWater;
            this.water = newWater;
            this.isEmpty = false;
            return take;
        }

        public void setWater(int water) {
            this.water = Math.min(water, fluidCapacity - tea);
            this.isEmpty = false;
        }

        public int getTea() {
            return tea;
        }

        public int addTea(int tea) {
            int capacity = fluidCapacity - water;
            int newTea = Math.min(capacity, this.tea + tea);
            int add = newTea - this.tea;
            this.tea = newTea;
            this.isEmpty = false;
            return add;
        }

        public int takeTea(int tea) {
            int newTea = Math.max(0, this.tea - tea);
            int take = this.tea - newTea;
            this.tea = newTea;
            this.isEmpty = false;
            return take;
        }

        public void setTea(int tea) {
            this.tea = Math.min(tea, fluidCapacity - water);
            this.isEmpty = false;
        }

        public boolean isPouring() {
            return isPouring;
        }

        public void setPouring(boolean pouring) {
            isPouring = pouring;
            isEmpty = false;
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("tea", tea);
            nbt.putInt("water", water);
            nbt.putFloat("leaves", leaves);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            this.tea = nbt.getInt("tea");
            this.water = nbt.getInt("water");
            this.leaves = nbt.getFloat("leaves");
            this.isEmpty = false;
        }

        @Nonnull
        @Override
        public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
            if (CAPABILITY != null) {
                return CAPABILITY.orEmpty(cap, LazyOptional.of(() -> this));
            }
            return LazyOptional.empty();
        }

        public boolean isValid() {
            return true;
        }

        public boolean isEmpty() {
            return isEmpty;
        }
    }

    public static class Storage implements Capability.IStorage<CapTeapot> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<CapTeapot> capability, CapTeapot instance, Direction side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<CapTeapot> capability, CapTeapot instance, Direction side, INBT nbt) {
            if (nbt instanceof CompoundNBT) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }
    }
}