package cx.rain.mc.forgemod.sinocraft.api.base;

import net.minecraft.item.Item;
import net.minecraft.item.ItemUseContext;
import net.minecraft.util.ActionResultType;

public abstract class ItemUsable extends Item {
    public ItemUsable(Properties properties) {
        super(properties);
    }

    public abstract ActionResultType clientUse(ItemUseContext context);
    public abstract ActionResultType serverUse(ItemUseContext context);

    @Override
    public ActionResultType onItemUse(ItemUseContext context) {
        if (context.getWorld().isRemote) {
            return clientUse(context);
        }
        else {
            return serverUse(context);
        }
    }
}
