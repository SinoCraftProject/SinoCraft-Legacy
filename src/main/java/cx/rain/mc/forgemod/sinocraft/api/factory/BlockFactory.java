package cx.rain.mc.forgemod.sinocraft.api.factory;

import cx.rain.mc.forgemod.sinocraft.api.interfaces.IBlockFactory;
import net.minecraft.block.Block;

import java.lang.reflect.InvocationTargetException;
import javax.annotation.Nullable;

/**
 * @deprecated 该接口不再有任何用处
 */
@Deprecated
public class BlockFactory implements IBlockFactory {
    @Override
    public Block get(Class<? extends Block> type, @Nullable Object[] args) throws NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        return (Block) type.getConstructor().newInstance();
    }
}