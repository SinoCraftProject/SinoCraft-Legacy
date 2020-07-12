package cx.rain.mc.forgemod.culturecraft.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.Callable;

import cx.rain.mc.forgemod.culturecraft.api.argument.NullArgs;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.IItemFactory;
import cx.rain.mc.forgemod.culturecraft.api.factory.ItemFactory;

/**
 * ModItem annotation.
 * For auto register items.
 * Use on class extends Item.
 * @author AmemiyaSigure
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ModItem {
    /**
     * Registry name of item
     * @return RegistryName
     */
    String name();

    /**
     * Item with special handheld model
     * @return special handheld model
     */
    HandheldModel handheldModel() default @HandheldModel(value = "");

    /**
     * The factory class get the instance of item
     * @return The factory
     */
    Class<? extends IItemFactory> factory() default ItemFactory.class;

    /**
     * The args to factory's callable factory
     * @return The args to factory's callable factory
     */
    Class<? extends Callable<Object[][]>> args() default NullArgs.class;
}

