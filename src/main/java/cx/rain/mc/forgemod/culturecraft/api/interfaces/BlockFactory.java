package cx.rain.mc.forgemod.culturecraft.api.interfaces;

import net.minecraft.block.Block;

public class BlockFactory implements IBlockFactory{
    @Override
    public Block get(Class<? extends Block> type) throws NoSuchMethodException,IllegalAccessException,InstantiationException,InvocationTargetException{
        return (Block)type.getConstructor().newInstance();
    }
}