package cx.rain.mc.forgemod.sinocraft.api.interfaces;

import net.minecraft.item.ItemStack;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.NonNullList;

/**
 * All Tile Entity of machines must implement this interface
 * @author flysong
 */
public interface IMachine {
    /**
     * Get machine state
     * @return Machine State
     */
    MachineState getWorkingState();

    /**
     * Set machine state
     * @param state Machine State
     */
    void setWorkingState(MachineState state);

    /**
     * MachineState Enum
     * All machines must be in one of 5 states
     */
    enum MachineState implements IStringSerializable {
        /**Close*/
        CLOSE("close"),
        /**IDLE*/
        IDLE("idle"),
        /**Working*/
        WORKING("working"),
        /**Overload*/
        OVERLOAD("overload"),
        /**damaged*/
        DAMAGED("damaged");
        private String name;

        /**
         * Get the enum constant's name
         * @return The enum constant's name
         */
        @Override
        public String getName(){
            return name;
        }

        MachineState(String name){
            this.name=name;
        }
    }

    NonNullList<ItemStack> getDropsItem(NonNullList<ItemStack> list);
}
