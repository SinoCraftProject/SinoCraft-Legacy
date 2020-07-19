package cx.rain.mc.forgemod.sinocraft.item;

import cx.rain.mc.forgemod.sinocraft.group.Groups;
import cx.rain.mc.forgemod.sinocraft.enumerate.LogType;
import net.minecraft.item.Item;

public class ItemKnife extends Item {
	public static interface IShave{
	//	Item Shave(PlayerEntity player,IBlockState state,);
	}
	//private static Map<RegistryObject<Block>,> recipe = new HashMap();

    public ItemKnife(/*LogType typeIn*/) {
        super(new Item.Properties().group(Groups.MISC));
    }
}
