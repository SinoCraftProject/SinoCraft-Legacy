package cx.rain.mc.forgemod.sinocraft.capability.storage;

import cx.rain.mc.forgemod.sinocraft.api.capability.IHeat;
import cx.rain.mc.forgemod.sinocraft.utility.UpdateHelper;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.IntNBT;
import net.minecraft.util.Direction;
import net.minecraftforge.common.capabilities.Capability;

public enum HeatStorage implements Capability.IStorage<IHeat> {

    INSTANCE;

    @Override
    public INBT writeNBT(Capability<IHeat> capability, IHeat instance, Direction side) {
        return IntNBT.valueOf(instance.getHeat());
    }

    @Override
    public void readNBT(Capability<IHeat> capability, IHeat instance, Direction side, INBT nbt) {
        IntNBT data = UpdateHelper.fixHeatData(nbt);
        instance.setHeat(data.getInt());
    }
}
