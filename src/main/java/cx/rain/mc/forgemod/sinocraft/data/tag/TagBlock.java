package cx.rain.mc.forgemod.sinocraft.data.tag;

import cx.rain.mc.forgemod.sinocraft.SinoCraft;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags;

public class TagBlock {
    public static final Tags.IOptionalNamedTag<Block> LOG_PEACH = BlockTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_peach"));
    public static final Tags.IOptionalNamedTag<Block> LOG_WALNUT = BlockTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_walnut"));
    public static final Tags.IOptionalNamedTag<Block> LOG_PLUM = BlockTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_plum"));
    public static final Tags.IOptionalNamedTag<Block> LOG_MULBERRY = BlockTags.createOptional(new ResourceLocation(SinoCraft.MODID, "log_mulberry"));
}
