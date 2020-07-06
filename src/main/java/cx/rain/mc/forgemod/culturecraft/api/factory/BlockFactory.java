package cx.rain.mc.forgemod.culturecraft.api.factory;

import cx.rain.mc.forgemod.culturecraft.api.interfaces.IBlockFactory;
import net.minecraft.block.Block;
import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

public class BlockFactory implements IBlockFactory {
    @Override
    public Block get(Class<? extends Block> type,@Nullable Object[] args) throws NoSuchMethodException,IllegalAccessException,InstantiationException,InvocationTargetException{
        return (Block)type.getConstructor().newInstance();
    }
}