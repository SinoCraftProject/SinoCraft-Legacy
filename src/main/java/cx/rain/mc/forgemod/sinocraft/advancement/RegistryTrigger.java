package cx.rain.mc.forgemod.sinocraft.advancement;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.advancements.criterion.RightClickBlockWithItemTrigger;
import net.minecraft.util.ResourceLocation;

import static cx.rain.mc.forgemod.sinocraft.SinoCraft.MODID;

public class RegistryTrigger {
    public static final RightClickBlockWithItemTrigger RIGHT_CLICK_BLOCK_WITH_ITEM = CriteriaTriggers.register(new RightClickBlockWithItemTrigger(new ResourceLocation(MODID,"right_click_block_with_item")));
    public static final RightClickBlockWithItemTrigger SHAVE_BARK_WITH_KNIFE = CriteriaTriggers.register(new RightClickBlockWithItemTrigger(new ResourceLocation(MODID,"shave_bark_with_knife")));
    public static final RightClickBlockWithItemTrigger SHAVE_WITH_KNIFE = CriteriaTriggers.register(new RightClickBlockWithItemTrigger(new ResourceLocation(MODID,"shave_with_knife")));
}
