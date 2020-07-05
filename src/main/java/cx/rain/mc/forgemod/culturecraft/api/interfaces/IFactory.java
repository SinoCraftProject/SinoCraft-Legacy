package cx.rain.mc.forgemod.culturecraft.api.interfaces;

import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

public interface IFactory<T,R>{
    T get(R type,@Nullable Object[] args) throws NoSuchMethodException,IllegalAccessException,InstantiationException,InvocationTargetException;
}