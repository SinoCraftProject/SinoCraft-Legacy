package cx.rain.mc.forgemod.sinocraft.utility;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class DebugHelper {

    public static Map<String, Object> debugData = new HashMap<>();

    public static void putValue(String key, Object value) {
        debugData.put(key, value);
    }
    public static boolean updateValue(String key, Object value) {
        if (debugData.containsKey(key)) {
            Object o = debugData.get(key);
            if (Objects.equals(o, value)) {
                return false;
            }
        }
        debugData.put(key, value);
        return true;
    }
    public static void removeValue(String key) {
        debugData.remove(key);
    }
    public static <T> T getValue(String key) {
        Object o = debugData.get(key);
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return null;
        }
    }
    public static <T> T getValue(String key, T defaultValue) {
        Object o = debugData.getOrDefault(key, defaultValue);
        try {
            return (T) o;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }
}
