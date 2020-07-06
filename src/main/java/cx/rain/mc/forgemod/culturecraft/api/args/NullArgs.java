package cx.rain.mc.forgemod.culturecraft.api.args;

import java.util.concurrent.Callable;

public class NullArgs implements Callable<Object[][]>{
    @Override
    public Object[][] call() throws Exception{
        return null;
    }
}