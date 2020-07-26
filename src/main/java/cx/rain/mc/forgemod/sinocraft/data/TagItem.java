package cx.rain.mc.forgemod.sinocraft.data;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class TagItem {
    public static final Tag<Item> LOG_PEACH = new ItemTags.Wrapper(new ResourceLocation(SinoCraft.MODID, "log_peach"));
    public static final Tag<Item> LOG_WALNUT = new ItemTags.Wrapper(new ResourceLocation(SinoCraft.MODID, "log_walnut"));
    public static final Tag<Item> LOG_PLUM = new ItemTags.Wrapper(new ResourceLocation(SinoCraft.MODID, "log_plum"));
    public static final Tag<Item> LOG_MULBERRY = new ItemTags.Wrapper(new ResourceLocation(SinoCraft.MODID, "log_mulberry"));
    public static final Tag<Item> KNIFE = new ItemTags.Wrapper(new ResourceLocation(SinoCraft.MODID, "knife"));
}
