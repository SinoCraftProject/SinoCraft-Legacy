package cx.rain.mc.forgemod.sinocraft.block.tileentity;

import cx.rain.mc.forgemod.sinocraft.api.capability.IWindEnergy;
import net.minecraft.tileentity.ITickableTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import static cx.rain.mc.forgemod.sinocraft.api.capability.CapabilityWindEnergy.CAPABILITY;

public class TileEntityBellows extends TileEntity implements ITickableTileEntity {
    private int we = 0;
    private int cd = 0;

    public TileEntityBellows() {
        super(ModTileEntities.BELLOWS.get());
    }

    public void addWe() {
        we ++;
    }

    @NotNull
    @Override
    public <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == CAPABILITY) {
            return LazyOptional.of(() -> new IWindEnergy() {
                @Override
                public int getWindEnergy() {
                    return we;
                }

                @Override
                public void setWindEnergy(int we) {
                }

                @Override
                public void resetWindEnergy() {
                }

                @Override
                public void addWindEnergy(int we) {
                }

                @Override
                public void subWindEnergy(int we) {
                }
            }).cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void tick() {
        if (cd > 0)
            cd --;
        if (we > 0 && cd <= 0) {
            we --;
            cd = 200;
        }
    }
}
