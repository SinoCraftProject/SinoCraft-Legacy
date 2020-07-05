package cx.rain.mc.forgemod.culturecraft.api.interfaces;

import java.lang.reflect.InvocationTargetException;

public interface IFactory<T,R>{
    T get(R type) throws NoSuchMethodException,IllegalAccessException,InstantiationException,InvocationTargetException;
}