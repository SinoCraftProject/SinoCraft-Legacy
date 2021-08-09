package cx.rain.mc.forgemod.sinocraft.utility;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.function.Function;

/**
 * Constructor with one parameter.
 */
public class Constructor1<PARAM, OBJ> implements Function<PARAM, OBJ> {

    Constructor<? extends OBJ> constructor;

    public Constructor1(Class<? extends OBJ> type, Class<PARAM> paramType) {
        try {
            constructor = type.getConstructor(paramType);
            constructor.setAccessible(true);
        } catch (NoSuchMethodException e) {
            SinoCraft.getLogger().error("Can't find constructor with parameter {} in {}", paramType.getName(), type.getName());
            constructor = null;
            e.printStackTrace();
        }
    }

    public Constructor1(String type, Class<PARAM> paramType) {
        try {
            Class<? extends OBJ> aClass = (Class<? extends OBJ>) Constructor1.class.getClassLoader().loadClass(type);
            constructor = aClass.getConstructor(paramType);
            constructor.setAccessible(true);
        } catch (ClassNotFoundException e) {
            SinoCraft.getLogger().error("Can't find class " + type);
            constructor = null;
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            SinoCraft.getLogger().error("Can't find constructor with parameter {} in {}", paramType.getName(), type);
            constructor = null;
            e.printStackTrace();
        }
    }

    @Override
    public OBJ apply(PARAM param) {
        if (constructor == null) return null;
        try {
            return constructor.newInstance(param);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            SinoCraft.getLogger().error("Can't create object by {} with {}, because of {}", constructor, param, e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}
