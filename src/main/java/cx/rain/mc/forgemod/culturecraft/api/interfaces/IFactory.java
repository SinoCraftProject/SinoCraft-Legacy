package cx.rain.mc.forgemod.culturecraft.api.interfaces;

public interface IFactory<T,R>{
    T get(R type);
}