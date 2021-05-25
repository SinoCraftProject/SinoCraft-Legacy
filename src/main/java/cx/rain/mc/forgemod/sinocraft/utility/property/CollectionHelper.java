package cx.rain.mc.forgemod.sinocraft.utility.property;

import net.minecraft.nbt.INBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.util.NonNullList;

import java.util.stream.Collector;

public class CollectionHelper {
    public static <T> Collector<T, ?, NonNullList<T>> toNonnullList() {
        return Collector.of(NonNullList::create, NonNullList::add,
                (left, right) -> { left.addAll(right); return left; },
                Collector.Characteristics.IDENTITY_FINISH);
    }

    public static <T extends INBT> Collector<T, ?, ListNBT> toNBTList() {
        return Collector.of(ListNBT::new, ListNBT::add,
                (left, right) -> { left.addAll(right); return left; },
                Collector.Characteristics.IDENTITY_FINISH);
    }
}
