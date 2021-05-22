package cx.rain.mc.forgemod.sinocraft.utility;

public class Lazy<T> {

    private T value = null;
    private final String error;

    public Lazy(String error) {
        this.error = error;
    }

    public void set(T value) {
        if (this.value != null) {
            throw new UnsupportedOperationException("Value has been initialized");
        }
        this.value = value;
    }

    public T get() {
        if (value == null) {
            throw new UnsupportedOperationException("Value is not initialized: " + error);
        }
        return value;
    }
}
