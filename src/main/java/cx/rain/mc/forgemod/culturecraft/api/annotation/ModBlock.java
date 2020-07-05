package cx.rain.mc.forgemod.culturecraft.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.IBlockFactory;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.BlockFactory;

/**
 * ModBlock annotation.
 * For auto register blocks.
 * Use on class extends Block.
 * @author AmemiyaSigure
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModBlock {
    /**
     * Registry name of block
     * @return RegistryName
     */
    String name();

    /**
     * A factory class get the instance of block
     * @return The factory
     */
    Class<? extends IBlockFactory> factory() default BlockFactory.class;
}