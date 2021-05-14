package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import net.minecraft.nbt.CompoundNBT;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.templates.FluidTank;

public class VatFluidHandler extends FluidTank implements INBTSerializable<CompoundNBT> {

    private final TileEntityVat te;

    public VatFluidHandler(TileEntityVat te) {
        super(1000);
        this.te = te;
    }

    @Override
    protected void onContentsChanged() {
        te.markDirty();
    }

    @Override
    public CompoundNBT serializeNBT() {
        return fluid.writeToNBT(new CompoundNBT());
    }

    @Override
    public void deserializeNBT(CompoundNBT nbt) {
        fluid = FluidStack.loadFluidStackFromNBT(nbt);
    }

    public void clear() {
        fluid = FluidStack.EMPTY;
        onContentsChanged();
    }
}
