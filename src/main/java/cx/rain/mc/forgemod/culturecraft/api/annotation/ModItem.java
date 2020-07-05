package cx.rain.mc.forgemod.culturecraft.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.IItemFactory;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.ItemFactory;

/**
 * ModItem annotation.
 * For auto register items.
 * Use on class extends Item.
 * @author AmemiyaSigure
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModItem {
    /**
     * Registry name of item
     * @return RegistryName
     */
    String name();

    /**
     * The factory class get the instance of item
     * @return The factory
     */
    Class<? extends IItemFactory> factory() default ItemFactory.class;

    /**
     * The args to factory's callable factory
     * @return The args to factory's callable factory
     */
    Class<? extends Callable<Object[][]>> args() default NullArgs;
}

