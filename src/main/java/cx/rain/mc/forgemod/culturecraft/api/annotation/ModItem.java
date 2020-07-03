package cx.rain.mc.forgemod.culturecraft.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ModItem annotation.
 * For auto register items.
 * Use on class extends ItemKnife.
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
}

