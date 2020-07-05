package cx.rain.mc.forgemod.culturecraft.api.interfaces;

import net.minecraft.item.Item;
import java.lang.reflect.InvocationTargetException;

public class ItemFactory implements IItemFactory{
    @Override
    public Item get(Class<? extends Item> type,@Nullable Object[] args) throws NoSuchMethodException,IllegalAccessException,InstantiationException,InvocationTargetException{
        return (Item)type.getConstructor().newInstance();
    }
}