package cx.rain.mc.forgemod.culturecraft.api.annotation;

import net.minecraft.block.Block;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Deprecated
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface ModTileEntity {
    String name();

    Class<? extends Block>[] validBlocks();
}
