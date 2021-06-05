package cx.rain.mc.forgemod.sinocraft.api.utility;

public class Lazy<T> {

    private T value = null;

    public void set(T value) {
        if (this.value != null) {
            throw new UnsupportedOperationException("Value has been initialized");
        }
        this.value = value;
    }

    public T get() {
        if (value == null) {
            throw new UnsupportedOperationException("Value is not initialized: Can't found SinoCraft mod");
        }
        return value;
    }

    public boolean hasInstance() {
        return value != null;
    }

    public <R> Lazy<R> cast() {
        return (Lazy<R>)this;
    }
}
