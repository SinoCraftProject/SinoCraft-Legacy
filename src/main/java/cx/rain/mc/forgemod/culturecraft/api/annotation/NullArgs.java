package cx.rain.mc.forgemod.culturecraft.api.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.IBlockFactory;
import cx.rain.mc.forgemod.culturecraft.api.interfaces.BlockFactory;
import java.util.concurrent.Callable;

public class NullArgs implements Callable<Object[][]>{
    @Override
    public Object[][] call() throws Exception{
        return null;
    }
}