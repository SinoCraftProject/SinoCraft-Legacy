package cx.rain.mc.forgemod.sinocraft.api.factory;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IItemFactory;
import net.minecraft.item.Item;

import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

public class ItemFactory implements IItemFactory {
    @Override
    public Item get(Class<? extends Item> type, @Nullable Object[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return (Item) type.getConstructor().newInstance();
    }
}