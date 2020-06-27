package cx.rain.mc.forgemod.culturecraft.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

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
}
