package cx.rain.mc.forgemod.sinocraft.api.factory;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IItemFactory;
import net.minecraft.item.Item;

import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;

/**
 * @deprecated 该接口不再有任何用处
 */
public class ItemFactory implements IItemFactory {
    @Override
    public Item get(Class<? extends Item> type, @Nullable Object[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return (Item) type.getConstructor().newInstance();
    }
}