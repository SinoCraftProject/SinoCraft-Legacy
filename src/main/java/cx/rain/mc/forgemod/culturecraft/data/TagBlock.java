package cx.rain.mc.forgemod.culturecraft.data;

import cx.rain.mc.forgemod.culturecraft.CultureCraft;
import net.minecraft.block.Block;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.Tag;
import net.minecraft.util.ResourceLocation;

public class TagBlock {
    public static final Tag<Block> LOG_PEACH =
            new BlockTags.Wrapper(new ResourceLocation(CultureCraft.MODID, "log_peach"));
    public static final Tag<Block> LOG_WALNUT =
            new BlockTags.Wrapper(new ResourceLocation(CultureCraft.MODID, "log_walnut"));
    public static final Tag<Block> LOG_PLUM =
            new BlockTags.Wrapper(new ResourceLocation(CultureCraft.MODID, "log_plum"));
}
