package cx.rain.mc.forgemod.culturecraft.api.interfaces;

import net.minecraft.block.Block;
import java.lang.reflect.InvocationTargetException;

public class BlockFactory implements IBlockFactory{
    @Override
    public Block get(Class<? extends Block> type,@Nullable Object[] args) throws NoSuchMethodException,IllegalAccessException,InstantiationException,InvocationTargetException{
        return (Block)type.getConstructor().newInstance();
    }
}