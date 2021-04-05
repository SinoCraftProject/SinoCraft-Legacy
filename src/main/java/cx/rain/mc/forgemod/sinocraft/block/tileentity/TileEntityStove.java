package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.base.TileEntityMachineBase;
import cx.rain.mc.forgemod.sinocraft.api.capability.FuelStackHandler;
import cx.rain.mc.forgemod.sinocraft.api.interfaces.IThermal;
import cx.rain.mc.forgemod.sinocraft.block.BlockStove;
import cx.rain.mc.forgemod.sinocraft.capability.ModCapabilities;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Direction;
import net.minecraft.util.NonNullList;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.CapabilityItemHandler;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TileEntityStove extends TileEntityMachineBase implements IThermal {
    private int thermal = 0;
    private int burn_cd = 0;
    private int level;

    private FuelStackHandler fuelHandler = new FuelStackHandler(1){
        @Override
        protected void onContentsChanged(int slot) {
            markDirty();
        }
    };

    public TileEntityStove() {
        super(ModTileEntities.STOVE.get());
        level = 1;
    }


    public TileEntityStove(int level) {
        super(ModTileEntities.STOVE.get());
        this.level = level;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ModCapabilities.THERMAL_CAPABILITY) {
            return LazyOptional.of(() -> this).cast();
        }
        if (cap == CapabilityItemHandler.ITEM_HANDLER_CAPABILITY && side == Direction.NORTH) {
            return LazyOptional.of(() -> fuelHandler).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if (this.world.isRemote || this.state == MachineState.DAMAGED)
            return;
        if (thermal == 0 && this.state != MachineState.CLOSE) {
            this.state = MachineState.CLOSE;
            this.world.setBlockState(this.pos ,this.getBlockState().with(BlockStove.STATE, state));
            markDirty();
        }
        if (thermal > 0 && thermal < 10000 && this.state != MachineState.WORKING) {
            this.state = MachineState.WORKING;
            this.world.setBlockState(this.pos ,this.getBlockState().with(BlockStove.STATE, state));
            markDirty();
        }
        if (thermal > 10000 && thermal < 20000 && this.state != MachineState.OVERLOAD) {
            this.state = MachineState.OVERLOAD;
            this.world.setBlockState(this.pos ,this.getBlockState().with(BlockStove.STATE, state));
            markDirty();
        }
        if (thermal > 20000 && this.state != MachineState.DAMAGED) {
            this.state = MachineState.DAMAGED;
            this.world.setBlockState(this.pos ,this.getBlockState().with(BlockStove.STATE, state));
            resetThermal();
            this.burn_cd = 0;
            markDirty();
        }
        if (thermal > 0) {
            thermal -= 5;
            markDirty();
        }
        if (burn_cd > 0) {
            burn_cd --;
            markDirty();
        }
        else if (fuelHandler.getStackInSlot(0).getCount() > 0 && fuelHandler.getStackInSlot(0) != ItemStack.EMPTY) {
            thermal += ForgeHooks.getBurnTime(fuelHandler.getStackInSlot(0));
            burn_cd = thermal / (level * 10);
            fuelHandler.extractItem(0, 1, false);
            markDirty();
        }
    }


    @Override
    public CompoundNBT write(CompoundNBT compound) {
        compound.putInt("thermal", thermal);
        compound.putInt("burn_cd", burn_cd);
        compound.put("fuel", fuelHandler.serializeNBT());
        return super.write(compound);
    }

    @Override
    public void read(BlockState state, CompoundNBT compound) {
        thermal = compound.getInt("thermal");
        burn_cd = compound.getInt("burn_cd");
        fuelHandler.deserializeNBT(compound.getCompound("fuel"));
        super.read(state, compound);
    }

    @Override
    public NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list) {
        list.add(fuelHandler.getStackInSlot(0));
        return list;
    }

    @Override
    public int getThermal() {
        return thermal;
    }

    @Override
    public void setThermal(int thermal) {
        this.thermal = thermal;
    }

    @Override
    public void resetThermal() {
        thermal = 0;
    }

    @Override
    public void addThermal(int thermal) {
        this.thermal += thermal;
    }

    @Override
    public void subThermal(int thermal) {
        this.thermal -= thermal;
    }
}
