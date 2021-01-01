package cx.rain.mc.forgemod.sinocraft.data;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.item.Item;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TagItem {
    public static final Tags.IOptionalNamedTag<Item> LOG_PEACH = ItemTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_peach"));
    public static final Tags.IOptionalNamedTag<Item> LOG_WALNUT = ItemTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_walnut"));
    public static final Tags.IOptionalNamedTag<Item> LOG_PLUM = ItemTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_plum"));
    public static final Tags.IOptionalNamedTag<Item> LOG_MULBERRY = ItemTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_mulberry"));
    public static final Tags.IOptionalNamedTag<Item> KNIFE = ItemTags.createOptional(new ResourceLocation(SinoCraft.MODID, "knife"));
}
