package cx.rain.mc.forgemod.sinocraft.item.base;

import cx.rain.mc.forgemod.sinocraft.item.ModGroups;
import net.minecraft.fluid.FlowingFluid;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;

public class ItemBucket extends BucketItem {

    public ItemBucket(RegistryObject<FlowingFluid> fluid) {
        super(fluid, new Properties().group(ModGroups.MISC).containerItem(Items.BUCKET).maxStackSize(1));
    }
}
