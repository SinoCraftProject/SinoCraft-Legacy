package cx.rain.mc.forgemod.sinocraft.capability;

import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.INBT;
import net.minecraft.profiler.IProfiler;
import net.minecraft.resources.IFutureReloadListener;
import net.minecraft.resources.IResourceManager;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AddReloadListenerEvent;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;

public class CapabilityTeacup {

    @CapabilityInject(CapTeacup.class)
    public static Capability<CapTeacup> CAPABILITY;
    public static final Storage STORAGE = new Storage();
    public static final CapTeacup DUMMY = new CapTeacup() {

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

    public static class CapTeacup implements ICapabilitySerializable<CompoundNBT> {

        private int tea;

        public int getTea() {
            return tea;
        }

        public int addTea(int tea) {
            int newTea = Math.min(getCapacity(), this.tea + tea);
            int add = newTea - this.tea;
            this.tea = newTea;
            return add;
        }

        public int takeTea(int tea) {
            int newTea = Math.max(0, this.tea - tea);
            int take = this.tea - newTea;
            this.tea = newTea;
            return take;
        }

        public void setTea(int tea) {
            this.tea = Math.min(tea, getCapacity());
        }

        public int getCapacity() {
            return 1000;
        }

        @Override
        public CompoundNBT serializeNBT() {
            CompoundNBT nbt = new CompoundNBT();
            nbt.putInt("tea", tea);
            return nbt;
        }

        @Override
        public void deserializeNBT(CompoundNBT nbt) {
            this.tea = nbt.getInt("tea");
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
    }

    public static class Storage implements Capability.IStorage<CapTeacup> {

        @Nullable
        @Override
        public INBT writeNBT(Capability<CapTeacup> capability, CapTeacup instance, Direction side) {
            return instance.serializeNBT();
        }

        @Override
        public void readNBT(Capability<CapTeacup> capability, CapTeacup instance, Direction side, INBT nbt) {
            if (nbt instanceof CompoundNBT) {
                instance.deserializeNBT((CompoundNBT) nbt);
            }
        }
    }
}